package io.github.mapvina.spatialk.turf.grids

import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals
import io.github.mapvina.spatialk.geojson.BoundingBox
import io.github.mapvina.spatialk.geojson.MultiPolygon
import io.github.mapvina.spatialk.geojson.Polygon
import io.github.mapvina.spatialk.geojson.Position
import io.github.mapvina.spatialk.testutil.assertPositionEquals
import io.github.mapvina.spatialk.testutil.readResourceFile
import io.github.mapvina.spatialk.turf.coordinatemutation.flattenCoordinates
import io.github.mapvina.spatialk.turf.measurement.computeBbox
import io.github.mapvina.spatialk.units.extensions.kilometers
import io.github.mapvina.spatialk.units.extensions.meters

class SquareGridTest {

    private lateinit var box: BoundingBox

    @BeforeTest
    fun before() {
        Polygon.fromJson(readResourceFile("grids/bbox.json")).also {
            box = computeBbox(it.coordinates[0])
        }
    }

    @Test
    fun testSquareGrid() {
        squareGrid(bbox = box, cellWidth = 200.meters, cellHeight = 200.meters).also {
            verifyValidGrid(it)
        }
    }

    private fun verifyValidGrid(grid: MultiPolygon) {
        assertEquals(16, grid.size)

        val expectedFirstItem =
            mutableListOf(
                Position(13.170147683370761, 52.515969323342695),
                Position(13.170147683370761, 52.517765865),
                Position(13.17194422502807, 52.517765865),
                Position(13.17194422502807, 52.515969323342695),
                Position(13.170147683370761, 52.515969323342695),
            )
        val actualFirstItem = grid.first().flattenCoordinates()

        assertEquals(expectedFirstItem.size, actualFirstItem.size)
        expectedFirstItem.forEachIndexed { index, _ ->
            assertPositionEquals(expectedFirstItem[index], actualFirstItem[index])
        }

        val expectedLastItem =
            mutableListOf(
                Position(13.18272347497193, 52.517765865),
                Position(13.18272347497193, 52.51956240665731),
                Position(13.18452001662924, 52.51956240665731),
                Position(13.18452001662924, 52.517765865),
                Position(13.18272347497193, 52.517765865),
            )
        val actualLastItem = grid.last().flattenCoordinates()

        assertEquals(expectedLastItem.size, actualLastItem.size)
        expectedFirstItem.forEachIndexed { index, _ ->
            assertPositionEquals(expectedLastItem[index], actualLastItem[index])
        }
    }

    @Test
    fun cellSizeBiggerThanBboxExtendLeadIntoEmptyGrid() {
        squareGrid(bbox = box, cellWidth = 2000.meters, cellHeight = 2000.meters).also {
            assertEquals(0, it.size)
        }
    }

    @Test
    fun smallerCellSizeWillOutputMoreCellsInGrid() {
        squareGrid(bbox = box, cellWidth = 0.1.kilometers, cellHeight = 0.1.kilometers).also {
            assertEquals(85, it.size)
        }
    }

    @Test
    fun increasedCellSizeWillOutputLessCellsInGrid() {
        squareGrid(bbox = box, cellWidth = 0.3.kilometers, cellHeight = 0.3.kilometers).also {
            assertEquals(5, it.size)
        }
    }
}
