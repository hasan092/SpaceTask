package com.example.spacetask.app.landing

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.spacetask.app.base.BaseFragment
import com.example.spacetask.data.models.Launcher
import com.example.spacetask.databinding.LandingFragmentBinding
import com.example.spacetask.utils.DateUtils
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LandingFragment : BaseFragment() {

    lateinit var binding: LandingFragmentBinding
    private val landingViewModel:LandingViewModel by viewModels()
    lateinit var adapter: LaunchAdapter
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        if (::binding.isInitialized.not()) {
            binding = LandingFragmentBinding.inflate(inflater, container, false)
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        landingViewModel.getLaunches()

        landingViewModel.launchers.observe(viewLifecycleOwner, Observer {
            handleLaunch(it)
        })

        landingViewModel.apiStatus.observe(viewLifecycleOwner, Observer {
            handleApiStatus(it)
        })
    }

    private fun handleLaunch(launches: List<Launcher>) {
        val list = launches.filter { (it.success && !DateUtils.isDateGreater(it.static_fire_date_unix?: 0)) || it.upcoming}
        adapter = LaunchAdapter(requireContext(), list) {
            findNavController().navigate(LandingFragmentDirections.toRocketDetailsFragment(it))
        }
        binding.launches.adapter = adapter
    }
}