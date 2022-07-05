package com.example.testcenter.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.testcenter.SpaceX.data.remote.space.SpaceXEntity
import com.example.testcenter.databinding.RecViewItemBinding
import coil.load

class MissionsListAdapter(val event: (position: Int) -> Unit): RecyclerView.Adapter<MissionsListAdapter.MissionViewHolder>() {

    var data: ArrayList<SpaceXEntity> = arrayListOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    class MissionViewHolder(binding: RecViewItemBinding) : RecyclerView.ViewHolder(binding.root){
        val logo = binding.logo
        val name = binding.name
        val success = binding.success
        val coresFlight = binding.coresFlight
        val date = binding.date
        val k = binding


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
        holder.date.text = date(item.date_utc.toString())



        holder.logo.load(item.links?.patch?.small.toString())

        holder.k.root.setOnClickListener{
            event.invoke(position)
        }

    }

    private fun date(dateUtc: String): String {
        return  "${dateUtc[8]}${dateUtc[9]}-${dateUtc[5]}${dateUtc[6]}-${dateUtc[0]}${dateUtc[1]}${dateUtc[2]}${dateUtc[3]}"
    }






}
