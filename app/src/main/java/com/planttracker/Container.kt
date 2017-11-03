package com.planttracker

interface Container {

    fun showItem(item: String)
    fun onBackPressed() : Boolean
}