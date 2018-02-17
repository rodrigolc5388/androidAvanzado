package com.rodrigolc.madridshops.activity


import android.Manifest.permission.ACCESS_COARSE_LOCATION
import android.Manifest.permission.ACCESS_FINE_LOCATION
import android.content.Context
import android.content.pm.PackageManager
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.rodrigolc.madridshops.R
import com.rodrigolc.madridshops.adapter.InfoWindowAdapter
import com.rodrigolc.madridshops.adapter.RecyclerViewAdapter
import com.rodrigolc.madridshops.domain.interactor.ErrorCompletion
import com.rodrigolc.madridshops.domain.interactor.SuccessCompletion
import com.rodrigolc.madridshops.domain.interactor.getAllShops.GetAllShoptivitiesInteractor
import com.rodrigolc.madridshops.domain.interactor.getAllShopshops.GetAllShoptivitiesInteractorImplementation
import com.rodrigolc.madridshops.domain.model.Shoptivities
import com.rodrigolc.madridshops.domain.model.Shoptivity
import com.rodrigolc.madridshops.fragment.ListFragment
import com.rodrigolc.madridshops.router.Router
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity: AppCompatActivity() {
    lateinit var listFragment: ListFragment
    lateinit var adapter: RecyclerViewAdapter

    val getAllShopsInteractor: GetAllShoptivitiesInteractor = GetAllShoptivitiesInteractorImplementation(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        Log.d("App", "onCreate MainActivity")


        //setupMap()
        getShoptivitiesForType("activity")
        //getShoptivitiesForType("shop")
        listFragment = supportFragmentManager.findFragmentById(R.id.activity_main_list_fragment) as ListFragment
    }

    private fun setUpList(){
        //listFragment =
    }

    private fun setupMap() {
        getAllShopsInteractor.execute(object : SuccessCompletion<Shoptivities> {
            override fun successCompletion(shoptivities: Shoptivities) {
                //initializeMap(shoptivities)
                //Log.d("Shoptivities", "Count: " + shoptivities.count())

            }
        }, object : ErrorCompletion {
            override fun errorCompletion(errorMessage: String) {
                Toast.makeText(baseContext, "Error loading", Toast.LENGTH_LONG).show()
            }
        })
    }

    private fun getShoptivitiesForType(type: String) {
        getAllShopsInteractor.executeForType(type,
            object : SuccessCompletion<Shoptivities> {
                override fun successCompletion(shoptivities: Shoptivities) {
                    initializeMap(shoptivities)
                }
            },
            object : ErrorCompletion {
                override fun errorCompletion(errorMessage: String) {
                    Toast.makeText(baseContext, "Error loading", Toast.LENGTH_LONG).show()
                }
            })
    }

    private fun initializeMap(shoptivities: Shoptivities) {
        val mapFragment = supportFragmentManager.findFragmentById(R.id.activity_main_map_fragment) as SupportMapFragment
        mapFragment.getMapAsync({
            Log.d("SUCCESS", "HABEMUS MAPA")

            centerMapInPosition(it, 40.416775, -3.703790)
            it.uiSettings.isRotateGesturesEnabled = false
            it.uiSettings.isZoomControlsEnabled = true
            showUserPosition(baseContext, it)
            map = it
            addAllPins(shoptivities)
            it.setInfoWindowAdapter(InfoWindowAdapter(this))

        })
    }

    fun centerMapInPosition(map: GoogleMap, latitude: Double, longitude: Double){
        val coordinate = LatLng(latitude, longitude)

        val cameraPosition = CameraPosition.Builder()
                .target(coordinate)
                .zoom(15f)
                .build()

        map.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition))
    }

    fun showUserPosition(context: Context, map: GoogleMap){
        if(ActivityCompat.checkSelfPermission(context, ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(context, ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this,
                    arrayOf(ACCESS_FINE_LOCATION, ACCESS_COARSE_LOCATION),
                    10)

            return
        }

    }

    private var map: GoogleMap? = null

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == 10){
            try{
            map?.isMyLocationEnabled = true
            } catch (e: SecurityException){
            }
        }
    }

    fun addAllPins(shoptivities: Shoptivities){
        for (i in 0 until shoptivities.count()){
            val shop = shoptivities.get(i)
            if(shop.latitude != null && shop.longitude != null) {
                addPin(map!!, shop)
            }

        }
    }

    fun addPin(map: GoogleMap, shoptivity: Shoptivity){
        map.addMarker(MarkerOptions()
                .position(LatLng(shoptivity.latitude!!, shoptivity.longitude!!))
                .title(shoptivity.name))
                .tag = shoptivity
    }





    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        Router().navigateFromMainActivityToPicassoActivity(this)
        return true /*when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }*/
    }
}
