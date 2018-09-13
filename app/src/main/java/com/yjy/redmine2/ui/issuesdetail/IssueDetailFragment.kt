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
import com.yjy.redmine2.common.Status
import com.yjy.redmine2.databinding.FragmentIssueDetailBinding
import com.yjy.redmine2.ui.issues.showToast


class IssueDetailFragment : Fragment() {

    companion object {
        fun newInstance() = IssueDetailFragment()
    }

    private lateinit var viewModel: IssueDetailViewModel

    private lateinit var binding: FragmentIssueDetailBinding

    private val attachmentAdapter = AttachmentAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_issue_detail, container, false)
        binding.pic.adapter = attachmentAdapter
        binding.pic.addItemDecoration(ItemDecoration())
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(IssueDetailViewModel::class.java)

        val issueId = IssueDetailFragmentArgs.fromBundle(arguments).issueId

        viewModel.also { it ->
            it.issueId.value = issueId
            it.issueDetail.observe(this, Observer {
                when(it.status) {
                    Status.LOADING -> binding.issueDetail = it.data
                    Status.ERROR -> showToast(it.message)
                    Status.SUCCESS -> {
                        binding.issueDetail = it.data
                        attachmentAdapter.submitList(it.data?.attachments)
                    }
                }
            })
        }
    }

}
