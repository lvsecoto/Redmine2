package com.yjy.redmine2

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.yjy.redmine2.ui.test.TestFragment

class TestActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.test_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, TestFragment.newInstance())
                .commitNow()
        }
    }

}
