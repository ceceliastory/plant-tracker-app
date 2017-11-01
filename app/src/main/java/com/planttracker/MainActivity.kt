package com.planttracker

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val plantRepository by lazy {
        PlantRepositoryProvider.provide()
    }

    private var disposable: CompositeDisposable = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        getPlants()
    }


    private fun getPlants() {
        disposable.add(
                plantRepository.getAllPlants()
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(
                                { result -> plant_name.text = result[0].name },
                                { error -> Toast.makeText(this, error.message, Toast.LENGTH_SHORT).show() }
                        ))
    }
}
