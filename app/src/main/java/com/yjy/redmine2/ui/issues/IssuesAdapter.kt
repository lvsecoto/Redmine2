package com.yjy.redmine2.ui.issues

import androidx.recyclerview.widget.DiffUtil
import com.yjy.redmine2.R
import com.yjy.redmine2.common.DataBoundListAdapter
import com.yjy.redmine2.databinding.ViewItemIssuesListBinding
import com.yjy.redmine2.repository.model.IssueList

class IssuesAdapter : DataBoundListAdapter<IssueList, ViewItemIssuesListBinding>(
    itemCallback = object : DiffUtil.ItemCallback<IssueList>() {
        override fun areItemsTheSame(oldItem: IssueList, newItem: IssueList): Boolean =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: IssueList, newItem: IssueList): Boolean =
            oldItem == newItem
    }
) {

    override fun onBindData(binding: ViewItemIssuesListBinding, item: IssueList) {
        super.onBindData(binding, item)
        binding.issue = item
    }

    override val layoutId: Int
        get() = R.layout.view_item_issues_list
}
