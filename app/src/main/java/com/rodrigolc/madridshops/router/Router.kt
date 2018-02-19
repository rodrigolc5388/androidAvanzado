package com.rodrigolc.madridshops.router

import android.content.Intent
import com.rodrigolc.madridshops.activity.DetailActivity
import com.rodrigolc.madridshops.activity.HomeActivity
import com.rodrigolc.madridshops.activity.MainActivity
import com.rodrigolc.madridshops.activity.PicassoActivity
import com.rodrigolc.madridshops.domain.model.Shoptivity

class Router {
    fun navigateFromMainActivityToPicassoActivity(main: MainActivity){
        main.startActivity(Intent(main, PicassoActivity::class.java))
    }

    fun navigateFromMainActivityToDetailActivity(main: MainActivity, shoptivity: Shoptivity){
        val intent = DetailActivity.newInstance(main.baseContext, shoptivity)
        main.startActivity(intent)
    }

    fun navigateFromHomeActivityToMainActivity(home: HomeActivity, section: String){
        val intent = MainActivity.intent(home.baseContext, section)
        home.startActivity(intent)
    }
}