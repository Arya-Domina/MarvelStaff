package com.example.marvelstaff.rest

import com.example.marvelstaff.models.Comic
import com.example.marvelstaff.models.ComicsList
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import java.lang.reflect.Type

class ComicsListDeserializer : JsonDeserializer<ComicsList>, BaseDeserializer {
    override fun deserialize(
        json: JsonElement?,
        typeOfT: Type?,
        context: JsonDeserializationContext?
    ): ComicsList {
        val characters = ComicsList(arrayListOf())
        json?.getJsonObject("data")?.getAsJsonArray("results")?.let { jsonArray ->
            jsonArray.forEach {
                characters.list.add(
                    Comic(
                        it.getInt("id"),
                        it.getString("title"),
                        it.getString("description"),
                        it.getString("format"),
                        it.getInt("pageCount"),
                        it.getJsonObject("series")?.getString("name"),
                        it.getJsonObject("series")?.getString("resourceURI"),
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