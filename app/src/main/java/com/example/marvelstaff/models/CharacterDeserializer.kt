package com.example.marvelstaff.models

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import java.lang.reflect.Type

class CharacterDeserializer : JsonDeserializer<Character>, BaseDeserializer {
    override fun deserialize(
        json: JsonElement?,
        typeOfT: Type?,
        context: JsonDeserializationContext?
    ): Character {
        var character = Character()
        json?.asJsonObject?.getJsonObject("data")?.getJsonArrayElement("results")?.let {
            character = Character(
                it.getInt("id"),
                it.getString("name"),
                it.getString("description")
            )
        }
        return character
    }
}