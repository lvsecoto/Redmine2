package com.lvsecoto.redmine2.ui.issuesdetail

import androidx.recyclerview.widget.DiffUtil
import com.lvsecoto.redmine2.R
import com.lvsecoto.redmine2.common.DataBoundListAdapter
import com.lvsecoto.redmine2.databinding.ViewItemIssueDetailAttachmentBinding
import com.lvsecoto.redmine2.repository.model.IssueDetail.Companion.Attachment

class AttachmentAdapter : DataBoundListAdapter<Attachment, ViewItemIssueDetailAttachmentBinding>(
    object : DiffUtil.ItemCallback<Attachment>() {
        override fun areItemsTheSame(
            oldItem: Attachment, newItem: Attachment
        ): Boolean {
            return oldItem.contentUrl == newItem.contentUrl
        }

        override fun areContentsTheSame(
            oldItem: Attachment, newItem: Attachment
        ): Boolean {
            return oldItem.contentUrl == newItem.contentUrl
        }
    }
) {

    override val layoutId: Int
        get() = R.layout.view_item_issue_detail_attachment

    override fun onBindData(binding: ViewItemIssueDetailAttachmentBinding, item: Attachment) {
        super.onBindData(binding, item)
        binding.attachment = item
    }
}