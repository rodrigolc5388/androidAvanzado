package com.rodrigolc.madridshops.adapter

import android.content.Context
import android.util.Log
import android.widget.ImageView
import com.google.android.gms.maps.model.Marker
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso

class InfoWindowAdapterCallback(
        val context: Context,
        val url: String,
        val view: ImageView,
        val marker: Marker): Callback{
    override fun onSuccess() {
        if (marker.isInfoWindowShown){
            marker.hideInfoWindow()

            Picasso.with(context).load(url).into(view)
            marker.showInfoWindow()
        }
    }

    override fun onError() {
        Log.d("Picasso", "Error 💩: Picasso error loading marker image")
    }

}
