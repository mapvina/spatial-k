package com.mapvina.spatialk.geojson.serialization

import com.mapvina.spatialk.geojson.SingleGeometry

internal object SingleGeometrySerializer :
    GeoJsonPolymorphicSerializer<SingleGeometry>(
        baseClass = SingleGeometry::class,
        allowedTypes = setOf("Point", "LineString", "Polygon"),
    )
