package com.example.fragresultapi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.fragresultapi.databinding.ActivityMainBinding
import com.example.fragresultapi.fragments.MasterFragment

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportFragmentManager.beginTransaction().add(MasterFragment::class.java, "")
    }
}