package com.rodrigolc.madridshops.router

import com.rodrigolc.madridshops.activity.DetailActivity
import com.rodrigolc.madridshops.activity.HomeActivity
import com.rodrigolc.madridshops.activity.MapAndListActivity
import com.rodrigolc.madridshops.domain.model.Shoptivity
import com.rodrigolc.madridshops.utils.SectionType

class Router {

    fun navigateFromMainActivityToDetailActivity(mapAndList: MapAndListActivity, shoptivity: Shoptivity){
        val intent = DetailActivity.newInstance(mapAndList.baseContext, shoptivity)
        mapAndList.startActivity(intent)
    }

    fun navigateFromHomeActivityToMainActivity(home: HomeActivity, section: SectionType){
        val intent = MapAndListActivity.intent(home.baseContext, section)
        home.startActivity(intent)
    }
}