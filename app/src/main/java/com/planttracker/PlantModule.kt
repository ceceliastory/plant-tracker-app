package com.planttracker

import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class PlantModule {

    @Provides
    @Singleton
    fun providePlantViewModel(plantRepository: PlantRepository) : PlantViewModel {
        return PlantViewModel(plantRepository)
    }

    @Provides
    @Singleton
    fun providePlantRepository(plantApiClient: PlantApiClient) : PlantRepository {
        return PlantRepository(plantApiClient)
    }

    @Provides
    @Singleton
    fun providePlantClientApi() : PlantApiClient {
        return PlantApiClient.create()
    }
}