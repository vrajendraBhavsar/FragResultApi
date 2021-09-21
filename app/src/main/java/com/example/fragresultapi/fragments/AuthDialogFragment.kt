package com.example.fragresultapi.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatDialogFragment
import androidx.core.view.postDelayed
import com.example.fragresultapi.R
import com.example.fragresultapi.databinding.FragmentAuthDialogBinding

class AuthDialogFragment : AppCompatDialogFragment() {
    lateinit var binding: FragmentAuthDialogBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentAuthDialogBinding.bind(view)    //fragment ViewBinding

        // First, get the resultKey from the caller.
        val requestKey = arguments?.getString(EXTRA_RESULT_KEY, null)
        if (requestKey.isNullOrEmpty()){
            dismiss()
            return
        }

        // Next, once user clicks "Auth", access the auth service and get the result.
        binding.btnAuth.setOnClickListener {
            val userName = binding.etUsername.text.toString()
            val password = binding.etPassword.text.toString()

            if (userName == password) {
                binding.btnAuth.text = resources.getText(R.string.verifying)
                view.postDelayed(1500){
                    childFragmentManager.setFragmentResult(requestKey, Bundle().apply {
                        putString(RESULT_AUTH_USER, userName)
                    })
                    dismiss()
                }
            }
        }
    }

//    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
//        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_auth_dialog, container, false)
//    }
    companion object{
        const val EXTRA_RESULT_KEY = "extra_result_key"
        const val RESULT_AUTH_USER = "result_auth_user"
    }
}