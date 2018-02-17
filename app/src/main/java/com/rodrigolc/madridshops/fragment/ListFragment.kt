package com.rodrigolc.madridshops.fragment


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.rodrigolc.madridshops.R
import com.rodrigolc.madridshops.adapter.RecyclerViewAdapter
import com.rodrigolc.madridshops.domain.model.Shoptivities


class ListFragment: Fragment() {
    companion object {
        fun newInstance(shoptivities: Shoptivities): ListFragment {
            val fragment = ListFragment()
            val arguments = Bundle()
            //arguments.put
        }
    }

    lateinit var root: View
    lateinit var shoptivitiesList: RecyclerView
    lateinit var adapter: RecyclerViewAdapter
    //private var onSelectedPlateListener: OnSelectedPlateListener? = null


    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)

        inflater?.let{
            root = it.inflate(R.layout.fragment_list, container, false)

        }

        // Inflate the layout for this fragment
        return root

    }

    fun shoptivitiesListSetter() {
        shoptivitiesList = root.findViewById(R.id.shoptivities_list)
        shoptivitiesList.layoutManager = LinearLayoutManager(activity)
        shoptivitiesList.itemAnimator = DefaultItemAnimator()
        shoptivitiesList.adapter = RecyclerViewAdapter(null)

    }

}
