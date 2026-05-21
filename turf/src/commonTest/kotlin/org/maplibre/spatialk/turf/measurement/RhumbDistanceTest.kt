package com.mapvina.spatialk.turf.measurement

import kotlin.test.Test
import com.mapvina.spatialk.geojson.Position
import com.mapvina.spatialk.testutil.assertDoubleEquals
import com.mapvina.spatialk.units.extensions.inKilometers

class RhumbDistanceTest {

    @Test
    fun testRhumbDistance() {
        val distance = rhumbDistance(Position(-75.343, 39.984), Position(-75.534, 39.123))

        assertDoubleEquals(97.12923942772163, distance.inKilometers, 0.000001)
    }
}
