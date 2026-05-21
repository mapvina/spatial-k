package com.mapvina.spatialk.geojson.serialization

import com.mapvina.spatialk.geojson.BoundingBox
import com.mapvina.spatialk.geojson.Point
import com.mapvina.spatialk.geojson.Position

internal object PointSerializer :
    BaseGeometrySerializer<Point, Position>("Point", Position.serializer()) {
    override fun getCoordinates(value: Point) = value.coordinates

    override fun construct(coordinates: Position, bbox: BoundingBox?) = Point(coordinates, bbox)
}
