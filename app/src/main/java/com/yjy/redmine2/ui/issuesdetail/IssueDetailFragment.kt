package com.yjy.redmine2.ui.issuesdetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.yjy.redmine2.R
import com.yjy.redmine2.common.ID_NULL
import com.yjy.redmine2.common.Status
import com.yjy.redmine2.common.utils.showToast
import com.yjy.redmine2.databinding.FragmentIssueDetailBinding
import com.yjy.redmine2.ui.issuesdetail.IssueDetailFragmentDirections.actionChangeIssueStatus


class IssueDetailFragment : Fragment() {

    companion object {
        fun newInstance() = IssueDetailFragment()
    }

    private lateinit var viewModel: IssueDetailViewModel

    private lateinit var binding: FragmentIssueDetailBinding

    private lateinit var attachmentAdapter: AttachmentAdapter

    private var argIssueId: Int = ID_NULL

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
        argIssueId = IssueDetailFragmentArgs.fromBundle(arguments).issueId

        attachmentAdapter = AttachmentAdapter().also {
            binding.pic.adapter = it
        }

        binding.issuesDetailBinding.run {
            status.setOnClickListener {
                findNavController().navigate(
                    R.id.action_change_issue_status,
                    actionChangeIssueStatus(argIssueId).arguments
                )
            }
        }

        viewModel.run {
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
        }

    }

}
