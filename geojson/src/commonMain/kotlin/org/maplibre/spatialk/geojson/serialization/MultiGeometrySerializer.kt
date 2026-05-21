package com.mapvina.spatialk.geojson.serialization

import com.mapvina.spatialk.geojson.MultiGeometry

internal object MultiGeometrySerializer :
    GeoJsonPolymorphicSerializer<MultiGeometry>(
        baseClass = MultiGeometry::class,
        allowedTypes = setOf("MultiPoint", "MultiLineString", "MultiPolygon"),
    )
