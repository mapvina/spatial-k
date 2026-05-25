package io.github.mapvina.spatialk.geojson.serialization

import kotlinx.serialization.builtins.ListSerializer
import io.github.mapvina.spatialk.geojson.BoundingBox
import io.github.mapvina.spatialk.geojson.MultiLineString
import io.github.mapvina.spatialk.geojson.Position

internal object MultiLineStringSerializer :
    BaseGeometrySerializer<MultiLineString, List<List<Position>>>(
        "MultiLineString",
        ListSerializer(ListSerializer(Position.serializer())),
    ) {
    override fun getCoordinates(value: MultiLineString) = value.coordinates

    override fun construct(coordinates: List<List<Position>>, bbox: BoundingBox?) =
        MultiLineString(coordinates, bbox)
}
