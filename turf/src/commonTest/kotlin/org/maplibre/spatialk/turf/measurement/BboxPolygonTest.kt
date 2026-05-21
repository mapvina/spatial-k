package com.mapvina.spatialk.turf.measurement

import kotlin.test.Test
import kotlin.test.assertEquals
import com.mapvina.spatialk.geojson.BoundingBox
import com.mapvina.spatialk.geojson.Position
import com.mapvina.spatialk.geojson.dsl.addRing
import com.mapvina.spatialk.geojson.dsl.buildPolygon

class BboxPolygonTest {
    @Test
    fun testBboxPolygon() {
        val bbox = BoundingBox(12.1, 34.3, 56.5, 78.7)

        val polygon = buildPolygon {
            addRing {
                add(Position(12.1, 34.3))
                add(Position(56.5, 34.3))
                add(Position(56.5, 78.7))
                add(Position(12.1, 78.7))
            }
            this.bbox = bbox
        }

        assertEquals(polygon, bbox.toPolygon())
    }
}
