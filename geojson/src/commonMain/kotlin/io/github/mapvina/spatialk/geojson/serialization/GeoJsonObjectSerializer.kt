package io.github.mapvina.spatialk.geojson.serialization

import io.github.mapvina.spatialk.geojson.GeoJsonObject

internal object GeoJsonObjectSerializer :
    GeoJsonPolymorphicSerializer<GeoJsonObject>(
        GeoJsonObject::class,
        setOf(
            "Point",
            "MultiPoint",
            "LineString",
            "MultiLineString",
            "Polygon",
            "MultiPolygon",
            "GeometryCollection",
            "Feature",
            "FeatureCollection",
        ),
    )
