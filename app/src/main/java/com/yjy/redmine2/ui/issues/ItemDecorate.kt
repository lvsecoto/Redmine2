package com.yjy.redmine2.ui.issues

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class ItemDecorate : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        outRect.left = 16
        outRect.top = 16
        outRect.right = 16
        if (parent.getChildAdapterPosition(view) == parent.adapter!!.itemCount - 1) {
            outRect.bottom = 16
        }
    }
}