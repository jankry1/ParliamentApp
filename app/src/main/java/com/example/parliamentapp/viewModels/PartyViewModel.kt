package com.example.parliamentapp.viewModels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.example.parliamentapp.data.*

/**
 * Name: Yana Krylova
 * Student Number: 2113602
 * Description: viewModel for PartyFragment
 */

class PartyViewModel (application: Application): AndroidViewModel(application) {

    private val repository = ParliamentRepository(ParliamentDataBase.getDatabase(getApplication()).parliamentDAO())
    val parties: LiveData<List<String>> = Transformations.map(repository.allData) {
        extractParties(it)
    }
    private fun extractParties(memberList: List<Parliament>): List<String> {
        return memberList.map { it.party }.toSortedSet().toList()
    }
}