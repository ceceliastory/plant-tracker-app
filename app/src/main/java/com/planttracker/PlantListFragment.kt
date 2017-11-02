package com.planttracker

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class PlantListFragment : Fragment() {

    @Inject lateinit var plantViewModel: PlantViewModel
    private val subscriptions = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        App.appComponent.inject(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.plant_list_fragment, container, false)
    }

    override fun onStart() {
        super.onStart()

        displayPlants()
    }

    override fun onStop() {
        super.onStop()
        subscriptions.clear()
    }

    private fun displayPlants() {
        subscriptions.add(
                plantViewModel.getAllPlants()
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(
                                { result -> view?.findViewById<TextView>(R.id.plant_name)?.text = result[0].name }
                        ))
    }
}