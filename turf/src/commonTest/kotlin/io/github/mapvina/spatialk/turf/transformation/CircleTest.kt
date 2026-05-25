package io.github.mapvina.spatialk.turf.transformation

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.double
import kotlinx.serialization.json.jsonPrimitive
import io.github.mapvina.spatialk.geojson.Feature
import io.github.mapvina.spatialk.geojson.FeatureCollection
import io.github.mapvina.spatialk.geojson.Geometry
import io.github.mapvina.spatialk.geojson.Point
import io.github.mapvina.spatialk.testutil.assertPositionEquals
import io.github.mapvina.spatialk.testutil.readResourceFile
import io.github.mapvina.spatialk.turf.coordinatemutation.flattenCoordinates
import io.github.mapvina.spatialk.units.Length
import io.github.mapvina.spatialk.units.extensions.kilometers

class CircleTest {

    @Test
    fun testCircle() {
        val pointFeature =
            Feature.fromJson<Point, JsonObject?>(
                readResourceFile("transformation/circle/in/circle1.json")
            )
        val expectedOut =
            FeatureCollection.fromJson<Geometry?, JsonObject?>(
                readResourceFile("transformation/circle/out/circle1.json")
            )

        val (_, expectedCircle) = expectedOut.features

        val circle =
            circle(
                center = pointFeature.geometry.coordinates,
                radius =
                    pointFeature.properties?.get("radius")?.jsonPrimitive?.double?.kilometers
                        ?: Length.Zero,
            )

        val allCoordinates = expectedCircle.geometry?.flattenCoordinates().orEmpty()
        assertTrue(allCoordinates.isNotEmpty())
        assertEquals(allCoordinates.size, circle.flattenCoordinates().size)
        allCoordinates.forEachIndexed { i, position ->
            assertPositionEquals(position, circle.flattenCoordinates()[i])
        }
    }
}
