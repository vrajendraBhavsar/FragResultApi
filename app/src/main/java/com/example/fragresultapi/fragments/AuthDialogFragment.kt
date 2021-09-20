package com.example.fragresultapi.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatDialogFragment
import com.example.fragresultapi.R
class AuthDialogFragment : AppCompatDialogFragment() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // First, get the resultKey from the caller.
        val requestKey = arguments?.getString(EXTRA_RESULT_KEY, null)
        if (requestKey.isNullOrEmpty()){
            dismiss()
            return
        }
    }

//    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
//        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_auth_dialog, container, false)
//    }

    // Next, once user clicks "Auth", access the auth service and get the result.


    companion object{
        const val EXTRA_RESULT_KEY = "extra_result_key"
        const val RESULT_AUTH_USER = "result_auth_user"
    }
}