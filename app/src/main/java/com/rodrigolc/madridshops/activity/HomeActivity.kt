package com.rodrigolc.madridshops.activity

import android.app.AlertDialog
import android.app.Fragment
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.Toast
import com.rodrigolc.madridshops.R
import com.rodrigolc.madridshops.domain.interactor.ErrorCompletion
import com.rodrigolc.madridshops.domain.interactor.SuccessCompletion
import com.rodrigolc.madridshops.domain.interactor.getAllShops.GetAllShoptivitiesInteractor
import com.rodrigolc.madridshops.domain.interactor.getAllShopshops.GetAllShoptivitiesInteractorImplementation
import com.rodrigolc.madridshops.domain.interactor.internetstatus.InternetStatusInteractorImpl
import com.rodrigolc.madridshops.domain.model.Shoptivities
import com.rodrigolc.madridshops.fragment.HomeFragment
import com.rodrigolc.madridshops.router.Router



class HomeActivity: AppCompatActivity(), HomeFragment.OnSelectedSectionListener {

    val getAllShoptivitiesInteractor: GetAllShoptivitiesInteractor = GetAllShoptivitiesInteractorImplementation(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        //setSupportActionBar(toolbar)

        checkInternetConnection()

        if (fragmentManager.findFragmentById(R.id.home_fragment) == null) {
            val fragment: Fragment = HomeFragment.newInstance()
            fragmentManager.beginTransaction()
                    .add(R.id.home_fragment, fragment)
                    .commit()
        }
    }

    private fun dataBaseTrigger() {
        getAllShoptivitiesInteractor.execute(object : SuccessCompletion<Shoptivities> {
            override fun successCompletion(shoptivities: Shoptivities) {
                Log.d("MadridShops Data", "Data base loaded succesfully")
                Log.d("CORIO HomeActivity", "" + shoptivities.count())
            }
        }, object : ErrorCompletion {
            override fun errorCompletion(errorMessage: String) {
                Toast.makeText(baseContext, "Error loading data", Toast.LENGTH_LONG).show()
            }
        })
    }

    private fun checkInternetConnection(){
        InternetStatusInteractorImpl().execute(this, success = {
            dataBaseTrigger()
        }, error = {
            AlertDialog.Builder(this)
                    .setTitle("No internet connection")
                    .setMessage("There is no internet connection. Please solve the issue and try again.")
                    .setPositiveButton("Ok", {dialog, _ ->
                        dialog.dismiss()
                        //dataBaseTrigger()
                        this.finish()

                    })
                    /*.setNegativeButton("Retry",{ dialog, _ ->
                        dialog.dismiss()
                        checkInternetConnection()
                    })*/
                    .show()

        })
    }



    override fun onSelectedSection(section: String) = Router().navigateFromHomeActivityToMainActivity(this, section)



}
