package com.planttracker.plants

import io.reactivex.Observable
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class PlantRepository @Inject constructor(private val plantApiClient: PlantApiClient) {

    fun getAllPlants(): Observable<List<Plant>> {
        return plantApiClient.getPlants()
    }

    fun addPlant(plant: Plant) {
        plantApiClient.addPlant(plant).enqueue(object : Callback<Void> {
            override fun onResponse(call: Call<Void>?, response: Response<Void>?) {
                println("YOU DID IT")
            }

            override fun onFailure(call: Call<Void>?, t: Throwable?) {
                println("OH NO")
            }
        })
    }
}