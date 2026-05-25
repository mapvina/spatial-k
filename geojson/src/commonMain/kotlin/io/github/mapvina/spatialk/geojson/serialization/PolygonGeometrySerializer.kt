package io.github.mapvina.spatialk.geojson.serialization

import io.github.mapvina.spatialk.geojson.PolygonGeometry

internal object PolygonGeometrySerializer :
    GeoJsonPolymorphicSerializer<PolygonGeometry>(
        baseClass = PolygonGeometry::class,
        allowedTypes = setOf("Polygon", "MultiPolygon"),
    )
