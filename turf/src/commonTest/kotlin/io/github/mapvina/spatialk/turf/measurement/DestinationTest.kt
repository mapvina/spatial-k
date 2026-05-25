package io.github.mapvina.spatialk.turf.measurement

import kotlin.test.Test
import io.github.mapvina.spatialk.geojson.Position
import io.github.mapvina.spatialk.testutil.assertDoubleEquals
import io.github.mapvina.spatialk.units.Bearing
import io.github.mapvina.spatialk.units.extensions.kilometers

class DestinationTest {

    @Test
    fun testDestination() {
        val point0 = Position(-75.0, 38.10096062273525)
        val (longitude, latitude) = point0.offset(100.kilometers, Bearing.North)

        assertDoubleEquals(-75.0, longitude, 0.1)
        assertDoubleEquals(39.000281, latitude, 0.000001)
    }
}
