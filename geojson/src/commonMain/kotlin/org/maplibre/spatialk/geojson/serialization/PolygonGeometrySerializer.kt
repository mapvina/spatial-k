package com.mapvina.spatialk.geojson.serialization

import com.mapvina.spatialk.geojson.PolygonGeometry

internal object PolygonGeometrySerializer :
    GeoJsonPolymorphicSerializer<PolygonGeometry>(
        baseClass = PolygonGeometry::class,
        allowedTypes = setOf("Polygon", "MultiPolygon"),
    )
