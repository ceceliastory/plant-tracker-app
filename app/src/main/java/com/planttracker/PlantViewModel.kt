package com.planttracker

import io.reactivex.Observable
import javax.inject.Inject

class PlantViewModel @Inject constructor(private val plantRepository: PlantRepository) {

    fun getAllPlants(): Observable<List<Plant>> {
        return plantRepository.getAllPlants()
    }
}