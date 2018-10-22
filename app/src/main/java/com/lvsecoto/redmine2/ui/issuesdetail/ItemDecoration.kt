package com.lvsecoto.redmine2.ui.issuesdetail

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.lvsecoto.redmine2.common.utils.fromDp

class ItemDecoration : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        outRect.left = 16.fromDp(parent.context)
        outRect.top = 16.fromDp(parent.context)
        outRect.right = 16.fromDp(parent.context)
        if (parent.getChildAdapterPosition(view) == parent.adapter!!.itemCount - 1) {
            outRect.bottom = 16.fromDp(parent.context)
        }
    }
}