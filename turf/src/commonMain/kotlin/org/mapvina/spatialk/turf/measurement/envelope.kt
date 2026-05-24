@file:JvmName("Measurement")
@file:JvmMultifileClass

package com.mapvina.spatialk.turf.measurement

import kotlin.jvm.JvmMultifileClass
import kotlin.jvm.JvmName
import com.mapvina.spatialk.geojson.Geometry
import com.mapvina.spatialk.geojson.Polygon

/**
 * Takes a [Geometry] and returns a rectangular [Polygon] that encompasses all vertices.
 *
 * @return a rectangular [Polygon] that encompasses all vertices
 */
public fun Geometry.envelope(): Polygon = (bbox ?: this.computeBbox()).toPolygon()
