package com.lvsecoto.redmine2.ui.issues

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.lvsecoto.liveobserver.Status
import com.lvsecoto.redmine2.R
import com.lvsecoto.redmine2.common.utils.showToast
import com.lvsecoto.redmine2.databinding.FragmentIssuesBinding

class IssuesFragment : Fragment() {

    companion object {
        fun newInstance() = IssuesFragment()
    }

    private lateinit var binding: FragmentIssuesBinding

    private lateinit var viewModel: IssuesViewModel

    private lateinit var adapter: IssuesAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_issues, container, false
        )
        binding.issues.addItemDecoration(ItemDecorate())
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(IssuesViewModel::class.java)

        adapter = IssuesAdapter(viewModel).also {
            binding.issues.adapter = it
        }

        viewModel.run {
            issues.observe(viewLifecycleOwner, Observer {
                adapter.submitList(it.data)
                when (it.status) {
                    Status.ERROR -> showToast(it.message)
                    else -> {
                    }
                }
            })
            solvedIssue.observe(viewLifecycleOwner, Observer {
                when (it.status) {
                    Status.SUCCESS -> showToast("Solved issue")
                    Status.ERROR -> showToast(it.message)
                    Status.LOADING -> showToast("wait")
                }
            })
        }
    }
}
