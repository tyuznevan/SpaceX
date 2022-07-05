package com.example.testcenter.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.testcenter.R
import com.example.testcenter.SpaceX.data.remote.space.SpaceXEntity
import com.example.testcenter.databinding.RecViewItemBinding
import coil.load

class MissionsListAdapter(): RecyclerView.Adapter<MissionsListAdapter.MyViewHolder>() {

    var data: ArrayList<SpaceXEntity> = arrayListOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    class MyViewHolder(binding: RecViewItemBinding) : RecyclerView.ViewHolder(binding.root){
        val logo = binding.logo
        val name = binding.name
        val success = binding.success
        val coresFlight = binding.coresFlight
        val date = binding.date


    }


    override fun getItemCount() = data.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = RecViewItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val item = data[position]

        holder.name.text = item.name
        holder.coresFlight.text = item.cores?.get(0)?.flight.toString()
        holder.success.text = item.success.toString()
        holder.date.text = date(item.date_utc.toString())


        //holder.success.text = item.links?.patch?.small.toString()
        holder.logo.load(item.links?.patch?.small.toString())



    }

    private fun date(dateUtc: String): String {
        return  "${dateUtc[8]}${dateUtc[9]}-${dateUtc[5]}${dateUtc[6]}-${dateUtc[0]}${dateUtc[1]}${dateUtc[2]}${dateUtc[3]}"
    }

}
