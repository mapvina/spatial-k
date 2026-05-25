@file:JvmName("FeatureConversion")
@file:JvmMultifileClass

package io.github.mapvina.spatialk.turf.featureconversion

import kotlin.jvm.JvmMultifileClass
import kotlin.jvm.JvmName
import io.github.mapvina.spatialk.geojson.Feature
import io.github.mapvina.spatialk.geojson.FeatureCollection
import io.github.mapvina.spatialk.geojson.Geometry

/**
 * Transforms the properties of a [Feature] using the provided transform function.
 *
 * @param transform Function that converts properties from type [P1] to type [P2].
 * @return A new [Feature] with the same geometry and transformed properties.
 */
public fun <G : Geometry?, P1, P2> Feature<G, P1>.mapProperties(
    transform: (P1) -> P2
): Feature<G, P2> =
    Feature(geometry = geometry, properties = transform(properties), id = id, bbox = bbox)

/**
 * Transforms the properties of all features in a [FeatureCollection] using the provided transform
 * function.
 *
 * @param transform Function that converts properties from type [P1] to type [P2].
 * @return A new [FeatureCollection] with the same geometries and transformed properties.
 */
public fun <G : Geometry?, P1, P2> FeatureCollection<G, P1>.mapProperties(
    transform: (P1) -> P2
): FeatureCollection<G, P2> =
    FeatureCollection(features = features.map { it.mapProperties(transform) }, bbox = bbox)
