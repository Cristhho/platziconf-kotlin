package dev.cristhhq.platziconf.ui.location

import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.DialogFragment
import dev.cristhhq.platziconf.R
import dev.cristhhq.platziconf.model.Location
import kotlinx.android.synthetic.main.fragment_location_detail_dialog.*

class LocationDetailDialogFragment : DialogFragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL, R.style.fullscreenDialog)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_location_detail_dialog, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        toolbarLocation.navigationIcon = ContextCompat.getDrawable(view.context, R.drawable.ic_close)
        toolbarLocation.setTitleTextColor(Color.WHITE)
        toolbarLocation.setNavigationOnClickListener {
            dismiss()
        }

        val location = Location()
        toolbarLocation.title = "Platzi Conf"
        tvDetailLocationName.text = location.name
        tvDetailLocationAddress.text = location.address
        tvDetailLocationPhone.text = location.phone
        tvDetailLocationWeb.text = location.webSite

        tvDetailLocationPhone.setOnClickListener {
            val intentPhone = Intent(Intent.ACTION_DIAL, Uri.parse("tel:${location.phone}"))
            startActivity(intentPhone)
        }
        tvDetailLocationWeb.setOnClickListener {
            val intentBrowser = Intent(Intent.ACTION_VIEW, Uri.parse(location.webSite))
            startActivity(intentBrowser)
        }
    }

    override fun onStart() {
        super.onStart()
        dialog?.window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
    }

}