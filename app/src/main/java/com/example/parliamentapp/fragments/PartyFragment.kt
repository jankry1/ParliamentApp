package com.example.parliamentapp.fragments

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.parliamentapp.MainActivity
import com.example.parliamentapp.R
import com.example.parliamentapp.adapters.PartyAdapter
import com.example.parliamentapp.databinding.FragmentPartyBinding
import com.example.parliamentapp.viewModels.PartyViewModel

/**
 * Name: Yana Krylova
 * Student Number: 2113602
 * Description: displaying all parties of the parliament in the recyclerview
 */

class Party : Fragment() {
    private lateinit var partyViewModel: PartyViewModel
    private lateinit var adapter: PartyAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = DataBindingUtil.inflate<FragmentPartyBinding>(
            inflater, R.layout.fragment_party, container, false)

        partyViewModel = ViewModelProvider(this)[PartyViewModel::class.java]
        adapter = PartyAdapter(requireContext(), partyViewModel.parties)

        partyViewModel.parties.observe(viewLifecycleOwner, Observer {(binding.partyList.adapter as PartyAdapter).notifyDataSetChanged()})
        binding.partyList.layoutManager = LinearLayoutManager(activity)
        binding.partyList.adapter = activity?.let {
            PartyAdapter(it, partyViewModel.parties)
        }

        (activity as AppCompatActivity).supportActionBar?.title = "Parliament parties"
        return binding.root
    }

    //navigation for menu
//    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//        return NavigationUI.onNavDestinationSelected(
//            item, requireView().findNavController()
//        ) || super.onOptionsItemSelected(item)
//    }
}