package com.mapvina.spatialk.turf.measurement

import kotlin.test.Test
import com.mapvina.spatialk.geojson.LineString
import com.mapvina.spatialk.geojson.Position
import com.mapvina.spatialk.testutil.assertDoubleEquals
import com.mapvina.spatialk.units.extensions.inKilometers

class PointToLineDistanceTest {

    @Test
    fun testPointToLineDistance() {
        val point = Position(-0.54931640625, 0.7470491450051796)
        val line =
            LineString(
                Position(1.0, 3.0),
                Position(2.0, 2.0),
                Position(2.0, 0.0),
                Position(-1.5, -1.5),
            )

        val distance = distance(point, line)
        assertDoubleEquals(188.01568693725255, distance.inKilometers, 0.000001)
    }
}
