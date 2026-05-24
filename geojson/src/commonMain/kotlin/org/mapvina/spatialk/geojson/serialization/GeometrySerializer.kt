package com.mapvina.spatialk.geojson.serialization

import com.mapvina.spatialk.geojson.Geometry

internal object GeometrySerializer :
    GeoJsonPolymorphicSerializer<Geometry>(
        baseClass = Geometry::class,
        allowedTypes =
            setOf(
                "Point",
                "MultiPoint",
                "LineString",
                "MultiLineString",
                "Polygon",
                "MultiPolygon",
                "GeometryCollection",
            ),
    )
