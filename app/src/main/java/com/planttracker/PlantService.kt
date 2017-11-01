package com.planttracker

import io.reactivex.Observable
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface PlantService {

    @GET("plants")
    fun getPlants(): Observable<List<Model.Plant>>

    companion object {
        fun create (): PlantService {
            val retrofit = Retrofit.Builder()
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl("https://plant-tracker.cfapps.io/")
                    .build()

            return retrofit.create(PlantService::class.java)


        }
    }
}
