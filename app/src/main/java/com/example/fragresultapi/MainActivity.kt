package com.example.fragresultapi

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.fragresultapi.databinding.ActivityMainBinding
import com.example.fragresultapi.fragments.MasterFragment

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .add(binding.container.id, MasterFragment(),"dialog")
                .commit()
        }
    }
}