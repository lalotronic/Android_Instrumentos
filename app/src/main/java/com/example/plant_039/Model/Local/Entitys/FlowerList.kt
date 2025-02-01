package com.example.plant_039.Model.Local.Entitys

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "List_Flowers")
data class FlowerList(
    @PrimaryKey
    val id: Int,
    val nombre: String,
    val origen: String,
    val imagenLink: String,
    val descripcion: String,
    @SerializedName("Año_creacion")
    val anioCreacion: Int,
    val precio : Int,
    val manual: Boolean

)