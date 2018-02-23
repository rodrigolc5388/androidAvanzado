package com.rodrigolc.madridshops.router

import com.rodrigolc.madridshops.activity.DetailActivity
import com.rodrigolc.madridshops.activity.HomeActivity
import com.rodrigolc.madridshops.activity.MainActivity
import com.rodrigolc.madridshops.domain.model.Shoptivity
import com.rodrigolc.madridshops.utils.SectionType

class Router {

    fun navigateFromMainActivityToDetailActivity(main: MainActivity, shoptivity: Shoptivity){
        val intent = DetailActivity.newInstance(main.baseContext, shoptivity)
        main.startActivity(intent)
    }

    fun navigateFromHomeActivityToMainActivity(home: HomeActivity, section: SectionType){
        val intent = MainActivity.intent(home.baseContext, section)
        home.startActivity(intent)
    }
}