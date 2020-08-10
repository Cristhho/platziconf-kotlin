package dev.cristhhq.platziconf.ui.schedule

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import dev.cristhhq.platziconf.R
import dev.cristhhq.platziconf.model.Conference
import dev.cristhhq.platziconf.viewmodel.ScheduleViewModel
import kotlinx.android.synthetic.main.fragment_schedule.*

class ScheduleFragment : Fragment() {

    private lateinit var schedulerAdapter: ScheduleAdapter
    private lateinit var viewModel: ScheduleViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_schedule, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProviders.of(this).get(ScheduleViewModel::class.java)

        schedulerAdapter = ScheduleAdapter(object: ScheduleAdapter.ScheduleListener{
            override fun onConferenceClicked(conference: Conference, position: Int) {
                val bundle = bundleOf("conference" to conference)
                findNavController().navigate(R.id.scheduleDetailDialogFragment, bundle)
            }
        })
        rvSchedule.apply {
            layoutManager = LinearLayoutManager(view.context, LinearLayoutManager.VERTICAL, false)
            adapter = schedulerAdapter
        }
        rvSchedule.addItemDecoration(DividerItemDecoration(context, LinearLayoutManager.VERTICAL))
        observeViewModel()
        viewModel.refresh()
    }

    private fun observeViewModel() {
        viewModel.listSchedule.observe(viewLifecycleOwner, Observer {
            schedulerAdapter.updateData(it)
        })

        viewModel.isLoading.observe(viewLifecycleOwner, Observer {
            if(it != null)
                rlBase.visibility = View.INVISIBLE
        })
    }
}