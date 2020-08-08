package dev.cristhhq.platziconf.ui.speakers

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import dev.cristhhq.platziconf.R
import dev.cristhhq.platziconf.model.Speaker
import dev.cristhhq.platziconf.viewmodel.SpeakersViewModel
import kotlinx.android.synthetic.main.fragment_schedule.*
import kotlinx.android.synthetic.main.fragment_speakers.*
import kotlinx.android.synthetic.main.fragment_speakers.rlBase

class SpeakersFragment : Fragment() {

    private lateinit var speakersAdapter: SpeakersAdapter
    private lateinit var viewModel : SpeakersViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_speakers, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProviders.of(this).get(SpeakersViewModel::class.java)

        speakersAdapter = SpeakersAdapter(object: SpeakersAdapter.SpeakerListener{
            override fun onSpeakerClicked(speaker: Speaker, position: Int) {
                findNavController().navigate(R.id.speakersDetailDialogFragment,
                    bundleOf("speaker" to speaker))
            }
        })
        rvSpeakers.apply {
            layoutManager = GridLayoutManager(view.context, 2, LinearLayoutManager.VERTICAL, false)
            adapter = speakersAdapter
        }
        observeViewModel()
        viewModel.refresh()
    }

    private fun observeViewModel() {
        viewModel.listSpeakers.observe(viewLifecycleOwner, Observer {
            speakersAdapter.updateData(it)
        })

        viewModel.isLoading.observe(viewLifecycleOwner, Observer {
            if(it != null)
                rlBase.visibility = View.INVISIBLE
        })
    }
}