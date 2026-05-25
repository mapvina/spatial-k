package io.github.mapvina.spatialk.geojson.serialization

import io.github.mapvina.spatialk.geojson.PointGeometry

internal object PointGeometrySerializer :
    GeoJsonPolymorphicSerializer<PointGeometry>(
        baseClass = PointGeometry::class,
        allowedTypes = setOf("Point", "MultiPoint"),
    )
