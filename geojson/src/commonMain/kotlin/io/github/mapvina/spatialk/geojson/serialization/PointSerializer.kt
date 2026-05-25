package io.github.mapvina.spatialk.geojson.serialization

import io.github.mapvina.spatialk.geojson.BoundingBox
import io.github.mapvina.spatialk.geojson.Point
import io.github.mapvina.spatialk.geojson.Position

internal object PointSerializer :
    BaseGeometrySerializer<Point, Position>("Point", Position.serializer()) {
    override fun getCoordinates(value: Point) = value.coordinates

    override fun construct(coordinates: Position, bbox: BoundingBox?) = Point(coordinates, bbox)
}
