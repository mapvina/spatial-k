package com.mapvina.spatialk.turf.transformation

import kotlin.test.Test
import kotlinx.serialization.json.JsonObject
import com.mapvina.spatialk.geojson.Feature
import com.mapvina.spatialk.geojson.LineString
import com.mapvina.spatialk.testutil.assertLineStringEquals
import com.mapvina.spatialk.testutil.readResourceFile

class BezierSplineTest {

    @Test
    fun testBezierSplineIn() {
        val feature =
            Feature.fromJson<LineString, JsonObject?>(
                readResourceFile("transformation/bezierspline/in/bezierIn.json")
            )
        val expectedOut =
            Feature.fromJson<LineString, JsonObject?>(
                readResourceFile("transformation/bezierspline/out/bezierIn.json")
            )

        assertLineStringEquals(expectedOut.geometry, feature.geometry.bezierSpline())
    }

    @Test
    fun testBezierSplineSimple() {
        val feature =
            Feature.fromJson<LineString, JsonObject?>(
                readResourceFile("transformation/bezierspline/in/simple.json")
            )
        val expectedOut =
            Feature.fromJson<LineString, JsonObject?>(
                readResourceFile("transformation/bezierspline/out/simple.json")
            )

        assertLineStringEquals(expectedOut.geometry, feature.geometry.bezierSpline())
    }

    /**
     * This test is designed to draw a bezierSpline across the 180th Meridian
     *
     * @see <a href="https://github.com/Turfjs/turf/issues/1063">
     */
    @Test
    fun testBezierSplineAcrossPacific() {
        val feature =
            Feature.fromJson<LineString, JsonObject?>(
                readResourceFile("transformation/bezierspline/in/issue-#1063.json")
            )
        val expectedOut =
            Feature.fromJson<LineString, JsonObject?>(
                readResourceFile("transformation/bezierspline/out/issue-#1063.json")
            )

        assertLineStringEquals(expectedOut.geometry, feature.geometry.bezierSpline())
    }
}
