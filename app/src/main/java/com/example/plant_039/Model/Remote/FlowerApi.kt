package com.example.plant_039.Model.Remote

import com.example.plant_039.Model.Remote.FromInternet.ListFlowers
import com.example.plant_039.Model.Remote.FromInternet.DetailsFlower
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path


interface FlowerApi {
    //https://caso-music-mariocanedo.vercel.app/musicStore/
    @GET("musicStore")
    suspend fun fetchFlowersList(): Response<List<ListFlowers>>


    // seleccionar uno

    @GET("musicStore/{id}")
    suspend fun fetchFlowersDetail(@Path("id") id: Int): Response<DetailsFlower>
}