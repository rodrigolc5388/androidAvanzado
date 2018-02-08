package com.rodrigolc.madridshops.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.rodrigolc.madridshops.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_picasso.*

class PicassoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_picasso)

        Picasso.with(this).setIndicatorsEnabled(true)

        Picasso.with(this)
                .load("http://static.812superfast.ferrari.com/includes/20170309/img/50.pages.01.homepage/03.Exterior-design/01/152m-feature-1440.jpg")
                .placeholder(android.R.drawable.ic_delete)
                .into(img1)

        Picasso.with(this)
                .load("https://www.supercars.net/blog/wp-content/uploads/2016/03/Screenshot-2016-03-07-22.46.08.png")
                .placeholder(android.R.drawable.ic_delete)
                .into(img2)

        Picasso.with(this)
                .load("https://www.km77.com/images/medium/0/3/3/9/range-rover-velar-lateral-posterior.330339.jpg")
                .placeholder(android.R.drawable.ic_delete)
                .into(img3)
    }
}
