package io.github.mapvina.spatialk.geojson.serialization

import io.github.mapvina.spatialk.geojson.SingleGeometry

internal object SingleGeometrySerializer :
    GeoJsonPolymorphicSerializer<SingleGeometry>(
        baseClass = SingleGeometry::class,
        allowedTypes = setOf("Point", "LineString", "Polygon"),
    )
