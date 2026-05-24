package com.mapvina.spatialk.turf.misc

import kotlin.test.Test
import kotlinx.serialization.json.JsonObject
import com.mapvina.spatialk.geojson.FeatureCollection
import com.mapvina.spatialk.geojson.Geometry
import com.mapvina.spatialk.geojson.LineString
import com.mapvina.spatialk.geojson.Point
import com.mapvina.spatialk.testutil.assertPositionEquals
import com.mapvina.spatialk.testutil.readResourceFile

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
