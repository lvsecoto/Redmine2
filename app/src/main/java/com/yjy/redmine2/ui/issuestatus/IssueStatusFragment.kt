package com.yjy.redmine2.ui.issuestatus

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.yjy.redmine2.R

class IssueStatusFragment : Fragment() {

    companion object {
        fun newInstance() = IssueStatusFragment()
    }

    private lateinit var viewModel: IssueStatusViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.issue_status_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(IssueStatusViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
