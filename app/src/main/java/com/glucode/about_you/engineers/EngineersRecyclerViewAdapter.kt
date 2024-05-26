package com.glucode.about_you.engineers

import android.content.Context
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.glucode.about_you.databinding.ItemEngineerBinding
import com.glucode.about_you.engineers.models.Engineer
import java.io.File

class EngineersRecyclerViewAdapter(
    private var engineers: List<Engineer>,
    private val onClick: (Engineer) -> Unit
) : RecyclerView.Adapter<EngineersRecyclerViewAdapter.EngineerViewHolder>() {

    private lateinit var context: Context

    override fun getItemCount() = engineers.count()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EngineerViewHolder {
        context = parent.context
        return EngineerViewHolder(ItemEngineerBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: EngineerViewHolder, position: Int) {
        holder.bind(engineers[position], onClick)
    }

    inner class EngineerViewHolder(private val binding: ItemEngineerBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(engineer: Engineer, onClick: (Engineer) -> Unit) {
            binding.name.text = engineer.name
            binding.role.text = engineer.role
            binding.root.setOnClickListener {
                onClick(engineer)
            }

            if (engineer.defaultImageName.isNotEmpty()) {
                val imageUri = Uri.fromFile(File(context.filesDir, engineer.defaultImageName))

                binding.profileImage.setColorFilter(context.getColor(android.R.color.transparent))
                Glide.with(binding.root)
                    .load(imageUri)
                    .centerCrop()
                    .skipMemoryCache(true)
                    .diskCacheStrategy(DiskCacheStrategy.NONE)
                    .into(binding.profileImage)
            }
        }
    }
}