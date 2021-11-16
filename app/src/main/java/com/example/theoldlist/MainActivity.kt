package com.example.theoldlist

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.room.Room
import com.example.theoldlist.taskdatasource.TaskDataBase


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_container)
        supportFragmentManager.beginTransaction().apply {
            add(R.id.fragment_container_id, TasksFragment()).commitNow()
        }
    }
}
