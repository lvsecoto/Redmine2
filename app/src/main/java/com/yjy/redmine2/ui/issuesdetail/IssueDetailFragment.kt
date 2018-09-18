package com.yjy.redmine2.ui.issuesdetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SpinnerAdapter
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

    private lateinit var attachmentAdapter : AttachmentAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =
                DataBindingUtil.inflate(inflater, R.layout.fragment_issue_detail, container, false)
        binding.pic.addItemDecoration(ItemDecoration())
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(IssueDetailViewModel::class.java)

        attachmentAdapter = AttachmentAdapter().also {
            binding.pic.adapter = it
        }

        viewModel.run {
            val argIssueId = IssueDetailFragmentArgs.fromBundle(arguments).issueId
            issueId.value = argIssueId

            issueDetail.observe(viewLifecycleOwner, Observer {
                binding.issueDetail = it.data
                attachmentAdapter.submitList(it.data?.attachments)
                when (it.status) {
                    Status.ERROR -> showToast(it.message)
                    else -> {
                    }
                }
            })
            statuses.observe(viewLifecycleOwner, Observer {
                binding.issuesDetailBinding.status
                SpinnerAdapter
            })
        }
    }

}
