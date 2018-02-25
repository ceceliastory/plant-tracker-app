package com.planttracker.plants

import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class PlantViewModel @Inject constructor(private val plantRepository: PlantRepository) {

    fun getAllPlants(): Observable<List<Plant>> {
        return plantRepository.getAllPlants()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }

    fun addPlant(name: String, lastWatered: String, wateringIntervalInDays: Int) {
        plantRepository.addPlant(Plant(name, lastWatered, wateringIntervalInDays))
    }
}