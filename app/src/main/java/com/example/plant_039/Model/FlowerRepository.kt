package com.example.plant_039.Model

import android.util.Log
import androidx.lifecycle.LiveData
import com.example.plant_039.Model.Local.Dao.FlowerDao
import com.example.plant_039.Model.Local.Entitys.FlowerDetails
import com.example.plant_039.Model.Local.fromInternetDetailsFlowers
import com.example.plant_039.Model.Local.fromInternetListFlowers

class FlowerRepository(private val flowerDao: FlowerDao) {

    private val networkService = RetrofitFlower.retrofitInstance()

    val flowerListLiveData = flowerDao.getAllFlowers()


    suspend fun fetchList() {
        val service = kotlin.runCatching { networkService.fetchFlowersList() }

        service.onSuccess {
            when (it.code()) {
                in 200..299 -> it.body()?.let {

                    Log.d("Flowers", it.toString())

                    flowerDao.insertAllFlowers(fromInternetListFlowers(it))

                }

                else -> Log.d("Repo", "${it.code()}-${it.errorBody()}")
            }
            service.onFailure {
                Log.e("Error", "${it.message}")
            }

        }

    }


    suspend fun fetchFlowerDetails(id: Int): FlowerDetails? {

        val service = kotlin.runCatching { networkService.fetchFlowersDetail(id) }

        return service.getOrNull()?.body()?.let { Details ->

            val flowersDetails = fromInternetDetailsFlowers(Details)

            flowerDao.insertFlowersDetail(flowersDetails)
            flowersDetails
        }
    }

    fun getFlowersDetailsById(id: Int): LiveData<FlowerDetails> {
        return flowerDao.getFlowersDetailById(id)
    }


}