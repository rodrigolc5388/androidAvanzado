package com.rodrigolc.madridshops.fragment


import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.rodrigolc.madridshops.R
import com.rodrigolc.madridshops.utils.SectionType


class HomeFragment : android.app.Fragment() {

    companion object {

        fun newInstance() = HomeFragment()
    }

    lateinit var root: View
    lateinit var shopsButton: ImageView
    lateinit var activitiesButton: ImageView
    private var onSelectedSectionListener: OnSelectedSectionListener? = null


    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)

        inflater?.let{
            root = it.inflate(R.layout.fragment_home, container, false)

            shopsButton = root.findViewById(R.id.shops_button)
            shopsButton.setImageResource(R.drawable.shops_logo)
            setButtonListener(shopsButton, SectionType.SHOP)

            activitiesButton = root.findViewById(R.id.activities_button)
            activitiesButton.setImageResource(R.drawable.activites_logo)
            setButtonListener(activitiesButton, SectionType.ACTIVITY)
        }

        return root
    }

    fun setButtonListener(button: ImageView, section: SectionType){
        button.setOnClickListener{
            onSelectedSectionListener?.onSelectedSection(section)
        }
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
        onSelectedSectionListener = null
    }

    fun commonAttach(listener: Any?) {
        if (listener is OnSelectedSectionListener) {
            onSelectedSectionListener = listener
        }
    }

    interface OnSelectedSectionListener {
        fun onSelectedSection(section: SectionType)
    }

}
