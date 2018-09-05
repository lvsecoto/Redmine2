package com.yjy.redmine2.ui.issues

import android.graphics.Rect
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.RecyclerView
import com.yjy.redmine2.R
import com.yjy.redmine2.databinding.FragmentIssuesBinding

class IssuesFragment : Fragment() {

    companion object {
        fun newInstance() = IssuesFragment()
    }

    private lateinit var binding: FragmentIssuesBinding

    private lateinit var viewModel: IssuesViewModel

    private var adapter = IssuesAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding =
                DataBindingUtil.inflate(inflater, R.layout.fragment_issues, container, false)
        binding.issues.adapter = adapter
        binding.issues.addItemDecoration(object : RecyclerView.ItemDecoration(){
            override fun getItemOffsets(
                outRect: Rect,
                view: View,
                parent: RecyclerView,
                state: RecyclerView.State
            ) {
                outRect.left = 16
                outRect.top = 16
                outRect.right = 16
                if (parent.getChildAdapterPosition(view) == adapter.itemCount - 1) {
                    outRect.bottom = 16
                }
            }
        })
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(IssuesViewModel::class.java)
        viewModel.issues.observe(this, Observer {
            adapter.submitList(it.data)
        })
        viewModel.statusEntity.observe(this, Observer {
            return@Observer
        })
    }
}
