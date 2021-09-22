package com.example.fragresultapi.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatDialogFragment
import androidx.core.view.postDelayed
import androidx.fragment.app.setFragmentResult
import com.example.fragresultapi.R
import com.example.fragresultapi.databinding.FragmentAuthDialogBinding

class AuthDialogFragment : AppCompatDialogFragment() {
    lateinit var binding: FragmentAuthDialogBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_auth_dialog, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentAuthDialogBinding.bind(view)    //fragment ViewBinding

        // First, get the resultKey from the caller.
        val requestKey = arguments?.getString(REQUEST_KEY, null)
        if (requestKey.isNullOrEmpty()) {
            dismiss()
            return
        }

        // Next, once user clicks "Auth", access the auth service and get the result.
        binding.btnAuth.setOnClickListener {
            val userName = binding.etUsername.text.toString()
            val password = binding.etPassword.text.toString()

            if (userName == password) {
                binding.btnAuth.text = resources.getText(R.string.verifying)
                view.postDelayed(1500) {
                    setFragmentResult(requestKey, Bundle().apply {
                        putString(DATA_KEY, userName)
                    })
                    dismiss()   //closed the dialog
                }
            }else{
                Toast.makeText(requireContext(), "Creds doesn't match", Toast.LENGTH_SHORT).show()
            }
        }
    }

    companion object {
        const val REQUEST_KEY = "request_key"
        const val DATA_KEY = "data_key"
    }
}