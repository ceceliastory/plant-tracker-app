package com.planttracker.plants.view

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Button
import android.widget.FrameLayout
import com.planttracker.R
import com.planttracker.plants.add.AddPlantActivity

class PlantListActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_container)

        val mainContainer = findViewById<FrameLayout>(R.id.main_container)

        val initialView = layoutInflater.inflate(R.layout.plant_main, mainContainer, false)
        mainContainer.addView(initialView)

        val addPlantButton = findViewById<Button>(R.id.add_plant_button)
        addPlantButton.setOnClickListener {
            val intent = Intent(this, AddPlantActivity::class.java)
            startActivity(intent)
        }
    }

}
