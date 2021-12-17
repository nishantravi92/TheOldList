package com.example.theoldlist

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.theoldlist.settings.PreferenceUtils


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        PreferenceUtils.setup(activity = this)
        setContentView(R.layout.fragment_container)
    }
}
