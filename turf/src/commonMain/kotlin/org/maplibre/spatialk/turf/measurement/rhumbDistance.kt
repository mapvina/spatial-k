@file:JvmName("Measurement")
@file:JvmMultifileClass

package com.mapvina.spatialk.turf.measurement

import kotlin.jvm.JvmMultifileClass
import kotlin.jvm.JvmName
import kotlin.jvm.JvmOverloads
import kotlin.jvm.JvmSynthetic
import kotlin.math.*
import com.mapvina.spatialk.geojson.Point
import com.mapvina.spatialk.geojson.Position
import com.mapvina.spatialk.units.International.Meters
import com.mapvina.spatialk.units.Length
import com.mapvina.spatialk.units.LengthUnit
import com.mapvina.spatialk.units.extensions.earthRadians

/** Calculates the distance along a rhumb line between two points. */
@JvmSynthetic
@JvmName("rhumbDistanceAsLength")
public fun rhumbDistance(from: Position, to: Position): Length {
    // compensate the crossing of the 180th meridian
    val destination =
        Position(
            to.longitude +
                when {
                    to.longitude - from.longitude > 180 -> -360
                    from.longitude - to.longitude > 180 -> 360
                    else -> 0
                },
            to.latitude,
        )

    val phi1 = from.latitude * PI / 180
    val phi2 = destination.latitude * PI / 180
    val deltaPhi = phi2 - phi1
    var deltaLambda = abs(destination.longitude - from.longitude) * PI / 180

    if (deltaLambda > PI) {
        deltaLambda -= 2 * PI
    }

    val deltaPsi = ln(tan(phi2 / 2 + PI / 4) / tan(phi1 / 2 + PI / 4))
    val q = if (abs(deltaPsi) > 10e-12) deltaPhi / deltaPsi else cos(phi1)

    val delta = sqrt(deltaPhi * deltaPhi + q * q * deltaLambda * deltaLambda)
    val dist = delta.earthRadians

    return dist
}

/** Calculates the distance along a rhumb line between two points. */
@JvmSynthetic
@JvmName("rhumbDistanceAsLength")
public fun rhumbDistance(from: Point, to: Point): Length =
    rhumbDistance(from.coordinates, to.coordinates)

@PublishedApi
@Suppress("unused")
@JvmOverloads
internal fun rhumbDistance(
    origin: Position,
    destination: Position,
    unit: LengthUnit = Meters,
): Double = rhumbDistance(origin, destination).toDouble(unit)

@PublishedApi
@Suppress("unused")
@JvmOverloads
internal fun rhumbDistance(origin: Point, destination: Point, unit: LengthUnit = Meters): Double =
    rhumbDistance(origin.coordinates, destination.coordinates, unit)
