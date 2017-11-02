package com.planttracker

import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(AppModule::class, PlantModule::class))
interface AppComponent {

    fun inject(app: App)
    fun inject(plantViewModel: PlantViewModel)
    fun inject(plantListFragment: PlantListFragment)
    fun inject(plantRepository: PlantRepository)
}