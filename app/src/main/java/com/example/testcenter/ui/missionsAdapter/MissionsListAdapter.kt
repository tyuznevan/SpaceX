package com.example.testcenter.ui.missionsAdapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.testcenter.space.SpaceXEntity
import com.example.testcenter.databinding.RecViewItemBinding
import coil.load
import com.example.testcenter.utils.Utils

class MissionsListAdapter(private val event: (position: Int) -> Unit): RecyclerView.Adapter<MissionsListAdapter.MissionViewHolder>() {

    var data: ArrayList<SpaceXEntity> = arrayListOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    class MissionViewHolder(val binding: RecViewItemBinding) : RecyclerView.ViewHolder(binding.root){
        val logo = binding.logo
        val name = binding.name
        val success = binding.success
        val coresFlight = binding.coresFlight
        val date = binding.date
    }

    override fun getItemCount() = data.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MissionViewHolder {
        val binding = RecViewItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MissionViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MissionViewHolder, position: Int) {
        val item = data[position]

        holder.name.text = item.name
        holder.coresFlight.text = item.cores?.get(0)?.flight.toString()
        holder.success.text = item.success.toString()
        holder.date.text = Utils.getUtcDate(item.date_utc.toString())
        holder.logo.load(item.links?.patch?.small.toString())

        holder.binding.root.setOnClickListener{
            event.invoke(position)
        }
    }

}
