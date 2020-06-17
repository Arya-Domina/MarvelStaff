package com.example.marvelstaff.rest

import com.example.marvelstaff.models.Character
import com.example.marvelstaff.models.CharactersList
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import java.lang.reflect.Type

class CharactersListDeserializer : JsonDeserializer<CharactersList>, BaseDeserializer {
    override fun deserialize(
        json: JsonElement?,
        typeOfT: Type?,
        context: JsonDeserializationContext?
    ): CharactersList {
        val characters = CharactersList(arrayListOf())
        json?.getJsonObject("data")?.getAsJsonArray("results")?.let { jsonArray ->
            jsonArray.forEach {
                characters.list.add(
                    Character(
                        it.getInt("id"),
                        it.getString("name"),
                        it.getString("description"),
                        it.getJsonObject("comics")?.getInt("available"),
                        it.getJsonObject("stories")?.getInt("available"),
                        it.getJsonObject("events")?.getInt("available"),
                        it.getJsonObject("series")?.getInt("available"),
                        it.getJsonObject("thumbnail")?.getString("path")
                                + "."
                                + it.getJsonObject("thumbnail")?.getString("extension")
                    )
                )
            }
        }
        return characters
    }
}