package com.planttracker

import android.os.Bundle
import android.support.annotation.LayoutRes
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.plan_list_detail_view.view.*
import kotlinx.android.synthetic.main.plant_list_view.view.*
import javax.inject.Inject

class PlantListFragment : Fragment() {

    @Inject lateinit var plantViewModel: PlantViewModel
    private val subscriptions = CompositeDisposable()
    private lateinit var plantAdapter: PlantListAdapter
    private lateinit var linearLayoutManager: LinearLayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        App.appComponent.inject(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.plant_list_view, container, false)

        linearLayoutManager = LinearLayoutManager(context)
        view.plants.layoutManager = linearLayoutManager

        plantAdapter = PlantListAdapter()
        view.plants.adapter = plantAdapter

        return view
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
                plantViewModel.getAllPlants().subscribe(
                        { result ->
                            run {
                                plantAdapter.plants = result
                                plantAdapter.notifyItemInserted(result.size)
                            }
                        }
                ))
    }

    class PlantListAdapter : RecyclerView.Adapter<PlantListAdapter.PlantHolder>() {
        var plants: List<Plant> = emptyList()

        override fun getItemCount(): Int {
            return plants.size
        }

        override fun onBindViewHolder(holder: PlantListAdapter.PlantHolder, position: Int) {
            val plant = plants[position]
            holder.bindPlant(plant)
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlantListAdapter.PlantHolder {
            val inflatedView = parent.inflate(R.layout.plan_list_detail_view, false)
            return PlantHolder(inflatedView)
        }

        class PlantHolder(private var view: View) : RecyclerView.ViewHolder(view), View.OnClickListener {
            private var plant: Plant? = null

            init {
                view.setOnClickListener(this)
            }

            fun bindPlant(plant: Plant) {
                this.plant = plant
                view.plant_name.text = plant.name
            }

            override fun onClick(v: View) {
                Log.d("RecyclerView", "CLICK!")
            }
        }
    }
}

fun ViewGroup.inflate(@LayoutRes layoutRes: Int, attachToRoot: Boolean = false): View {
    return LayoutInflater.from(context).inflate(layoutRes, this, attachToRoot)
}