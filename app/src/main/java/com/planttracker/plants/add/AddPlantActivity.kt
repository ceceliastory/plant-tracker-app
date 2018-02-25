package com.planttracker.plants.add

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.FrameLayout
import com.planttracker.R
import android.content.Intent
import com.planttracker.plants.view.PlantListActivity


class AddPlantActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_container)

        val mainContainer = findViewById<FrameLayout>(R.id.main_container)

        val initialView = layoutInflater.inflate(R.layout.plant_add_view, mainContainer, false)
        mainContainer.addView(initialView)
    }

    fun navigateBack() {
        val intent = Intent(this, PlantListActivity::class.java)
        startActivity(intent)
    }

}