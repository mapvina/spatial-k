package com.mapvina.spatialk.geojson.serialization

import com.mapvina.spatialk.geojson.LineStringGeometry

internal object LineStringGeometrySerializer :
    GeoJsonPolymorphicSerializer<LineStringGeometry>(
        baseClass = LineStringGeometry::class,
        allowedTypes = setOf("LineString", "MultiLineString"),
    )
