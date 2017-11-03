package com.planttracker

import android.content.Context
import android.util.AttributeSet
import android.widget.FrameLayout
import com.planttracker.plants.PlantListView


class SinglePaneContainer(context: Context, attrs: AttributeSet) : FrameLayout(context, attrs), Container {
    private var listView: PlantListView? = null
    override fun onFinishInflate() {
        super.onFinishInflate()
        listView = getChildAt(0) as PlantListView
    }

    override fun onBackPressed(): Boolean {
        if (!listViewAttached()) {
            removeViewAt(0)
            addView(listView)
            return true
        }
        return false
    }

    override fun showItem(item: String) {
//        if (listViewAttached()) {
//            removeViewAt(0)
//            View.inflate(context, R.layout.detail, this)
//        }
//        val detailView = getChildAt(0) as MyDetailView
//        detailView.setItem(item)
    }

    private fun listViewAttached(): Boolean {
        return listView!!.parent != null
    }
}