package com.rodrigolc.madridshops.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView

import com.rodrigolc.madridshops.R
import com.rodrigolc.madridshops.domain.model.Shoptivity
import com.squareup.picasso.Picasso


class DetailFragment : Fragment() {

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
    lateinit var shoptivity: Shoptivity


    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)

        inflater?.let{
            shoptivity = arguments.getSerializable(EXTRA_SHOPTIVITY_FRAGMENT) as Shoptivity

            root = it.inflate(R.layout.fragment_detail, container, false)

            picture = root.findViewById(R.id.fragment_detail_image_view)
            Picasso.with(activity).load(shoptivity.image).into(picture)

            description = root.findViewById(R.id.fragment_detail_description)
            description.setText(shoptivity.description)

            openingHours = root.findViewById(R.id.fragment_detail_opening_hours)
            openingHours.setText(shoptivity.openingHours)

            address = root.findViewById(R.id.fragment_detail_address)
            address.setText(shoptivity.address)

            map = root.findViewById(R.id.fragment_detail_map_image)
            val url = "http://maps.googleapis.com/maps/api/staticmap?center=${shoptivity.latitude},${shoptivity.longitude}&zoom=17&size=${map.width}x${map.height}&scale=2"
            Picasso.with(activity).load(url).into(map)
        }

        return root
    }

}
