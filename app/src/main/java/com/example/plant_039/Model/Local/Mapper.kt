package com.example.plant_039.Model.Local

import com.example.plant_039.Model.Local.Entitys.FlowerDetails
import com.example.plant_039.Model.Local.Entitys.FlowerList
import com.example.plant_039.Model.Remote.FromInternet.DetailsFlower
import com.example.plant_039.Model.Remote.FromInternet.ListFlowers

fun fromInternetListFlowers(flowerList: List<ListFlowers>): List<FlowerList> {

    return flowerList.map {
        FlowerList(
            id = it.id,
            nombre = it.nombre,
            origen = it.origen,
            imagenLink = it.imagenLink,
            descripcion = it.descripcion,
            anioCreacion = it.anioCreacion,
            precio = it.precio,
            manual = it.manual
        )
    }
}


fun fromInternetDetailsFlowers(detailsFlower: DetailsFlower): FlowerDetails {

    return FlowerDetails(
        id = detailsFlower.id,
        nombre = detailsFlower.nombre,
        origen = detailsFlower.origen,
        imagenLink = detailsFlower.imagenLink,
        descripcion = detailsFlower.descripcion,
        anioCreacion = detailsFlower.anioCreacion,
        precio = detailsFlower.precio,
        manual = detailsFlower.manual
    )
}