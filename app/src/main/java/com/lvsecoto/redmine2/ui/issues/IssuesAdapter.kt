package com.yjy.redmine2.ui.issues

import androidx.navigation.Navigation
import androidx.recyclerview.widget.DiffUtil
import com.yjy.redmine2.R
import com.yjy.redmine2.common.DataBoundListAdapter
import com.yjy.redmine2.databinding.ViewItemIssuesListBinding
import com.yjy.redmine2.repository.model.IssueInList

class IssuesAdapter(
    private val viewModel: IssuesViewModel
) : DataBoundListAdapter<IssueInList, ViewItemIssuesListBinding>(
    itemCallback = object : DiffUtil.ItemCallback<IssueInList>() {
        override fun areItemsTheSame(oldItem: IssueInList, newItem: IssueInList): Boolean =
            oldItem.issueId == newItem.issueId

        override fun areContentsTheSame(oldItem: IssueInList, newItem: IssueInList): Boolean =
            oldItem == newItem
    }
) {

    override fun onBindData(binding: ViewItemIssuesListBinding, item: IssueInList) {
        super.onBindData(binding, item)
        binding.run {
            issue = item
            viewModel = this@IssuesAdapter.viewModel
            setOnClickItem {
                Navigation.findNavController(root).navigate(
                    IssuesFragmentDirections.actionViewIssueDetail(item.issueId)
                )
            }
        }
    }

    override val layoutId: Int
        get() = R.layout.view_item_issues_list
}
