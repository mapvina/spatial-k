package io.github.mapvina.spatialk.turf.measurement

import kotlin.test.Test
import kotlin.test.assertEquals
import io.github.mapvina.spatialk.geojson.LineString
import io.github.mapvina.spatialk.testutil.readResourceFile
import io.github.mapvina.spatialk.units.extensions.inKilometers

class LengthTest {

    @Test
    fun testLength() {
        val geometry = LineString.fromJson(readResourceFile("measurement/length/lineString.json"))

        assertEquals(42.560767589197006, geometry.length().inKilometers)
    }
}
