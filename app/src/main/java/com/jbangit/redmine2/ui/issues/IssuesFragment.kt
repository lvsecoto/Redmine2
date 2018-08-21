package com.jbangit.redmine2.ui.issues

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.jbangit.redmine2.R
import com.jbangit.redmine2.databinding.FragmentIssuesBinding

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
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(IssuesViewModel::class.java)
        viewModel.issues.observe(this, Observer(adapter::submitList))
    }

}
