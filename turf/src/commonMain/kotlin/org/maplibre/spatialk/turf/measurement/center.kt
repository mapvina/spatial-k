@file:JvmName("Measurement")
@file:JvmMultifileClass

package com.mapvina.spatialk.turf.measurement

import kotlin.jvm.JvmMultifileClass
import kotlin.jvm.JvmName
import com.mapvina.spatialk.geojson.BoundingBox
import com.mapvina.spatialk.geojson.Point

/** @return The [Point] at the center of the bounding box. */
public fun BoundingBox.center(): Point {
    val x = (southwest.longitude + northeast.longitude) / 2
    val y = (southwest.latitude + northeast.latitude) / 2
    return Point(longitude = x, latitude = y)
}
