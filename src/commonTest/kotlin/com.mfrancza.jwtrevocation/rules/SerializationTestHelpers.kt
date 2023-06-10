package com.mfrancza.jwtrevocation.rules

import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import kotlin.test.assertEquals

/**
 * Tests that the provided sample objects can be serialized and deserialized wihtou
 */
inline fun <reified T>canBeSerializedAndDeserialized(sampleObjects: List<T>) {
    sampleObjects.forEach {
        val jsonString = Json.Default.encodeToString(it)
        val deserializedObject = Json.decodeFromString<T>(jsonString)
        assertEquals(it, deserializedObject, "The deserialized object should have the same values as the original object")
    }
}