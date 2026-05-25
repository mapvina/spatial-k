package io.github.mapvina.spatialk.geojson.serialization

import kotlinx.serialization.builtins.ListSerializer
import io.github.mapvina.spatialk.geojson.BoundingBox
import io.github.mapvina.spatialk.geojson.MultiPolygon
import io.github.mapvina.spatialk.geojson.Position

internal object MultiPolygonSerializer :
    BaseGeometrySerializer<MultiPolygon, List<List<List<Position>>>>(
        "MultiPolygon",
        ListSerializer(ListSerializer(ListSerializer(Position.serializer()))),
    ) {
    override fun getCoordinates(value: MultiPolygon) = value.coordinates

    override fun construct(coordinates: List<List<List<Position>>>, bbox: BoundingBox?) =
        MultiPolygon(coordinates, bbox)
}
