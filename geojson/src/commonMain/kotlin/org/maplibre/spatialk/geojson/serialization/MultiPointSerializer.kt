package com.mapvina.spatialk.geojson.serialization

import kotlinx.serialization.builtins.ListSerializer
import com.mapvina.spatialk.geojson.BoundingBox
import com.mapvina.spatialk.geojson.MultiPoint
import com.mapvina.spatialk.geojson.Position

internal object MultiPointSerializer :
    BaseGeometrySerializer<MultiPoint, List<Position>>(
        "MultiPoint",
        ListSerializer(Position.serializer()),
    ) {
    override fun getCoordinates(value: MultiPoint) = value.coordinates

    override fun construct(coordinates: List<Position>, bbox: BoundingBox?) =
        MultiPoint(coordinates, bbox)
}
