package com.mapvina.spatialk.units

import kotlin.test.Test
import kotlin.test.assertEquals
import com.mapvina.spatialk.testutil.assertDoubleEquals
import com.mapvina.spatialk.units.extensions.*

class AreaTests {
    @Test
    fun testConvertArea() {
        assertDoubleEquals(0.386102, 1.squareKilometers.inSquareMiles)
        assertDoubleEquals(2.58999, 1.squareMiles.inSquareKilometers)
        assertDoubleEquals(10000.0, 1.squareMeters.inSquareCentimeters)
        assertDoubleEquals(0.0247105, 100.squareMeters.inAcres)
        assertDoubleEquals(119.599, 100.squareMeters.inSquareYards)
        assertDoubleEquals(1076.391, 100.squareMeters.inSquareFeet)
        assertDoubleEquals(0.0092903, 100000.squareFeet.inSquareKilometers)
    }

    @Test
    fun testSumArea() {
        val areas = listOf(2.squareMeters, 3.squareMeters)
        assertEquals(5.squareMeters, areas.sum())
        assertEquals(5.squareMeters, areas.sumOf { it })
    }
}
