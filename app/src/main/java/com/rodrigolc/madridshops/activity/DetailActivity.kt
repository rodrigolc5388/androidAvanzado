package com.rodrigolc.madridshops.activity

import android.app.Fragment
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import com.rodrigolc.madridshops.R
import com.rodrigolc.madridshops.domain.model.Shoptivity
import com.rodrigolc.madridshops.fragment.DetailFragment

class DetailActivity: AppCompatActivity() {

    companion object {
        private val EXTRA_SHOPTIVITY = "EXTRA_SHOPTIVITY"

        fun newInstance(context: Context, shoptivity: Shoptivity): Intent {
            val intent = Intent(context, DetailActivity::class.java)
            intent.putExtra(EXTRA_SHOPTIVITY, shoptivity)

            return intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val shoptivity = intent.getSerializableExtra(EXTRA_SHOPTIVITY) as Shoptivity

        supportActionBar?.title = shoptivity.name.capitalize()
        supportActionBar?.setDisplayHomeAsUpEnabled(true)


        if (fragmentManager.findFragmentById(R.id.shoptivity_detail_fragment) == null){
            val fragment: Fragment = DetailFragment.newInstance(shoptivity)
            fragmentManager.beginTransaction()
                    .add(R.id.shoptivity_detail_fragment, fragment)
                    .commit()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            android.R.id.home -> {
                finish()
                true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}

