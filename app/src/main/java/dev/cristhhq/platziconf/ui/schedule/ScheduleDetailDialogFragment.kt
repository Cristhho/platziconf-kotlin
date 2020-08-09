package dev.cristhhq.platziconf.ui.schedule

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.DialogFragment
import dev.cristhhq.platziconf.R
import dev.cristhhq.platziconf.model.Conference
import kotlinx.android.synthetic.main.fragment_schedule_detail_dialog.*
import java.text.SimpleDateFormat
import java.util.*

class ScheduleDetailDialogFragment : DialogFragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL, R.style.fullscreenDialog)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_schedule_detail_dialog, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        toolbarConf.navigationIcon = ContextCompat.getDrawable(view.context, R.drawable.ic_close)
        toolbarConf.setTitleTextColor(Color.WHITE)
        toolbarConf.setNavigationOnClickListener {
            dismiss()
        }

        val conference = arguments?.getSerializable("conference") as Conference
        toolbarConf.title = conference.title
        tvDetailConfTitle.text = conference.title
        val dateFormat = SimpleDateFormat("dd/MM/yyyy hh:mm a", Locale("es", "EC"))
        tvDetailConfHour.text = dateFormat.format(conference.datetime)
        tvDetailConfSpeaker.text = conference.speaker
        tvDetailConfTag.text = conference.tag
        tvDetailConfDesc.text = conference.description
    }

    override fun onStart() {
        super.onStart()
        dialog?.window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
    }
}