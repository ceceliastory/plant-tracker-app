package com.planttracker.plants

import android.content.Context
import android.support.annotation.LayoutRes
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.planttracker.App
import com.planttracker.R
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.plan_list_detail_view.view.*
import javax.inject.Inject

class PlantListView(context: Context, attributeSet: AttributeSet) : RecyclerView(context, attributeSet) {

    @Inject lateinit var plantViewModel: PlantViewModel
    private val subscriptions = CompositeDisposable()
    private lateinit var listAdapter: PlantListAdapter
    private lateinit var linearLayoutManager: LinearLayoutManager

    override fun onFinishInflate() {
        super.onFinishInflate()
        App.appComponent.inject(this)

        linearLayoutManager = LinearLayoutManager(context)
        layoutManager = linearLayoutManager

        listAdapter = PlantListAdapter()
        adapter = listAdapter

        displayPlants()
    }

    fun onStop() {
        subscriptions.clear()
    }

    private fun displayPlants() {
        subscriptions.add(
                plantViewModel.getAllPlants().subscribe(
                        { result ->
                            run {
                                listAdapter.plants = result
                                listAdapter.notifyItemInserted(result.size)
                            }
                        }
                ))
    }

    class PlantListAdapter : RecyclerView.Adapter<PlantListAdapter.PlantHolder>() {
        var plants: List<Plant> = emptyList()

        override fun getItemCount(): Int {
            return plants.size
        }

        override fun onBindViewHolder(holder: PlantHolder, position: Int) {
            val plant = plants[position]
            holder.bindPlant(plant)
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlantHolder {
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