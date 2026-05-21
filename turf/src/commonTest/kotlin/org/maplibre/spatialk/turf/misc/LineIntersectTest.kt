package com.mapvina.spatialk.turf.misc

import kotlin.test.Test
import kotlinx.serialization.json.JsonObject
import com.mapvina.spatialk.geojson.FeatureCollection
import com.mapvina.spatialk.geojson.Geometry
import com.mapvina.spatialk.geojson.LineString
import com.mapvina.spatialk.geojson.Position
import com.mapvina.spatialk.testutil.assertPositionEquals
import com.mapvina.spatialk.testutil.readResourceFile

class LineIntersectTest {

    @Test
    fun testLineIntersect() {
        val features =
            FeatureCollection.fromJson<Geometry?, JsonObject?>(
                readResourceFile("misc/lineIntersect/twoPoints.json")
            )
        val intersect =
            intersect(
                features.features[0].geometry as LineString,
                features.features[1].geometry as LineString,
            )

        assertPositionEquals(
            Position(-120.93653884065287, 51.287945374086675),
            intersect!![0].coordinates,
        )
    }
}
