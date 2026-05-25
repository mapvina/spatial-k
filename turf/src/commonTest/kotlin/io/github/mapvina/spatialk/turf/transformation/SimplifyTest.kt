package io.github.mapvina.spatialk.turf.transformation

import kotlin.math.roundToInt
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlinx.serialization.json.JsonObject
import io.github.mapvina.spatialk.geojson.Feature
import io.github.mapvina.spatialk.geojson.LineString
import io.github.mapvina.spatialk.geojson.Position
import io.github.mapvina.spatialk.testutil.readResourceFile

class SimplifyTest {

    @Test
    fun testSimplifyLineString() {
        val feature =
            Feature.fromJson<LineString, JsonObject?>(
                readResourceFile("transformation/simplify/in/linestring.json")
            )
        val expected =
            Feature.fromJson<LineString, JsonObject?>(
                readResourceFile("transformation/simplify/out/linestring.json")
            )
        val simplified = feature.geometry.simplify(0.01)
        val roundedSimplified =
            LineString(
                simplified.coordinates.map { position ->
                    Position(
                        (position.longitude * 1000000).roundToInt() / 1000000.0,
                        (position.latitude * 1000000).roundToInt() / 1000000.0,
                    )
                }
            )
        assertEquals(expected.geometry, roundedSimplified)
    }
}
