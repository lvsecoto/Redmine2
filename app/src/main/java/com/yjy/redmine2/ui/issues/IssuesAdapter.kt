package com.yjy.redmine2.ui.issues

import androidx.recyclerview.widget.DiffUtil
import com.yjy.redmine2.R
import com.yjy.redmine2.common.DataBoundListAdapter
import com.yjy.redmine2.databinding.ViewItemIssuesListBinding
import com.yjy.redmine2.repository.model.IssueInList

class IssuesAdapter : DataBoundListAdapter<IssueInList, ViewItemIssuesListBinding>(
    itemCallback = object : DiffUtil.ItemCallback<IssueInList>() {
        override fun areItemsTheSame(oldItem: IssueInList, newItem: IssueInList): Boolean =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: IssueInList, newItem: IssueInList): Boolean =
            oldItem == newItem
    }
) {

    override fun onBindData(binding: ViewItemIssuesListBinding, item: IssueInList) {
        super.onBindData(binding, item)
        binding.issue = item
    }

    override val layoutId: Int
        get() = R.layout.view_item_issues_list
}
