package com.yjy.redmine2.ui.issuesdetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.yjy.redmine2.R
import com.yjy.redmine2.databinding.FragmentIssueDetailBinding


class IssueDetailFragment : Fragment() {

    companion object {
        fun newInstance() = IssueDetailFragment()
    }

    private lateinit var viewModel: IssueDetailViewModel

    private lateinit var binding: FragmentIssueDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_issue_detail, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(IssueDetailViewModel::class.java)

        val issueId = IssueDetailFragmentArgs.fromBundle(arguments).issueId

        viewModel.also { it ->
            it.issueId.value = issueId
            it.issueDetail.observe(this, Observer {
                binding.issueDetail = it.data
            })
        }
    }

}
