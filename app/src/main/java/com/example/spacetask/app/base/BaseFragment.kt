package com.example.spacetask.app.base

import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.spacetask.data.models.APIStatus
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
open class BaseFragment : Fragment() {
    private val progressBar = ProgressDialogFragment()

    private fun showProgressBar() {
        progressBar.show(parentFragmentManager, "")
    }

    private fun hideProgressBar() {
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
                apiStatus.message?.let { showError(it) }
            }
        }
    }

    private fun showError(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_LONG).show()
    }
}