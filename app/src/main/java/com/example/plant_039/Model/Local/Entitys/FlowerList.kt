package com.example.plant_039.Model.Local.Entitys

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "List_Flowers")
data class FlowerList(
    @PrimaryKey
    val id: Int,
    val nombre: String,
    val origen: String,
    val imagenLink: String,
    val descripcion: String,
    val precio : Float,
    val manual : Boolean
)