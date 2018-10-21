package com.lvsecoto.redmine2.ui.issueassignment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.lvsecoto.redmine2.R

class IssueAssignmentFragment : Fragment() {

    companion object {
        fun newInstance() = IssueAssignmentFragment()
    }

    private lateinit var viewModel: IssueAssignmentViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.issue_assignment_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(IssueAssignmentViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
