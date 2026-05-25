package io.github.mapvina.spatialk.turf.misc

import kotlin.test.Test
import kotlinx.serialization.json.JsonObject
import io.github.mapvina.spatialk.geojson.FeatureCollection
import io.github.mapvina.spatialk.geojson.Geometry
import io.github.mapvina.spatialk.geojson.LineString
import io.github.mapvina.spatialk.geojson.Position
import io.github.mapvina.spatialk.testutil.assertPositionEquals
import io.github.mapvina.spatialk.testutil.readResourceFile

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
