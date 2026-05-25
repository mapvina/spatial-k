@file:JvmName("Measurement")
@file:JvmMultifileClass

package io.github.mapvina.spatialk.turf.measurement

import kotlin.jvm.JvmMultifileClass
import kotlin.jvm.JvmName
import io.github.mapvina.spatialk.geojson.BoundingBox
import io.github.mapvina.spatialk.geojson.Polygon
import io.github.mapvina.spatialk.geojson.Position

/**
 * Takes a bounding box and returns an equivalent [Polygon].
 *
 * @return The bounding box as a polygon
 */
public fun BoundingBox.toPolygon(): Polygon {
    require(northeast.altitude == null && southwest.altitude == null) {
        "Bounding Box cannot have altitudes"
    }

    return Polygon(
        listOf(
            southwest,
            Position(northeast.longitude, southwest.latitude),
            northeast,
            Position(southwest.longitude, northeast.latitude),
            southwest,
        ),
        bbox = this,
    )
}
