package com.rodrigolc.madridshops.fragment


import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.rodrigolc.madridshops.R


class HomeFragment : Fragment() {

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
            setButtonListener(shopsButton, getString(R.string.shop_section_string))

            activitiesButton = root.findViewById(R.id.activities_button)
            activitiesButton.setImageResource(R.drawable.activites_logo)
            setButtonListener(activitiesButton, getString(R.string.activity_section_string))
        }

        return root
    }

    fun setButtonListener(button: ImageView, section: String){
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
        fun onSelectedSection(section: String)
    }

}
