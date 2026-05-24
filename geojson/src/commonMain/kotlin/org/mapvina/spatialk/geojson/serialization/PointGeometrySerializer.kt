package com.mapvina.spatialk.geojson.serialization

import com.mapvina.spatialk.geojson.PointGeometry

internal object PointGeometrySerializer :
    GeoJsonPolymorphicSerializer<PointGeometry>(
        baseClass = PointGeometry::class,
        allowedTypes = setOf("Point", "MultiPoint"),
    )
