package io.github.mapvina.spatialk.geojson.serialization

import kotlinx.serialization.builtins.ListSerializer
import io.github.mapvina.spatialk.geojson.BoundingBox
import io.github.mapvina.spatialk.geojson.LineString
import io.github.mapvina.spatialk.geojson.Position

internal object LineStringSerializer :
    BaseGeometrySerializer<LineString, List<Position>>(
        "LineString",
        ListSerializer(Position.serializer()),
    ) {
    override fun getCoordinates(value: LineString) = value.coordinates

    override fun construct(coordinates: List<Position>, bbox: BoundingBox?) =
        LineString(coordinates, bbox)
}
