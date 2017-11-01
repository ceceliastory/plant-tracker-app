package com.planttracker

import io.reactivex.Observable

class PlantRepository(val plantService: PlantService) {

    fun getAllPlants() : Observable<List<Model.Plant>> {
        return plantService.getPlants()
    }
}

object PlantRepositoryProvider {

    fun provide(): PlantRepository {
        return PlantRepository(PlantService.create())
    }
}