package com.mapvina.spatialk.turf.measurement

import kotlin.test.Test
import kotlin.test.assertEquals
import com.mapvina.spatialk.geojson.LineString
import com.mapvina.spatialk.geojson.Position
import com.mapvina.spatialk.testutil.assertPositionEquals
import com.mapvina.spatialk.testutil.readResourceFile
import com.mapvina.spatialk.units.extensions.kilometers

class AlongTest {

    @Test
    fun testAlong() {
        val geometry = LineString.fromJson(readResourceFile("measurement/along/lineString.json"))

        assertPositionEquals(
            Position(-79.4179672644524, 43.636029126566484),
            geometry.locateAlong(1.kilometers).coordinates,
        )
        assertPositionEquals(
            Position(-79.39973865844715, 43.63797943080659),
            geometry.locateAlong(2.5.kilometers).coordinates,
        )
        assertPositionEquals(
            Position(-79.37493324279785, 43.64470906117713),
            geometry.locateAlong(100.kilometers).coordinates,
        )
        assertEquals(geometry.coordinates.last(), geometry.locateAlong(100.kilometers).coordinates)
    }
}
