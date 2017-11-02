package com.planttracker

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ListView
import android.widget.TextView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class PlantListFragment : Fragment() {

    @Inject lateinit var plantViewModel: PlantViewModel
    private val subscriptions = CompositeDisposable()
    private lateinit var plantAdapter: PlantListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        App.appComponent.inject(this)
        plantAdapter = PlantListAdapter(context)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.plant_list_view, container, false)

        val listView = view.findViewById<ListView>(R.id.list_plants) as ListView
        listView.adapter = plantAdapter

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
                plantViewModel.getAllPlants()
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(
                                { result ->
                                    run {
                                        plantAdapter.plants = result
                                        plantAdapter.notifyDataSetChanged()
                                    }
                                }
                        ))
    }

    class PlantListAdapter(context: Context) : BaseAdapter() {

        var plants: List<Plant> = emptyList()
        private val layoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
            val rowView = layoutInflater.inflate(R.layout.plan_list_detail_view, parent, false)
            val plant: Plant = getItem(position) as Plant
            val titleTextView = rowView.findViewById<TextView>(R.id.plant_name) as TextView

            titleTextView.text = plant.name

            return rowView
        }

        override fun getItem(p0: Int): Any {
            return plants[p0]
        }

        override fun getItemId(p0: Int): Long {
            return p0.toLong()
        }

        override fun getCount(): Int {
            return plants.size
        }
    }
}