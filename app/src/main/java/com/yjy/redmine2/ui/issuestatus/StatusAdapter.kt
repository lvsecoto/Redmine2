package com.yjy.redmine2.ui.issuestatus

import androidx.recyclerview.widget.DiffUtil
import com.yjy.redmine2.R
import com.yjy.redmine2.common.DataBoundListAdapter
import com.yjy.redmine2.databinding.ViewItemIssueStatusBinding
import com.yjy.redmine2.repository.model.StatusInList

class StatusAdapter(
    private val onClickItem: (StatusInList) -> Unit
) : DataBoundListAdapter<StatusInList, ViewItemIssueStatusBinding>(
    object : DiffUtil.ItemCallback<StatusInList>() {
        override fun areItemsTheSame(
            oldItem: StatusInList,
            newItem: StatusInList
        ): Boolean = oldItem.statusId == newItem.statusId

        override fun areContentsTheSame(
            oldItem: StatusInList,
            newItem: StatusInList
        ): Boolean =
            oldItem == newItem
    }
) {

    override val layoutId: Int
        get() = R.layout.view_item_issue_status

    override fun onBindData(binding: ViewItemIssueStatusBinding, item: StatusInList) {
        super.onBindData(binding, item)
        binding.status = item
        binding.root.setOnClickListener {
            onClickItem(item)
        }
    }
}
