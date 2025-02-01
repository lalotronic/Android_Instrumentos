package com.example.plant_039.Model.Remote.FromInternet

import com.google.gson.annotations.SerializedName

data class ListFlowers(

    val id: Int,
    val nombre :String,
    val origen :String,
    val imagenLink: String,
    val descripcion :String,
    @SerializedName("Año_creacion")
    val anioCreacion: Int,
    val precio: Int,
    val manual: Boolean
)