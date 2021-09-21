package com.example.fragresultapi.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.system.Os.remove
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.FragmentManager
import com.example.fragresultapi.R
import com.example.fragresultapi.databinding.FragmentMasterBinding
import java.util.*

class MasterFragment : Fragment() {
    lateinit var binding: FragmentMasterBinding
    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentMasterBinding.bind(view)

        childFragmentManager.setFragmentResultListener(request_key, this) { _, bundle ->
            val userNameFromDialog = bundle.getString(AuthDialogFragment.RESULT_AUTH_USER, "Unknown")
            binding.tvMaster.text = "Auth user: $userNameFromDialog"
            Toast.makeText(requireContext(), "Auth successfully!", Toast.LENGTH_LONG).show()
            childFragmentManager.clearFragmentResultListener(request_key)   //cleared listener
        }

        binding.btnMaster.setOnClickListener {
            childFragmentManager.openAuthDialog()
        }
    }

    companion object{
        val request_key = UUID.randomUUID().toString()
    }

    fun FragmentManager.openAuthDialog(resultKey: String) {
        val existing = findFragmentByTag("AuthDialogFragment")
        if (existing != null){
            remove(existing)
        }
        val newDialog = AuthDialogFragment().apply {
            val bundle = Bundle()
            bundle.putString(AuthDialogFragment.EXTRA_RESULT_KEY, resultKey)
            arguments = bundle
        }
        newDialog.show(this, "AuthDialogFragment")
    }
}