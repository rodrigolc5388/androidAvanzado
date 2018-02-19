package com.rodrigolc.madridshops.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.rodrigolc.madridshops.R
import com.rodrigolc.madridshops.domain.model.Shoptivity
import com.squareup.picasso.Picasso


class DetailFragment : android.app.Fragment() {

    companion object {
        private val EXTRA_SHOPTIVITY_FRAGMENT = "EXTRA_SHOPTIVITY_FRAGMENT"

        fun newInstance(shoptivity: Shoptivity): DetailFragment{
            val fragment = DetailFragment()
            val arguments = Bundle()
            arguments.putSerializable(EXTRA_SHOPTIVITY_FRAGMENT, shoptivity)
            fragment.arguments = arguments

            return fragment
        }
    }

    lateinit var root: View
    lateinit var picture: ImageView
    lateinit var description: TextView
    lateinit var openingHours: TextView
    lateinit var address: TextView
    lateinit var map: ImageView
    lateinit var detailShoptivity: Shoptivity


    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)

        inflater?.let{
            detailShoptivity = arguments.getSerializable(EXTRA_SHOPTIVITY_FRAGMENT) as Shoptivity
            Log.d("CORIO", "Latitude: " + detailShoptivity.latitude.toString())
            Log.d("CORIO", "Latitude: " + detailShoptivity.longitude.toString())

            root = it.inflate(R.layout.fragment_detail, container, false)

            picture = root.findViewById(R.id.fragment_detail_image_view)
            Picasso.with(activity).load(detailShoptivity.image).into(picture)

            description = root.findViewById(R.id.fragment_detail_description)
            description.setText(detailShoptivity.description)

            openingHours = root.findViewById(R.id.fragment_detail_opening_hours)
            openingHours.setText(detailShoptivity.openingHours)

            address = root.findViewById(R.id.fragment_detail_address)
            address.setText(detailShoptivity.address)

            map = root.findViewById(R.id.fragment_detail_map_image)
            val url = "http://maps.googleapis.com/maps/api/staticmap?center=${detailShoptivity.latitude.toString()},${detailShoptivity.longitude.toString()}&zoom=17&size=320x220&scale=2&key=AIzaSyCuHHCDuQUYslST0sRTRDAavFZw3qWR_AY"
            val url2 = "http://maps.googleapis.com/maps/api/staticmap?center=40.4353,%20-3.6924&zoom=17&size=320x220&scale=2"
            val url3 = "http://maps.googleapis.com/maps/api/staticmap?center=${detailShoptivity.latitude.toString()},${detailShoptivity.longitude.toString()}&zoom=17&size=320x220&scale=2"
            Picasso.with(activity).load(url3).into(map)
        }

        return root
    }

}
