package com.example.marvelstaff.models

import com.google.gson.JsonElement
import com.google.gson.JsonObject

interface BaseDeserializer {

    fun JsonElement.getJsonObject(memberName: String): JsonObject? {
        return try {
            this.asJsonObject.get(memberName).asJsonObject
        } catch (th: Throwable) {
            null
        }
    }

    fun JsonElement.getInt(memberName: String): Int? {
        return try {
            this.asJsonObject.get(memberName).asInt
        } catch (th: Throwable) {
            null
        }
    }

    fun JsonElement.getString(memberName: String): String? {
        return try {
            this.asJsonObject.get(memberName).asString
        } catch (th: Throwable) {
            null
        }
    }
}