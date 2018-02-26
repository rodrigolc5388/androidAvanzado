package com.rodrigolc.madridshops.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.Marker
import com.rodrigolc.madridshops.R
import com.rodrigolc.madridshops.domain.model.Shoptivity
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.marker_info_window.view.*
import java.util.*


class InfoWindowAdapter(val context: Context): GoogleMap.InfoWindowAdapter{

    override fun getInfoContents(m: Marker): View {

        val infoWinView = LayoutInflater.from(context).inflate(R.layout.marker_info_window, null)
        val shoptivity = m.tag as Shoptivity
        val image = infoWinView.info_window_image

        infoWinView.info_window_title.text = shoptivity.name
        when (Locale.getDefault().getLanguage()){
            "es" -> {
                infoWinView.info_window_subtitle.text = shoptivity.openingHours_es
            }
            else ->  {
                infoWinView.info_window_subtitle.text = shoptivity.openingHours_en
            }
        }

        Picasso.with(context)
                .load(shoptivity.logo)
                .into(image, InfoWindowAdapterCallback(context, shoptivity.logo, image, m))

        return infoWinView
    }

    override fun getInfoWindow(m: Marker): View? {
        return null
    }
}
