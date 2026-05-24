package com.mapvina.spatialk.testutil

import kotlin.math.abs
import kotlin.test.assertEquals
import kotlin.test.asserter
import com.mapvina.spatialk.geojson.LineString
import com.mapvina.spatialk.geojson.Position
import com.mapvina.spatialk.units.Bearing
import com.mapvina.spatialk.units.Bearing.Companion.North
import com.mapvina.spatialk.units.Rotation
import com.mapvina.spatialk.units.extensions.degrees
import com.mapvina.spatialk.units.extensions.inDegrees

fun assertDoubleEquals(
    expected: Double,
    actual: Double?,
    epsilon: Double = 0.0001,
    message: String? = null,
) {
    asserter.assertNotNull(null, actual)
    asserter.assertTrue(
        {
            (message ?: "") +
                "Expected <$expected>, actual <$actual>, should differ no more than <$epsilon>."
        },
        abs(expected - actual!!) <= epsilon,
    )
}

fun assertRotationEquals(
    expected: Rotation,
    actual: Rotation?,
    epsilon: Rotation = 0.0001.degrees,
    message: String? = null,
) {
    asserter.assertNotNull(null, actual)

    assertDoubleEquals(expected.inDegrees, actual?.inDegrees, epsilon.inDegrees, message)
    assertDoubleEquals(expected.inDegrees, actual?.inDegrees, epsilon.inDegrees, message)
}

fun assertBearingEquals(
    expected: Bearing,
    actual: Bearing?,
    epsilon: Rotation = 0.0001.degrees,
    message: String? = null,
) {
    asserter.assertNotNull(null, actual)

    assertRotationEquals((expected - North), actual?.minus(North), epsilon, message)
    assertRotationEquals((expected - North), actual?.minus(North), epsilon, message)
}

fun assertPositionEquals(
    expected: Position,
    actual: Position?,
    epsilon: Double = 0.0001,
    message: String? = null,
) {
    asserter.assertNotNull(null, actual)

    assertDoubleEquals(expected.latitude, actual?.latitude, epsilon, message)
    assertDoubleEquals(expected.longitude, actual?.longitude, epsilon, message)
}

fun assertLineStringEquals(
    expected: LineString,
    actual: LineString,
    epsilon: Double = 0.0001,
    message: String? = null,
) {
    assertEquals(expected.coordinates.size, actual.coordinates.size, "Coordinate count mismatch")
    expected.coordinates.forEachIndexed { index, expectedPos ->
        val actualPos = actual.coordinates[index]
        assertPositionEquals(expectedPos, actualPos, epsilon, (message ?: "") + " at index $index")
    }
}
