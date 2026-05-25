package io.github.mapvina.spatialk.geojson.dsl

import io.github.mapvina.spatialk.geojson.BoundingBox
import io.github.mapvina.spatialk.geojson.MultiPolygon
import io.github.mapvina.spatialk.geojson.Polygon
import io.github.mapvina.spatialk.geojson.Position

/**
 * Builder for constructing [MultiPolygon] objects using a DSL.
 *
 * @property bbox An optional [BoundingBox] for this [MultiPolygon].
 * @see MultiPolygon
 * @see buildMultiPolygon
 * @see addPolygon
 */
@GeoJsonDsl
public class MultiPolygonBuilder {
    public var bbox: BoundingBox? = null
    private val coordinates: MutableList<List<List<Position>>> = mutableListOf()

    /**
     * Adds a [Polygon] to this [MultiPolygon].
     *
     * @param polygon The [Polygon] to add.
     */
    public fun add(polygon: Polygon) {
        coordinates.add(polygon.coordinates)
    }

    /**
     * Builds the [MultiPolygon] from the configured values.
     *
     * @return The constructed [MultiPolygon].
     */
    public fun build(): MultiPolygon = MultiPolygon(coordinates, bbox)
}
