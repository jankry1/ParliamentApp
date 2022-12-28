package com.example.parliamentapp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.parliamentapp.R
import com.example.parliamentapp.databinding.FragmentMembersOfPartyBinding
import com.example.parliamentapp.viewModels.MembersOfPartyViewModel
import com.example.parliamentapp.adapters.ParliamentAdapter

/**
 * Name: Yana Krylova
 * Student number: 2113602
 * Description: displaying the members of the selected party in recyclerview
 */

class MembersOfParty : Fragment() {
    private lateinit var memberViewModel: MembersOfPartyViewModel
    private lateinit var adapter: ParliamentAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val binding = DataBindingUtil.inflate<FragmentMembersOfPartyBinding>(
            inflater, R.layout.fragment_members_of_party, container, false)

        val partyName = MembersOfPartyArgs.fromBundle(requireArguments()).partyName
        memberViewModel = ViewModelProvider(this)[MembersOfPartyViewModel::class.java]

        memberViewModel.members.observe(viewLifecycleOwner, Observer {
            adapter = ParliamentAdapter(requireContext(), memberViewModel.extractMembers(it, partyName))
            binding.membersList.adapter = adapter
        })

        binding.membersList.layoutManager = LinearLayoutManager(activity)

        (activity as AppCompatActivity).supportActionBar?.title = "Members of the party"

        return binding.root
    }
}