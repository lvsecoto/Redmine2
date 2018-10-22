package com.lvsecoto.redmine2.ui.issuestatus

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.lvsecoto.redmine2.R
import com.lvsecoto.redmine2.common.utils.showToast
import com.lvsecoto.redmine2.databinding.IssueStatusFragmentBinding

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
        binding.toolbar.setupWithNavController(findNavController())
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val statusAdapter = StatusAdapter(
            onClickItem = {
                viewModel.changeStatus(it.statusId)
            }
        )

        viewModel = ViewModelProviders.of(this).get(IssueStatusViewModel::class.java).apply {
            issueId.value = fromBundle().issueId
            issueStatus.observe(viewLifecycleOwner, Observer {
                statusAdapter.submitList(it.data)
            })
            changedIssueStatus.observe(viewLifecycleOwner, Observer {
                when (it.status) {
                    com.lvsecoto.liveobserver.Status.SUCCESS -> {
                        if (it.data != true) {
                            showToast("修改失败")
                        }
                        findNavController().navigateUp()
                    }
                    com.lvsecoto.liveobserver.Status.ERROR -> showToast(it.message)
                    com.lvsecoto.liveobserver.Status.LOADING -> {
                    }
                }
            })
        }

        binding.run {
            with(statuses) {
                adapter = statusAdapter
            }
        }
    }

    private fun fromBundle() =
        IssueStatusFragmentArgs.fromBundle(arguments)

}
