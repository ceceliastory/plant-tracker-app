package com.planttracker.plants

import io.reactivex.Observable
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface PlantApiClient {

    @GET("plants")
    fun getPlants(): Observable<List<Plant>>

    @POST("plant")
    fun addPlant(@Body plant: Plant)

    companion object {
        fun create (): PlantApiClient {
            val retrofit = Retrofit.Builder()
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl("https://plant-tracker.cfapps.io/")
                    .build()

            return retrofit.create(PlantApiClient::class.java)
        }
    }
}
