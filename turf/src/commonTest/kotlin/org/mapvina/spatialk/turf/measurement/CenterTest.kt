package com.mapvina.spatialk.turf.measurement

import kotlin.test.Test
import com.mapvina.spatialk.geojson.Polygon
import com.mapvina.spatialk.testutil.assertDoubleEquals
import com.mapvina.spatialk.testutil.readResourceFile

class CenterTest {

    @Test
    fun testCenterFromGeometry() {
        val geometry = Polygon.fromJson(readResourceFile("measurement/area/other.json"))

        val centerPoint = geometry.computeBbox().center()

        assertDoubleEquals(-75.71805238723755, centerPoint.longitude, 0.0001)
        assertDoubleEquals(45.3811030151199, centerPoint.latitude, 0.0001)
    }
}
