package com.planttracker.plants.add

import android.content.Context
import android.util.AttributeSet
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import com.planttracker.App
import com.planttracker.R
import com.planttracker.plants.PlantViewModel
import javax.inject.Inject

class AddPlantView(context: Context?, attrs: AttributeSet?) : LinearLayout(context, attrs) {

    @Inject lateinit var plantViewModel: PlantViewModel

    override fun onFinishInflate() {
        super.onFinishInflate()
        App.appComponent.inject(this)

        val button = findViewById<Button>(R.id.add_new_plant_submit_button)
        button.setOnClickListener {
            val plantNameInput = findViewById<EditText>(R.id.plant_name_input) as EditText
            val plantWateringInterval = findViewById<EditText>(R.id.plant_watering_interval) as EditText
            val plantLastWateredDate = findViewById<EditText>(R.id.plant_last_watered_date) as EditText

            if (plantNameInput.text.isNotEmpty()) {
                plantViewModel.addPlant(
                        plantNameInput.text.toString(),
                        plantLastWateredDate.text.toString(),
                        plantWateringInterval.text.toString().toInt()
                )
                (context as AddPlantActivity).navigateBack()
            }
        }
    }
}