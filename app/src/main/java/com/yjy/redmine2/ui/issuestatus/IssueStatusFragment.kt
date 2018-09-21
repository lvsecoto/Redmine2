package com.yjy.redmine2.ui.issuestatus

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.yjy.redmine2.R
import com.yjy.redmine2.databinding.IssueStatusFragmentBinding

class IssueStatusFragment : Fragment() {

    companion object {
        fun newInstance() = IssueStatusFragment()
    }

    private lateinit var viewModel: IssueStatusViewModel

    private lateinit var binding: IssueStatusFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =
                DataBindingUtil.inflate(inflater, R.layout.issue_status_fragment, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(IssueStatusViewModel::class.java)
        val statusAdapter = StatusAdapter(viewModel)

        binding.run {

            with(statuses) {
                adapter = statusAdapter
            }

        }

        viewModel.run {
            issueStatus.observe(viewLifecycleOwner, Observer {
                statusAdapter.submitList(it.data)
            })
        }
        // TODO: Use the ViewModel
    }

}
