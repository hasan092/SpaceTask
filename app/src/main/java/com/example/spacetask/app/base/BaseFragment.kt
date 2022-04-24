package com.example.spacetask.app.base

import androidx.fragment.app.Fragment
import com.example.spacetask.data.models.APIStatus
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
open class BaseFragment : Fragment() {
    private val progressBar = ProgressDialogFragment()

    fun showProgressBar() {
        progressBar.show(parentFragmentManager, "")
    }

    fun hideProgressBar() {
        progressBar.dismissAllowingStateLoss()
    }

    fun handleApiStatus(apiStatus: APIStatus) {
        when (apiStatus.status) {
            APIStatus.Status.LOADING -> {
                showProgressBar()
            }
            APIStatus.Status.SUCCESS -> {
                hideProgressBar()
            }
            APIStatus.Status.FAILED -> {
                hideProgressBar()
            }
        }
    }
}