package com.mapvina.spatialk.geojson.dsl

import com.mapvina.spatialk.geojson.BoundingBox
import com.mapvina.spatialk.geojson.LineString
import com.mapvina.spatialk.geojson.MultiLineString
import com.mapvina.spatialk.geojson.Position

/**
 * Builder for constructing [MultiLineString] objects using a DSL.
 *
 * @property bbox An optional [BoundingBox] for this [MultiLineString].
 * @see MultiLineString
 * @see buildMultiLineString
 * @see addLineString
 */
@GeoJsonDsl
public class MultiLineStringBuilder {
    public var bbox: BoundingBox? = null
    private val coordinates: MutableList<List<Position>> = mutableListOf()

    /**
     * Adds a [LineString] to this [MultiLineString].
     *
     * @param lineString The [LineString] to add.
     */
    public fun add(lineString: LineString) {
        coordinates.add(lineString.coordinates)
    }

    /**
     * Builds the [MultiLineString] from the configured values.
     *
     * @return The constructed [MultiLineString].
     */
    public fun build(): MultiLineString = MultiLineString(coordinates, bbox)
}
