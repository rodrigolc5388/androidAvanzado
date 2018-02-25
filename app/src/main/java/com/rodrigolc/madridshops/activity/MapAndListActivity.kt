package com.rodrigolc.madridshops.activity


import android.Manifest.permission.ACCESS_COARSE_LOCATION
import android.Manifest.permission.ACCESS_FINE_LOCATION
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.MenuItem
import android.widget.Toast
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.rodrigolc.madridshops.R
import com.rodrigolc.madridshops.adapter.InfoWindowAdapter
import com.rodrigolc.madridshops.domain.interactor.ErrorCompletion
import com.rodrigolc.madridshops.domain.interactor.SuccessCompletion
import com.rodrigolc.madridshops.domain.interactor.getAllShops.GetAllShoptivitiesInteractor
import com.rodrigolc.madridshops.domain.interactor.getAllShopshops.GetAllShoptivitiesInteractorImplementation
import com.rodrigolc.madridshops.domain.model.Shoptivities
import com.rodrigolc.madridshops.domain.model.Shoptivity
import com.rodrigolc.madridshops.fragment.ListFragment
import com.rodrigolc.madridshops.router.Router
import com.rodrigolc.madridshops.utils.SectionType
import kotlinx.android.synthetic.main.activity_map_and_list.*

class MapAndListActivity : AppCompatActivity(), ListFragment.OnSelectedShoptivityListener {

    companion object {
        private val EXTRA_TYPE = "EXTRA_TYPE"

        fun intent(context: Context, type: SectionType): Intent{
            val intent = Intent(context, MapAndListActivity::class.java)
            intent.putExtra(EXTRA_TYPE, type)
            return intent
        }
    }

    private var map: GoogleMap? = null
    lateinit var listFragment: ListFragment

    val getAllShoptivitiesInteractor: GetAllShoptivitiesInteractor = GetAllShoptivitiesInteractorImplementation(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_map_and_list)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val type = intent.getSerializableExtra(EXTRA_TYPE) as SectionType
        setUpMapAndListForType(type)

    }

    private fun setUpMapAndListForType(fetchType: SectionType){
        getAllShoptivitiesInteractor.executeForType(fetchType,
                object : SuccessCompletion<Shoptivities> {
                    override fun successCompletion(shoptivities: Shoptivities) {
                        initializeMap(shoptivities)
                        initializeList(shoptivities)
                        Log.d("CORIO MainShoptivities", "" + shoptivities.count())
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

            map = it

            it.uiSettings.isRotateGesturesEnabled = false
            it.uiSettings.isZoomControlsEnabled = true

            centerMapInPosition(it, 40.416775, -3.703790)
            showUserPosition(baseContext, it)
            addAllPins(shoptivities)

            it.setInfoWindowAdapter(InfoWindowAdapter(this))
            it.setOnInfoWindowClickListener(GoogleMap.OnInfoWindowClickListener {
                val shoptivity: Shoptivity = it.tag as Shoptivity
                Router().navigateFromMainActivityToDetailActivity(this, shoptivity)
                it.isVisible = false
            })

        })
    }


    private fun initializeList(shoptivities: Shoptivities){
        listFragment = supportFragmentManager.findFragmentById(R.id.activity_main_list_fragment) as ListFragment
        listFragment.setShoptivities(shoptivities)
    }


    // Navigation to Shoptivity Detail View
    override fun onSelectedShoptivity(shoptivity: Shoptivity) = Router().navigateFromMainActivityToDetailActivity(this, shoptivity)


    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            android.R.id.home -> {
                finish()
                true
            }
        }
        return super.onOptionsItemSelected(item)
    }



    // GoogleMap setUp

    fun centerMapInPosition(map: GoogleMap, latitude: Double, longitude: Double){
        val coordinate = LatLng(latitude, longitude)

        val cameraPosition = CameraPosition.Builder()
                .target(coordinate)
                .zoom(13f)
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
        } else {
            map.isMyLocationEnabled = true
        }
    }


    fun addPin(map: GoogleMap, shoptivity: Shoptivity){
        map.addMarker(MarkerOptions()
                .position(LatLng(shoptivity.latitude!!, shoptivity.longitude!!))
                .title(shoptivity.name)
                .icon(BitmapDescriptorFactory.fromResource(when(shoptivity.type){
                    "shop" -> R.drawable.shop_marker_icon
                    "activity" -> R.drawable.activity_marker_icon
                    else -> Log.d("Error", "Custom marker icon not found")
                })))
                .tag = shoptivity



    }


    fun addAllPins(shoptivities: Shoptivities){
        for (i in 0 until shoptivities.count()){
            val shop = shoptivities.get(i)
            if(shop.latitude != null && shop.longitude != null) {
                addPin(map!!, shop)
            }
        }
    }


    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == 10){
            try{
                map?.isMyLocationEnabled = true
            } catch (e: SecurityException){
            }
        }
    }
}
