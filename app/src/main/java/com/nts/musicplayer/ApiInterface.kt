package com.nts.musicplayer

import com.nts.musicplayer.data.MyData
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface ApiInterface  {

    @Headers("X-RapidAPI-Key: 013a4bb8f2mshfc8ef92a71480d3p1db917jsn172a1db2a88a" ,
             "X-RapidAPI-Host: deezerdevs-deezer.p.rapidapi.com")
    @GET("search")
    fun getData(@Query("q") query: String) : Call<MyData>

}