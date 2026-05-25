package io.github.mapvina.spatialk.turf.measurement

import kotlin.test.Test
import io.github.mapvina.spatialk.geojson.Position
import io.github.mapvina.spatialk.testutil.assertDoubleEquals
import io.github.mapvina.spatialk.units.extensions.inKilometers

class DistanceTest {

    @Test
    fun testDistance() {
        val a = Position(-73.67, 45.48)
        val b = Position(-79.48, 43.68)

        assertDoubleEquals(501.64563403765925, distance(a, b).inKilometers)
    }
}
