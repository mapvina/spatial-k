package io.github.mapvina.spatialk.gpx

import kotlin.test.Test
import kotlin.test.assertEquals
import io.github.mapvina.spatialk.geojson.FeatureCollection
import io.github.mapvina.spatialk.geojson.Point
import io.github.mapvina.spatialk.geojson.SensitiveGeoJsonApi
import io.github.mapvina.spatialk.testutil.readResourceFile

class GpxTest {
    @OptIn(SensitiveGeoJsonApi::class)
    @Test
    fun testWaypoints() {
        val document = Gpx.decodeFromString(readResourceFile("in/waypoints.gpx"))
        assertEquals(3, document.waypoints.size)
        assertEquals("WPT001", document.waypoints[0].name)

        assertEquals(
            FeatureCollection.fromJson<Point, Waypoint>(readResourceFile("out/waypoints.json")),
            document.waypoints.toGeoJson(),
        )

        assertEncodedEquals(readResourceFile("out/waypoints.gpx"), document)
    }

    @Test
    fun testTrack() {
        assertEncodedEquals(
            readResourceFile("out/track.gpx"),
            Gpx.decodeFromString(readResourceFile("in/track.gpx")),
        )

        assertEncodedEquals(
            readResourceFile("out/track.gpx"),
            Gpx.decodeFromString(readResourceFile("in/track_lenient.gpx"))
                .copy(creator = "ChatGPT-GPX"),
        )
    }

    @Test
    fun testRoute() {
        val document = Gpx.decodeFromString(readResourceFile("in/route.gpx"))
        assertEncodedEquals(readResourceFile("out/route.gpx"), document)
    }

    fun assertEncodedEquals(expected: String, actual: Document) {
        val expected = expected.replace(Regex("\\s+"), "")
        val actual = Gpx.encodeToString(actual).replace(Regex("\\s+"), "")

        // dirty hack for different number formatting on nodeJs platform
        if (!actual.contains(".0<")) {
            assertEquals(expected.replace(".0<", "<"), actual)
        } else {
            assertEquals(expected, actual)
        }
    }
}
