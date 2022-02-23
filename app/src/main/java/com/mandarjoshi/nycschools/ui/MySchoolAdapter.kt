package com.mandarjoshi.nycschools.ui

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView

import com.mandarjoshi.nycschools.databinding.FragmentSchoolBinding

import com.mandarjoshi.nycschools.model.SchoolDetails

class MySchoolAdapter(
    private val items: List<SchoolDetails>,
    private val onItemClickListener: (String) -> Unit
) : RecyclerView.Adapter<MySchoolAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            onItemClickListener,
            FragmentSchoolBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.mItem = items[position]
        holder.schoolName.text = holder.mItem.schoolName
        holder.neighbourhood.text = holder.mItem.neighbourHood
        holder.id = holder.mItem.databaseNumber
    }

    override fun getItemCount(): Int {
        return items.size
    }

    class ViewHolder(onItemClickListener: (String) -> Unit, binding: FragmentSchoolBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val schoolName: TextView = binding.itemNumber
        val neighbourhood: TextView = binding.content
        lateinit var id: String
        lateinit var mItem: SchoolDetails
        override fun toString(): String {
            return super.toString() + " '" + neighbourhood.text + "'"
        }

        init {
            binding.root.setOnClickListener { onItemClickListener(id) }
        }
    }
}
