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
import io.github.mapvina.spatialk.units.International.Meters
import io.github.mapvina.spatialk.units.Length
import io.github.mapvina.spatialk.units.LengthUnit
import io.github.mapvina.spatialk.units.RotationUnit
import io.github.mapvina.spatialk.units.extensions.*

/**
 * Takes a [Position] and calculates the location of a destination position given a distance
 * [Length] and [Bearing]. This uses the
 * [Haversine formula](https://en.wikipedia.org/wiki/Haversine_formula) to account for global
 * curvature.
 *
 * @param distance distance from the origin point
 * @param bearing direction from the origin point
 * @return destination position
 */
@JvmSynthetic
public fun Position.offset(distance: Length, bearing: Bearing): Position {
    val longitude1 = this.longitude.degrees
    val latitude1 = this.latitude.degrees
    val bearingFromN = (bearing - North)
    val radians = distance.inEarthRadians.radians

    val latitude2 =
        asin(sin(latitude1) * cos(radians) + cos(latitude1) * sin(radians) * cos(bearingFromN))
    val longitude2 =
        longitude1 +
            atan2(
                sin(bearingFromN) * sin(radians) * cos(latitude1),
                cos(radians) - sin(latitude1) * sin(latitude2),
            )

    return Position(longitude2.inDegrees, latitude2.inDegrees)
}

/**
 * Takes a [Point] and calculates the location of a destination position given a distance [Length]
 * and [Bearing]. This uses the [Haversine formula](https://en.wikipedia.org/wiki/Haversine_formula)
 * to account for global curvature.
 *
 * @param distance distance from the origin point
 * @param bearing direction from the origin point
 * @return destination position
 */
@JvmSynthetic
public fun Point.offset(distance: Length, bearing: Bearing): Position =
    this.coordinates.offset(distance, bearing)

@PublishedApi
@Suppress("unused")
@JvmOverloads
internal fun offset(
    origin: Position,
    distance: Double,
    distanceUnit: LengthUnit = Meters,
    bearing: Double,
    bearingUnit: RotationUnit = Degrees,
): Position =
    origin.offset(distance.toLength(distanceUnit), North + bearing.toRotation(bearingUnit))

@PublishedApi
@Suppress("unused")
@JvmOverloads
internal fun offset(
    origin: Point,
    distance: Double,
    distanceUnit: LengthUnit = Meters,
    bearing: Double,
    bearingUnit: RotationUnit = Degrees,
): Position = offset(origin.coordinates, distance, distanceUnit, bearing, bearingUnit)
