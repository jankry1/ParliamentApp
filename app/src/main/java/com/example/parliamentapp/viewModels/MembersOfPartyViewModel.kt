package com.example.parliamentapp.viewModels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.parliamentapp.data.Parliament
import com.example.parliamentapp.data.ParliamentDataBase
import com.example.parliamentapp.data.ParliamentRepository
/**
 * Name: Yana Krylova
 * Student Number: 2113602
 * Description: viewModel for MembersOfPartyFragment
 */

class MembersOfPartyViewModel(application: Application): AndroidViewModel(application) {

    private val repository = ParliamentRepository(ParliamentDataBase.getDatabase(getApplication()).parliamentDAO())
    val members: LiveData<List<Parliament>> = repository.allData

    fun extractMembers(memberList: List<Parliament>, partyName: String): List<String> {
        return memberList.filter { it.party == partyName }.map { it.first + " " + it.last}
    }
}
