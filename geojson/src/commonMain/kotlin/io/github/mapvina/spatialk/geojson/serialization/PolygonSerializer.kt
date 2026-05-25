package io.github.mapvina.spatialk.geojson.serialization

import kotlinx.serialization.builtins.ListSerializer
import io.github.mapvina.spatialk.geojson.BoundingBox
import io.github.mapvina.spatialk.geojson.Polygon
import io.github.mapvina.spatialk.geojson.Position

internal object PolygonSerializer :
    BaseGeometrySerializer<Polygon, List<List<Position>>>(
        "Polygon",
        ListSerializer(ListSerializer(Position.serializer())),
    ) {
    override fun getCoordinates(value: Polygon) = value.coordinates

    override fun construct(coordinates: List<List<Position>>, bbox: BoundingBox?) =
        Polygon(coordinates, bbox)
}
