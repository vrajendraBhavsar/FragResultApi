package com.example.fragresultapi.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.example.fragresultapi.R
import com.example.fragresultapi.databinding.FragmentMasterBinding
import java.util.*

class MasterFragment : Fragment(R.layout.fragment_master) {
    lateinit var binding: FragmentMasterBinding

    companion object {
        val requestKey = UUID.randomUUID().toString()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        childFragmentManager.setFragmentResultListener(requestKey, this) { _, bundle ->
            val userNameFromDialog = bundle.getString(AuthDialogFragment.DATA_KEY, "Unknown")
            Log.d("HELOWWWW", "onCreate: $bundle")
            binding.tvMaster.text = "Auth user: $userNameFromDialog"
            Toast.makeText(requireContext(), "Auth successfully!", Toast.LENGTH_LONG).show()
            childFragmentManager.clearFragmentResultListener(requestKey)   //cleared listener
        }
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentMasterBinding.bind(view)

        binding.btnMaster.setOnClickListener {
            childFragmentManager.openAuthDialog(requestKey)
        }
    }

    fun FragmentManager.openAuthDialog(resultKey: String) {
        val existing = findFragmentByTag("AuthDialogFragment")
        if (existing != null) { //if fragment already exist..we will remove (if Dialog is open already)
            childFragmentManager
                .beginTransaction()
                .remove(existing)
                .commit()
        }
        //opening dialog
        val authDialog = AuthDialogFragment().apply {
            val bundle = Bundle()
            bundle.putString(AuthDialogFragment.REQUEST_KEY, resultKey) //bundle ma key set tari
            arguments = bundle
        }
        authDialog.show(this, "AuthDialogFragment")
    }
}