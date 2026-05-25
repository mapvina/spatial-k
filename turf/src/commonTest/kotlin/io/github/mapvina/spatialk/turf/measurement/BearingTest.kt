package io.github.mapvina.spatialk.turf.measurement

import kotlin.test.Test
import io.github.mapvina.spatialk.geojson.Position
import io.github.mapvina.spatialk.testutil.assertBearingEquals
import io.github.mapvina.spatialk.units.Bearing.Companion.North
import io.github.mapvina.spatialk.units.extensions.degrees

class BearingTest {

    @Test
    fun testBearing() {
        val start = Position(-75.0, 45.0)
        val end = Position(20.0, 60.0)

        assertBearingEquals(
            North + 37.75495.degrees,
            start.bearingTo(end),
            message = "Initial Bearing",
        )
        assertBearingEquals(
            North + 120.01405.degrees,
            start.bearingTo(end, final = true),
            message = "Final Bearing",
        )
    }

    @Test
    fun testRhumbBearing() {
        val start = Position(-75.0, 45.0)
        val end = Position(20.0, 60.0)
        assertBearingEquals(North + 75.28061364784332.degrees, start.rhumbBearingTo(end))
    }
}
