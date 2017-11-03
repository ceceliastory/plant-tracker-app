package com.planttracker

import com.planttracker.plants.PlantListView
import com.planttracker.plants.PlantModule
import com.planttracker.plants.PlantRepository
import com.planttracker.plants.PlantViewModel
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(AppModule::class, PlantModule::class))
interface AppComponent {

    fun inject(app: App)
    fun inject(plantViewModel: PlantViewModel)
    fun inject(plantListView: PlantListView)
    fun inject(plantRepository: PlantRepository)
}