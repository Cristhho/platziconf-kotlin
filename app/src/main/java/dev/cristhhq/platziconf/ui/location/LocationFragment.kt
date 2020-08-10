package dev.cristhhq.platziconf.ui.location

import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.findNavController
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.*
import dev.cristhhq.platziconf.R
import dev.cristhhq.platziconf.model.Location

class LocationFragment : Fragment(), OnMapReadyCallback {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_location, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    override fun onMapReady(map: GoogleMap?) {
        val location = Location()
        val zoom = 16f
        val centerMap = LatLng(location.latitude, location.longitude)

        map?.animateCamera(CameraUpdateFactory.newLatLngZoom(centerMap, zoom))

        val marker = MarkerOptions()
        marker.position(centerMap)
        marker.title("Platzi Conf")
        val bitmap = context?.applicationContext?.let { ContextCompat.getDrawable(it, R.drawable.logo_platzi) } as BitmapDrawable
        val smallMarker = Bitmap.createScaledBitmap(bitmap.bitmap, 100, 100, false)
        marker.icon(BitmapDescriptorFactory.fromBitmap(smallMarker))
        map?.addMarker(marker)
        map?.setOnInfoWindowClickListener {
            findNavController().navigate(R.id.locationDetailDialogFragment)
        }
        map?.setMapStyle(MapStyleOptions.loadRawResourceStyle(context, R.raw.custom_map))
    }

}