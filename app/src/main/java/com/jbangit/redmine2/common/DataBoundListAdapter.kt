package com.jbangit.redmine2.common

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter

abstract class DataBoundListAdapter<T, B : ViewDataBinding>(itemCallback: DiffUtil.ItemCallback<T>) :
    ListAdapter<T, DataBoundViewHolder<B>>(itemCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataBoundViewHolder<B> {
        return DataBoundViewHolder(createBinding(parent))
    }

    open fun createBinding(parent: ViewGroup): B = DataBindingUtil.inflate(
        LayoutInflater.from(parent.context), layoutId, parent, false
    )

    open val layoutId: Int = 0

    override fun onBindViewHolder(holder: DataBoundViewHolder<B>, position: Int) {
        onBindData(holder.binding, getItem(position))
    }

    open fun onBindData(binding: B, item: T) {}
}
