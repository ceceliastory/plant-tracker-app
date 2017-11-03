package com.planttracker.plants

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.planttracker.Container
import com.planttracker.R

class PlantListActivity : AppCompatActivity() {

    private lateinit var container: Container

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        container = findViewById<View>(R.id.container) as Container
    }

    override fun onBackPressed() {
        val handled = container.onBackPressed()
        if (!handled) {
            finish()
        }
    }
}
