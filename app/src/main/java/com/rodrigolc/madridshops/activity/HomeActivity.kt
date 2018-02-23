package com.rodrigolc.madridshops.activity

import android.app.AlertDialog
import android.app.Fragment
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.Toast
import com.rodrigolc.madridshops.domain.interactor.ErrorCompletion
import com.rodrigolc.madridshops.domain.interactor.SuccessCompletion
import com.rodrigolc.madridshops.domain.interactor.getAllShops.GetAllShoptivitiesInteractor
import com.rodrigolc.madridshops.domain.interactor.getAllShopshops.GetAllShoptivitiesInteractorImplementation
import com.rodrigolc.madridshops.domain.interactor.internetstatus.InternetStatusInteractorImpl
import com.rodrigolc.madridshops.domain.model.Shoptivities
import com.rodrigolc.madridshops.fragment.HomeFragment
import com.rodrigolc.madridshops.router.Router
import com.rodrigolc.madridshops.utils.SectionType
import kotlinx.android.synthetic.main.activity_home.*


class HomeActivity: AppCompatActivity(), HomeFragment.OnSelectedSectionListener {

    val getAllShoptivitiesInteractor: GetAllShoptivitiesInteractor = GetAllShoptivitiesInteractorImplementation(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.rodrigolc.madridshops.R.layout.activity_home)

        checkInternetConnection()

        if (fragmentManager.findFragmentById(com.rodrigolc.madridshops.R.id.home_fragment) == null) {
            val fragment: Fragment = HomeFragment.newInstance()
            fragmentManager.beginTransaction()
                    .add(com.rodrigolc.madridshops.R.id.home_fragment, fragment)
                    .commit()
        }
    }

    private fun dataBaseTrigger() {
        //loadingView.smoothToShow()

        getAllShoptivitiesInteractor.execute(object : SuccessCompletion<Shoptivities> {
            override fun successCompletion(shoptivities: Shoptivities) {
                loadingView.smoothToHide()
                Log.d("MadridShops Data", "Data base loaded succesfully")
                Log.d("CORIO HomeActivity", "" + shoptivities.count())
            }
        }, object : ErrorCompletion {
            override fun errorCompletion(errorMessage: String) {
                loadingView.smoothToHide()
                Toast.makeText(baseContext, "Error loading data", Toast.LENGTH_LONG).show()
            }
        })
    }

    private fun checkInternetConnection(){
        loadingView.smoothToShow()
        InternetStatusInteractorImpl().execute(this, success = {
            dataBaseTrigger()
        }, error = {
            AlertDialog.Builder(this)

                    .setTitle("No internet connection")
                    .setMessage("There is no internet connection. Please solve the issue and try again.")
                    .setPositiveButton("Ok", {dialog, _ ->
                        dialog.dismiss()
                        this.finish()
                    })
                    .setNegativeButton("Use cached data",{ dialog, _ ->
                        dialog.dismiss()
                        dataBaseTrigger()
                    })
                    .show()

        })
    }

    // Fragment listener method to receive section's button touched
    override fun onSelectedSection(section: SectionType) = Router().navigateFromHomeActivityToMainActivity(this, section)
}
