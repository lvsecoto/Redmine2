package com.jbangit.redmine2.common

import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

class DataBoundViewHolder<out B : ViewDataBinding> constructor(
    val binding: B
) : RecyclerView.ViewHolder(binding.root)