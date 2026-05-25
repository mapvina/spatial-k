package io.github.mapvina.spatialk.geojson.serialization

import io.github.mapvina.spatialk.geojson.LineStringGeometry

internal object LineStringGeometrySerializer :
    GeoJsonPolymorphicSerializer<LineStringGeometry>(
        baseClass = LineStringGeometry::class,
        allowedTypes = setOf("LineString", "MultiLineString"),
    )
