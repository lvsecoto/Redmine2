package com.jbangit.redmine2.ui.issues

import androidx.recyclerview.widget.DiffUtil
import com.jbangit.redmine2.R
import com.jbangit.redmine2.common.DataBoundListAdapter
import com.jbangit.redmine2.databinding.ViewItemIssuesListBinding

class IssuesAdapter : DataBoundListAdapter<String, ViewItemIssuesListBinding>(
    itemCallback = object : DiffUtil.ItemCallback<String>() {
        override fun areItemsTheSame(oldItem: String, newItem: String): Boolean =
            oldItem == newItem

        override fun areContentsTheSame(oldItem: String, newItem: String): Boolean =
            oldItem == newItem
    }
) {

    override val layoutId: Int
        get() = R.layout.view_item_issues_list
}
