package com.jbangit.redmine2.ui.issues

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel

class IssuesViewModel : ViewModel() {
    val issues: LiveData<List<String>> = object : LiveData<List<String>>() {
        override fun onActive() {
            value = List(10) {
                "123"
            }
        }
    }
}
