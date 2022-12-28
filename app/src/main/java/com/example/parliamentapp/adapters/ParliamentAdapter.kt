package com.example.parliamentapp.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.parliamentapp.R
import com.example.parliamentapp.fragments.MembersOfPartyDirections

/**
 * Name: Yana Krylova
 * Student Number: 2113602
 * Description: Here is an adapter for the Member Fragment RecyclerView. It sets the names of the members of the selected parliament party to the recyclerView.
 */

class MemberViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

class ParliamentAdapter(private val context: Context, var members: List<String>) :
    ListAdapter<String, MemberViewHolder>(MemberDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MemberViewHolder {
        val itemView = LayoutInflater.from(context).inflate(R.layout.text_item_view, parent, false)
        return MemberViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return members.size
    }

    override fun onBindViewHolder(holder: MemberViewHolder, i: Int) {
        (holder.itemView as TextView).apply {
            text = members[i]
            setOnClickListener {
                val action = MembersOfPartyDirections.actionMembersOfPartyFragmentToMemberFragment(text.toString())
                it.findNavController().navigate(action)
            }
        }
    }
}
// DiffUtil is a utility class that calculates the difference between two lists and outputs a list of update operations that converts the first list into the second one
// The callback that acts as a gateway to the backing list data
class MemberDiffCallback : DiffUtil.ItemCallback<String>() {
    override fun areItemsTheSame(old: String, new: String): Boolean {
        return old == new
    }
    override fun areContentsTheSame(old: String, new: String): Boolean {
        return old == new
    }
}