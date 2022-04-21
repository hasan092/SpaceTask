package com.example.spacetask.app.landing

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions
import com.example.spacetask.data.models.Launcher
import com.example.spacetask.databinding.ItemLaunchBinding
import com.example.spacetask.utils.DateUtils

class LaunchAdapter(
    val context: Context,
    val launches: List<Launcher>,
    var callback: ((launcher: Launcher) -> Unit)
) : RecyclerView.Adapter<LaunchAdapter.LaunchViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LaunchViewHolder {
        val binding = ItemLaunchBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return LaunchViewHolder(binding)
    }

    override fun onBindViewHolder(holder: LaunchViewHolder, position: Int) {
        holder.bind(launches[position])
    }

    override fun getItemCount(): Int {
        return launches.size
    }

    inner class LaunchViewHolder(val binding: ItemLaunchBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(launcher: Launcher) {
            launcher.links?.flickr?.original?.let {
                if (it.isNotEmpty()) {
                    val options = RequestOptions().transform(CenterCrop(), RoundedCorners(20))
                    Glide.with(context)
                        .load(it[0])
                        .transition(DrawableTransitionOptions.withCrossFade())
                        .diskCacheStrategy(DiskCacheStrategy.NONE)
                        .apply(options)
                        .into(binding.backgroundImage)
                }
            }
            val date = if (launcher.upcoming) DateUtils.getVisualDate(launcher.date_unix?:0) else DateUtils.getVisualDate(launcher.static_fire_date_unix?: 0)
            binding.launchDate.text = date
            binding.flightNumber.text = launcher.flight_number.toString()
            binding.flightName.text = launcher.name
            binding.upcomingIndicator.visibility = if (launcher.upcoming) View.VISIBLE else View.GONE
            itemView.setOnClickListener {
                callback.invoke(launcher)
            }
        }
    }
}