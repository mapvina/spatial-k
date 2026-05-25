@file:JvmName("Miscellaneous")
@file:JvmMultifileClass

package io.github.mapvina.spatialk.turf.misc

import kotlin.jvm.JvmMultifileClass
import kotlin.jvm.JvmName
import io.github.mapvina.spatialk.geojson.GeometryCollection
import io.github.mapvina.spatialk.geojson.MultiPoint
import io.github.mapvina.spatialk.geojson.Point
import io.github.mapvina.spatialk.geojson.PointGeometry
import io.github.mapvina.spatialk.geojson.PolygonGeometry
import io.github.mapvina.spatialk.turf.booleans.contains

/**
 * Filters a [GeometryCollection] of point geometries to include only those that are inside any of
 * the given polygons.
 *
 * For [MultiPoint] geometries, filters individual points within each multi-point and only includes
 * the multi-point if at least one point is inside a polygon.
 *
 * @param polygons Collection of polygon geometries to test containment against.
 * @return A [GeometryCollection] containing only the point geometries (or portions thereof) that
 *   are inside at least one of the polygons.
 */
public fun <G : PointGeometry> GeometryCollection<G>.filterInside(
    polygons: GeometryCollection<PolygonGeometry>
): GeometryCollection<G> {
    val results = mutableListOf<G>()

    forEach { pointGeometry ->
        when (pointGeometry) {
            is Point ->
                if (polygons.any { pointGeometry.coordinates in it }) results.add(pointGeometry)
            is MultiPoint -> {
                val pointsInside = pointGeometry.filter { point ->
                    polygons.any { point.coordinates in it }
                }
                @Suppress("UNCHECKED_CAST")
                if (pointsInside.isNotEmpty())
                    results.add(MultiPoint(pointsInside.map { it.coordinates }) as G)
            }
        }
    }

    return GeometryCollection(results)
}
