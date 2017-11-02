package com.planttracker

import io.reactivex.Observable
import javax.inject.Inject

class PlantRepository @Inject constructor(private val plantApiClient: PlantApiClient) {

    fun getAllPlants() : Observable<List<Plant>> {
        return plantApiClient.getPlants()
    }
}