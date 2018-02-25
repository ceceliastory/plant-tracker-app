package com.planttracker.plants

data class Plant(val name: String,
                 var lastWatered: String = "",
                 var wateringIntervalInDays: Int = 0)