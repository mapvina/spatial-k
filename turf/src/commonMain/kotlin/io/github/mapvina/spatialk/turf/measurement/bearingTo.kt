@file:JvmName("Measurement")
@file:JvmMultifileClass

package io.github.mapvina.spatialk.turf.measurement

import kotlin.jvm.JvmMultifileClass
import kotlin.jvm.JvmName
import kotlin.jvm.JvmOverloads
import kotlin.jvm.JvmSynthetic
import io.github.mapvina.spatialk.geojson.Point
import io.github.mapvina.spatialk.geojson.Position
import io.github.mapvina.spatialk.units.Bearing
import io.github.mapvina.spatialk.units.Bearing.Companion.North
import io.github.mapvina.spatialk.units.DMS.Degrees
import io.github.mapvina.spatialk.units.RotationUnit
import io.github.mapvina.spatialk.units.extensions.*

/**
 * Takes two positions and finds the geographic bearing between them, i.e., the angle measured in
 * degrees from the north line.
 *
 * @param to ending point
 * @param final calculates the final bearing if true
 * @return [Bearing] between this and [to]
 */
@JvmOverloads
@JvmName("bearingToAsBearing")
@JvmSynthetic
public fun Position.bearingTo(to: Position, final: Boolean = false): Bearing {
    if (final) return to.bearingTo(this) + 180.degrees

    val lon1 = this.longitude.degrees
    val lon2 = to.longitude.degrees
    val lat1 = this.latitude.degrees
    val lat2 = to.latitude.degrees

    val a = sin(lon2 - lon1) * cos(lat2)
    val b = cos(lat1) * sin(lat2) - sin(lat1) * cos(lat2) * cos(lon2 - lon1)

    return North + atan2(a, b)
}

/**
 * Takes two points and finds the geographic bearing between them, i.e., the angle measured in
 * degrees from the north line.
 *
 * @param to ending point
 * @param final calculates the final bearing if true
 * @return [Bearing] between this and [to]
 */
@JvmOverloads
@JvmName("bearingToAsBearing")
@JvmSynthetic
public fun Point.bearingTo(to: Point, final: Boolean = false): Bearing =
    this.coordinates.bearingTo(to.coordinates, final)

@PublishedApi
@Suppress("unused")
@JvmOverloads
internal fun bearingTo(
    from: Position,
    to: Position,
    final: Boolean = false,
    unit: RotationUnit = Degrees,
): Double = from.bearingTo(to, final).smallestRotationTo(North).toDouble(unit)

@PublishedApi
@Suppress("unused")
@JvmOverloads
internal fun bearingTo(
    from: Point,
    to: Point,
    final: Boolean = false,
    unit: RotationUnit = Degrees,
): Double = bearingTo(from.coordinates, to.coordinates, final, unit)
