package com.example.marvelstaff.models

import com.google.gson.JsonObject

interface BaseDeserializer {

    fun JsonObject.getJsonArrayElement(memberName: String): JsonObject? {
        return try {
            this.get(memberName).asJsonArray[0].asJsonObject
        } catch (th: Throwable) {
            null
        }
    }

    fun JsonObject.getJsonObject(memberName: String): JsonObject? {
        return try {
            this.get(memberName).asJsonObject
        } catch (th: Throwable) {
            null
        }
    }

    fun JsonObject.getInt(memberName: String): Int? {
        return try {
            this.get(memberName).asInt
        } catch (th: Throwable) {
            null
        }
    }

    fun JsonObject.getString(memberName: String): String? {
        return try {
            this.get(memberName).asString
        } catch (th: Throwable) {
            null
        }
    }
}