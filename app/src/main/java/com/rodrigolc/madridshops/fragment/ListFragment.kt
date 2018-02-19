package com.rodrigolc.madridshops.fragment

import android.app.Activity
import android.content.Context
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
import com.rodrigolc.madridshops.domain.model.Shoptivity


class ListFragment: Fragment() {

    lateinit var root: View
    lateinit var shoptivitiesList: RecyclerView
    private var adapter: RecyclerViewAdapter? = null
    private var recylerViewShoptivities: Shoptivities? = null
    private var onSelectedShoptivityListener: OnSelectedShoptivityListener? = null



    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)

        inflater?.let{
            root = it.inflate(R.layout.fragment_list, container, false)
            shoptivitiesList = root.findViewById(R.id.shoptivities_list) as RecyclerView
            shoptivitiesList.layoutManager = LinearLayoutManager(activity)
            shoptivitiesList.itemAnimator = DefaultItemAnimator()
            adapter = RecyclerViewAdapter(recylerViewShoptivities)
            shoptivitiesList.adapter = adapter
        }

        return root
    }



    fun setAdapterListener(adapter: RecyclerViewAdapter){
        adapter.onClickListener = View.OnClickListener { v: View ->
            val position = shoptivitiesList.getChildAdapterPosition(v)
            val shoptivity = recylerViewShoptivities!!.get(position)
            onSelectedShoptivityListener?.onSelectedShoptivity(shoptivity)
            // Tal vez haya que quitar este .finish()
            activity.finish() }
    }

    fun setShoptivities(shoptivities: Shoptivities){
        this.recylerViewShoptivities = shoptivities
        adapter = RecyclerViewAdapter(recylerViewShoptivities)
        setAdapterListener(adapter!!)
        shoptivitiesList.adapter = adapter
    }


    override fun onAttach(context: Context?) {
        super.onAttach(context)
        commonAttach(context)
    }

    override fun onAttach(activity: Activity?) {
        super.onAttach(activity)
        commonAttach(activity)
    }

    override fun onDetach() {
        super.onDetach()
        onSelectedShoptivityListener = null
    }

    fun commonAttach(listener: Any?) {
        if (listener is OnSelectedShoptivityListener) {
            onSelectedShoptivityListener = listener
        }
    }

    interface OnSelectedShoptivityListener {
        fun onSelectedShoptivity(shoptivity: Shoptivity)
    }

}
