package com.rodrigolc.madridshops.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.rodrigolc.madridshops.R
import com.rodrigolc.madridshops.domain.interactor.getAllShops.GetAllShoptivitiesInteractor
import com.rodrigolc.madridshops.domain.interactor.getAllShopshops.GetAllShoptivitiesInteractorImplementation
import com.rodrigolc.madridshops.fragment.HomeFragment

class HomeActivity: AppCompatActivity(), HomeFragment.OnSelectedSectionListener{

    val getAllShopsInteractor: GetAllShoptivitiesInteractor = GetAllShoptivitiesInteractorImplementation(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        //setSupportActionBar(toolbar)

        
    }











    override fun onSelectedSection(section: String) {

    }

}
