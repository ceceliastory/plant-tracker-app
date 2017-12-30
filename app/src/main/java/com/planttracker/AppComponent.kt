package com.planttracker

import com.planttracker.plants.*
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(AppModule::class, PlantModule::class))
interface AppComponent {

    fun inject(app: App)
    fun inject(plantViewModel: PlantViewModel)
    fun inject(plantListView: PlantListView)
    fun inject(addPlantView: AddPlantView)
    fun inject(plantRepository: PlantRepository)
}