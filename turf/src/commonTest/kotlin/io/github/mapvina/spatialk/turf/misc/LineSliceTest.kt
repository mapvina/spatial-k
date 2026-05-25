package io.github.mapvina.spatialk.turf.misc

import kotlin.test.Test
import kotlinx.serialization.json.JsonObject
import io.github.mapvina.spatialk.geojson.FeatureCollection
import io.github.mapvina.spatialk.geojson.Geometry
import io.github.mapvina.spatialk.geojson.LineString
import io.github.mapvina.spatialk.geojson.Point
import io.github.mapvina.spatialk.testutil.assertPositionEquals
import io.github.mapvina.spatialk.testutil.readResourceFile

class LineSliceTest {

    @Test
    fun testLineSlice() {
        val features =
            FeatureCollection.fromJson<Geometry?, JsonObject?>(
                readResourceFile("misc/lineSlice/route.json")
            )
        val slice = LineString.fromJson(readResourceFile("misc/lineSlice/slice.json"))

        val (lineString, start, stop) = features.features

        val result =
            (lineString.geometry as LineString).slice(
                (start.geometry as Point).coordinates,
                (stop.geometry as Point).coordinates,
            )
        slice.coordinates.forEachIndexed { i, position ->
            assertPositionEquals(position, result.coordinates[i])
        }
    }
}
