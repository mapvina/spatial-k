package io.github.mapvina.spatialk.geojson.serialization

import kotlin.collections.get
import kotlin.reflect.KClass
import kotlinx.serialization.DeserializationStrategy
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.MissingFieldException
import kotlinx.serialization.SerializationException
import kotlinx.serialization.builtins.nullable
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonContentPolymorphicSerializer
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.decodeFromJsonElement
import kotlinx.serialization.json.jsonPrimitive
import io.github.mapvina.spatialk.geojson.Feature
import io.github.mapvina.spatialk.geojson.FeatureCollection
import io.github.mapvina.spatialk.geojson.GeoJsonObject
import io.github.mapvina.spatialk.geojson.Geometry
import io.github.mapvina.spatialk.geojson.GeometryCollection
import io.github.mapvina.spatialk.geojson.LineString
import io.github.mapvina.spatialk.geojson.MultiLineString
import io.github.mapvina.spatialk.geojson.MultiPoint
import io.github.mapvina.spatialk.geojson.MultiPolygon
import io.github.mapvina.spatialk.geojson.Point
import io.github.mapvina.spatialk.geojson.Polygon

internal abstract class GeoJsonPolymorphicSerializer<T : GeoJsonObject>(
    baseClass: KClass<T>,
    private val allowedTypes: Set<String>,
) : JsonContentPolymorphicSerializer<T>(baseClass) {

    private val allowedSerializers by lazy { allSerializers.filter { it.key in allowedTypes } }

    @OptIn(ExperimentalSerializationApi::class)
    override fun selectDeserializer(element: JsonElement): DeserializationStrategy<T> {
        element as? JsonObject ?: throw SerializationException("Expected JSON object")
        val type =
            element["type"]?.let { Json.decodeFromJsonElement<String>(it.jsonPrimitive) }
                ?: throw MissingFieldException("type", "GeoJsonObject")
        val actualSerializer =
            allowedSerializers[type]
                ?: throw SerializationException(
                    "Unexpected type $type; expected one of: ${allowedTypes.joinToString()}"
                )
        @Suppress("UNCHECKED_CAST")
        return actualSerializer as DeserializationStrategy<T>
    }

    internal companion object {
        val allSerializers by lazy {
            mapOf(
                "Point" to Point.serializer(),
                "MultiPoint" to MultiPoint.serializer(),
                "LineString" to LineString.serializer(),
                "MultiLineString" to MultiLineString.serializer(),
                "Polygon" to Polygon.serializer(),
                "MultiPolygon" to MultiPolygon.serializer(),
                "GeometryCollection" to GeometryCollection.serializer(Geometry.serializer()),
                "Feature" to
                    Feature.serializer(
                        Geometry.serializer().nullable,
                        JsonObject.serializer().nullable,
                    ),
                "FeatureCollection" to
                    FeatureCollection.serializer(
                        Geometry.serializer().nullable,
                        JsonObject.serializer().nullable,
                    ),
            )
        }
    }
}
