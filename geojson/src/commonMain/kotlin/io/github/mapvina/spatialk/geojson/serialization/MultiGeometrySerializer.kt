package io.github.mapvina.spatialk.geojson.serialization

import io.github.mapvina.spatialk.geojson.MultiGeometry

internal object MultiGeometrySerializer :
    GeoJsonPolymorphicSerializer<MultiGeometry>(
        baseClass = MultiGeometry::class,
        allowedTypes = setOf("MultiPoint", "MultiLineString", "MultiPolygon"),
    )
