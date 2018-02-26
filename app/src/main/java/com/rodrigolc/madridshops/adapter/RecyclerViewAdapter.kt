package com.rodrigolc.madridshops.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.rodrigolc.madridshops.R
import com.rodrigolc.madridshops.domain.model.Shoptivities
import com.rodrigolc.madridshops.domain.model.Shoptivity
import com.squareup.picasso.Picasso
import java.util.*

class RecyclerViewAdapter(val shoptivities: Shoptivities?): RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>() {

    var onClickListener: View.OnClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent?.context).inflate(R.layout. recycler_view_cell, parent, false)
        view.setOnClickListener(onClickListener)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        if (shoptivities != null){
            holder?.bindPlate(shoptivities.get(position))
        }
    }

    override fun getItemCount(): Int = shoptivities?.count() ?: 0



    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val shoptivityName = itemView.findViewById<TextView>(R.id.cell_title)
        val shoptivityOpeningHours = itemView.findViewById<TextView>(R.id.cell_subtitle)
        val shoptivityLogo = itemView.findViewById<ImageView>(R.id.cell_image)


        fun bindPlate(shoptivity: Shoptivity) {
            val context = shoptivityLogo.context
            shoptivityName.text = shoptivity.name
            when (Locale.getDefault().getLanguage()){
                "es" -> {
                    shoptivityOpeningHours.text = shoptivity.openingHours_es
                }
                else ->  {
                    shoptivityOpeningHours.text = shoptivity.openingHours_en
                }
            }
            // Cell image loading with Picasso
            Picasso.with(context).load(shoptivity.logo).into(shoptivityLogo)
        }
    }
}