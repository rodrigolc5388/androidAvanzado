package com.rodrigolc.madridshops.router

import android.content.Intent
import com.rodrigolc.madridshops.activity.MainActivity
import com.rodrigolc.madridshops.activity.PicassoActivity

class Router {
    fun navigateFromMainActivityToPicassoActivity(main: MainActivity){
        main.startActivity(Intent(main, PicassoActivity::class.java))
    }
}