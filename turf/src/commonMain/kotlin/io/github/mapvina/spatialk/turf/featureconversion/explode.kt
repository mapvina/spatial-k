@file:JvmName("FeatureConversion")
@file:JvmMultifileClass

package io.github.mapvina.spatialk.turf.featureconversion

import kotlin.jvm.JvmMultifileClass
import kotlin.jvm.JvmName
import io.github.mapvina.spatialk.geojson.GeoJsonObject
import io.github.mapvina.spatialk.geojson.GeometryCollection
import io.github.mapvina.spatialk.geojson.MultiPoint
import io.github.mapvina.spatialk.turf.coordinatemutation.flattenCoordinates

/** @return a [GeometryCollection] with all coordinates of the input object as `Point` features */
public fun GeoJsonObject.explode(): MultiPoint = MultiPoint(flattenCoordinates())
