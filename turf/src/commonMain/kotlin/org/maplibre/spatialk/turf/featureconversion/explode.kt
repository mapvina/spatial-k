@file:JvmName("FeatureConversion")
@file:JvmMultifileClass

package com.mapvina.spatialk.turf.featureconversion

import kotlin.jvm.JvmMultifileClass
import kotlin.jvm.JvmName
import com.mapvina.spatialk.geojson.GeoJsonObject
import com.mapvina.spatialk.geojson.GeometryCollection
import com.mapvina.spatialk.geojson.MultiPoint
import com.mapvina.spatialk.turf.coordinatemutation.flattenCoordinates

/** @return a [GeometryCollection] with all coordinates of the input object as `Point` features */
public fun GeoJsonObject.explode(): MultiPoint = MultiPoint(flattenCoordinates())
