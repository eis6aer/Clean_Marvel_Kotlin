package com.puzzlebench.cmk.data.service.api

import com.puzzlebench.cmk.data.service.response.CharacterResponse
import com.puzzlebench.cmk.data.service.response.DataBaseResponse
import com.puzzlebench.cmk.data.service.response.MarvelBaseResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import java.util.*

interface MarvelApi {
    @GET("/v1/public/characters")
    fun getCharacter(): Call<MarvelBaseResponse<DataBaseResponse<ArrayList<CharacterResponse>>>>

    @GET("/v1/public/characters/{characterId}")
    fun getCharacterDetails(@Path("characterId") characterId : Int): Call<MarvelBaseResponse<DataBaseResponse<CharacterResponse>>>
}