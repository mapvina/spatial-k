package com.mapvina.spatialk.turf.measurement

import kotlin.test.Test
import com.mapvina.spatialk.geojson.Polygon
import com.mapvina.spatialk.geojson.dsl.buildGeometryCollection
import com.mapvina.spatialk.testutil.assertDoubleEquals
import com.mapvina.spatialk.testutil.readResourceFile
import com.mapvina.spatialk.units.extensions.inSquareMeters

class AreaTest {

    @Test
    fun testArea() {
        val geometry = Polygon.fromJson(readResourceFile("measurement/area/polygon.json"))
        assertDoubleEquals(236446.506, geometry.area().inSquareMeters, 0.001, "Single polygon")

        val other = Polygon.fromJson(readResourceFile("measurement/area/other.json"))
        val collection = buildGeometryCollection {
            add(geometry)
            add(other)
        }
        assertDoubleEquals(
            4173831.866,
            collection.area().inSquareMeters,
            0.001,
            "Geometry Collection",
        )
    }
}
