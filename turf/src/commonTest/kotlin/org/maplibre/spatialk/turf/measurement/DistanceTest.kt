package com.mapvina.spatialk.turf.measurement

import kotlin.test.Test
import com.mapvina.spatialk.geojson.Position
import com.mapvina.spatialk.testutil.assertDoubleEquals
import com.mapvina.spatialk.units.extensions.inKilometers

class DistanceTest {

    @Test
    fun testDistance() {
        val a = Position(-73.67, 45.48)
        val b = Position(-79.48, 43.68)

        assertDoubleEquals(501.64563403765925, distance(a, b).inKilometers)
    }
}
