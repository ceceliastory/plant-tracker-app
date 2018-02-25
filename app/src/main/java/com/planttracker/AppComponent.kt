package com.planttracker

import com.planttracker.plants.add.AddPlantView
import com.planttracker.plants.*
import com.planttracker.plants.view.PlantListView
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