package com.mapvina.spatialk.geojson.serialization

import kotlinx.serialization.builtins.ListSerializer
import com.mapvina.spatialk.geojson.BoundingBox
import com.mapvina.spatialk.geojson.MultiLineString
import com.mapvina.spatialk.geojson.Position

internal object MultiLineStringSerializer :
    BaseGeometrySerializer<MultiLineString, List<List<Position>>>(
        "MultiLineString",
        ListSerializer(ListSerializer(Position.serializer())),
    ) {
    override fun getCoordinates(value: MultiLineString) = value.coordinates

    override fun construct(coordinates: List<List<Position>>, bbox: BoundingBox?) =
        MultiLineString(coordinates, bbox)
}
