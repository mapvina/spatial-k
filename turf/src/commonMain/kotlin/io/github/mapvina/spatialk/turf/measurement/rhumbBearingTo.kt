@file:JvmName("Measurement")
@file:JvmMultifileClass

package io.github.mapvina.spatialk.turf.measurement

import kotlin.jvm.JvmMultifileClass
import kotlin.jvm.JvmName
import kotlin.jvm.JvmOverloads
import kotlin.jvm.JvmSynthetic
import kotlin.math.PI
import kotlin.math.ln
import io.github.mapvina.spatialk.geojson.Point
import io.github.mapvina.spatialk.geojson.Position
import io.github.mapvina.spatialk.units.Bearing
import io.github.mapvina.spatialk.units.Bearing.Companion.North
import io.github.mapvina.spatialk.units.DMS.Degrees
import io.github.mapvina.spatialk.units.RotationUnit
import io.github.mapvina.spatialk.units.extensions.*

/**
 * Takes two positions and finds the bearing angle between them along a Rhumb line (a line with
 * constant bearing).
 *
 * @param to ending point
 * @return [Bearing] between this and [to] along a rhumb line
 */
@JvmName("rhumbBearingToAsBearing")
@JvmSynthetic
public fun Position.rhumbBearingTo(to: Position): Bearing = calculateRhumbBearing(this, to)

/**
 * Takes two points and finds the bearing angle between them along a Rhumb line (a line with
 * constant bearing).
 *
 * @param to ending point
 * @return [Bearing] between this and [to] along a rhumb line
 */
@JvmName("rhumbBearingToAsBearing")
@JvmSynthetic
public fun Point.rhumbBearingTo(to: Point): Bearing =
    this.coordinates.rhumbBearingTo(to.coordinates)

@PublishedApi
@Suppress("unused")
@JvmOverloads
internal fun rhumbBearingTo(from: Position, to: Position, unit: RotationUnit = Degrees): Double =
    from.rhumbBearingTo(to).smallestRotationTo(North).toDouble(unit)

@PublishedApi
@Suppress("unused")
@JvmOverloads
internal fun rhumbBearingTo(from: Point, to: Point, unit: RotationUnit = Degrees): Double =
    rhumbBearingTo(from.coordinates, to.coordinates, unit)

private fun calculateRhumbBearing(from: Position, to: Position): Bearing {
    val phi1 = from.latitude.degrees
    val phi2 = to.latitude.degrees
    var deltaLambda = (to.longitude - from.longitude).degrees

    if (deltaLambda > PI.radians) deltaLambda -= 2.0 * PI.radians
    if (deltaLambda < -PI.radians) deltaLambda += 2.0 * PI.radians

    val deltaPsi = ln(tan(phi2 / 2.0 + PI.radians / 4.0) / tan(phi1 / 2.0 + PI.radians / 4.0))
    val theta = atan2(deltaLambda.inRadians, deltaPsi)

    return North + theta
}
