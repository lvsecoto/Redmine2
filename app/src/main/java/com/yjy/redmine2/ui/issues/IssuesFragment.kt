package com.yjy.redmine2.ui.issues

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import com.google.android.material.navigation.NavigationView
import com.google.android.material.snackbar.Snackbar
import com.yjy.redmine2.R
import com.yjy.redmine2.common.Status
import com.yjy.redmine2.databinding.FragmentIssuesBinding

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

        adapter = IssuesAdapter(
            viewModel = viewModel
        ).apply {
        }
        binding.issues.adapter = adapter
        viewModel.issues.observe(this, Observer {
            when (it.status) {
                Status.ERROR -> showToast(it.message)
                else -> {
                    adapter.submitList(it.data)
                }
            }
        })
        viewModel.solvedIssue.observe(this, Observer {
            when (it.status) {
                Status.SUCCESS -> showToast("Solve issue ${it.data?.issueId}")
                Status.ERROR -> showToast(it.message)
                Status.LOADING -> showToast("wait")
            }
        })
    }

}

fun Fragment.showToast(toast: String?) {
    Toast.makeText(this.context, toast, Toast.LENGTH_LONG).show()
}
