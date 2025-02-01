package com.example.plant_039.Model.Remote.FromInternet

data class ListFlowers(

    /*val id: Int,
    val nombre: String,
    val tipo: String,
    val imagen: String,
    val descripcion: String*/
    val id: Int,
    val nombre :String,
    val origen :String,
    val imagenLink: String,
    val descripcion :String,
    val precio: Float,
    val manual: Boolean
)