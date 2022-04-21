package com.example.spacetask.app.rocket

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.FileProvider
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions
import com.example.spacetask.data.ResponseHandler
import com.example.spacetask.data.models.Launcher
import com.example.spacetask.data.models.Rocket
import com.example.spacetask.databinding.RocketDetailsFragmentBinding
import com.example.spacetask.utils.DateUtils
import com.example.spacetask.utils.Utils
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RocketDetailsFragment : Fragment() {
    lateinit var binding: RocketDetailsFragmentBinding
    private val rocketViewModel: RocketViewModel by viewModels()
    private var launcher: Launcher? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = RocketDetailsFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.let {
            launcher = it.get("launcher") as Launcher
        }

        launcher?.let {
            it.rocket?.let { rocket ->
                rocketViewModel.getRocket(rocket, object : ResponseHandler<Rocket> {
                    override fun onLoading() {

                    }

                    override fun onResponse(aResult: Rocket) {
                        handleRocketDetails(aResult)
                    }

                    override fun onError(aError: String) {
                    }

                    override fun onFailure(aThrowable: Throwable) {
                    }

                })
            }

        }
    }

    fun handleRocketDetails(rocket: Rocket) {
        launcher?.let {
            binding.flightNumber.text = it.flight_number.toString()
            val date = if (it.upcoming) DateUtils.getVisualDate(it.date_unix?:0) else DateUtils.getVisualDate(it.static_fire_date_unix?: 0)
            binding.flightDate.text = date
            binding.upcomingIndicator.visibility = if (it.upcoming) View.VISIBLE else View.GONE
        }
        rocket.let {
            binding.flightName.text = it.name
            binding.flightDescription.text = it.description
            it.flickr_images.let { image ->
                if (image.isNotEmpty()) {
                    val options = RequestOptions().transform(CenterCrop())
                    Glide.with(requireActivity())
                        .load(image[0])
                        .transition(DrawableTransitionOptions.withCrossFade())
                        .apply(options)
                        .into(binding.launcherBackground)
                }
            }
        }

        binding.close.setOnClickListener {
            activity?.onBackPressed()
        }

        binding.readMore.setOnClickListener {
            Utils.readMore(activity, rocket.wikipedia)
        }
    }
}