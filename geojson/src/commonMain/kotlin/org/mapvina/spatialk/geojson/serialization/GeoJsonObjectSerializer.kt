package com.mapvina.spatialk.geojson.serialization

import com.mapvina.spatialk.geojson.GeoJsonObject

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
