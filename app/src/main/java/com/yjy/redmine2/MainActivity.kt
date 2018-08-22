package com.yjy.redmine2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.yjy.redmine2.ui.issues.IssuesFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, IssuesFragment.newInstance())
                .commitNow()
        }
    }

}
