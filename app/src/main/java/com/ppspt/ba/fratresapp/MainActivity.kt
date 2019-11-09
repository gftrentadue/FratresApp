package com.ppspt.ba.fratresapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ppspt.ba.fratresapp.view.CalendarFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .add(R.id.container, CalendarFragment.newInstance(), CalendarFragment.TAG)
                .commitNow()
        }
    }

}
