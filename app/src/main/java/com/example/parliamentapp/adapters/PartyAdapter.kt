package com.example.parliamentapp.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.LiveData
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.parliamentapp.R
import com.example.parliamentapp.fragments.PartyDirections

/**
 * Name: Yana Krylova
 * Student Number: 2113602
 * Description:  Here is an adapter for the Party Fragment RecyclerView. It sets the parties to the recyclerView.
 */

class PartyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

class PartyAdapter(private val context: Context, var parties: LiveData<List<String>>) :
        ListAdapter<String, PartyViewHolder>(PartyDiffCallback()) {

    override fun getItemCount(): Int {
        return parties.value?.size ?: 0
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PartyViewHolder {
        val itemView = LayoutInflater.from(context).inflate(R.layout.text_item_view, parent, false)
        return PartyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: PartyViewHolder, i: Int) {
        (holder.itemView as TextView).apply {
            text = parties.value?.get(i)
            setOnClickListener {
                val action = PartyDirections.actionPartyFragmentToMembersOfPartyFragment(text.toString())
                it.findNavController().navigate(action)
            }
        }
    }
}
// DiffUtil is a utility class that calculates the difference between two lists and outputs a list of update operations that converts the first list into the second one
// The callback that acts as a gateway to the backing list data
class PartyDiffCallback : DiffUtil.ItemCallback<String>() {
    override fun areItemsTheSame(old: String, new: String): Boolean {
        return old == new }
    override fun areContentsTheSame(old: String, new: String): Boolean {
        return old == new
        }
    }