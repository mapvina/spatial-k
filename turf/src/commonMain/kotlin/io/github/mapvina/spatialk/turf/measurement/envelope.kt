@file:JvmName("Measurement")
@file:JvmMultifileClass

package io.github.mapvina.spatialk.turf.measurement

import kotlin.jvm.JvmMultifileClass
import kotlin.jvm.JvmName
import io.github.mapvina.spatialk.geojson.Geometry
import io.github.mapvina.spatialk.geojson.Polygon

/**
 * Takes a [Geometry] and returns a rectangular [Polygon] that encompasses all vertices.
 *
 * @return a rectangular [Polygon] that encompasses all vertices
 */
public fun Geometry.envelope(): Polygon = (bbox ?: this.computeBbox()).toPolygon()
