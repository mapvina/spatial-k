@file:JvmName("Measurement")
@file:JvmMultifileClass

package io.github.mapvina.spatialk.turf.measurement

import kotlin.jvm.JvmMultifileClass
import kotlin.jvm.JvmName
import kotlin.jvm.JvmOverloads
import kotlin.jvm.JvmSynthetic
import io.github.mapvina.spatialk.geojson.LineString
import io.github.mapvina.spatialk.geojson.Point
import io.github.mapvina.spatialk.units.International.Meters
import io.github.mapvina.spatialk.units.Length
import io.github.mapvina.spatialk.units.LengthUnit
import io.github.mapvina.spatialk.units.extensions.toLength

/**
 * Takes a [LineString] and returns a [Point] at a specified distance along the line.
 *
 * @param distance distance along the line
 * @return A point [distance] along the line
 */
@JvmSynthetic
public fun LineString.locateAlong(distance: Length): Point {
    var travelled = Length.Zero

    coordinates.forEachIndexed { i, coordinate ->
        when {
            distance >= travelled && i == coordinates.size - 1 -> {}
            travelled >= distance -> {
                val overshot = distance - travelled
                return if (overshot.isZero) Point(coordinate)
                else {
                    val direction = coordinates[i - 1].bearingTo(coordinate)
                    Point(coordinate.offset(overshot, direction))
                }
            }

            else -> travelled += distance(coordinate, coordinates[i + 1])
        }
    }

    return Point(coordinates[coordinates.size - 1])
}

@PublishedApi
@Suppress("unused")
@JvmOverloads
internal fun locateAlong(line: LineString, distance: Double, unit: LengthUnit = Meters): Point =
    line.locateAlong(distance.toLength(unit))
