package com.example.parliamentapp.viewModels

import android.app.Application
import com.example.parliamentapp.data.*
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
 * Name: Yana Krylova
 * Student Number: 2113602
 * Description: viewModel for MemberFragment
 */

class MemberViewModel(application: Application): AndroidViewModel(application) {

    private val repository = ParliamentRepository(ParliamentDataBase.getDatabase(getApplication()).parliamentDAO())

    val members: LiveData<List<Parliament>> = repository.allData
    val comment = repository.allComment
    val like = repository.allLike
    val dislike = repository.allDislike

    fun extractInfo(memberList: List<Parliament>, memberName: String): String {
        return memberList.filter { it.first + " " + it.last == memberName }.joinToString(" ") {
            "Seat number: " + it.seatNumber + "\nBorn year: " + it.bornYear + "\nMinister: " + if (it.minister) "Yes!" else "No" + "\nTwitter: " + if(it.twitter=="") "${it.first} doesn't have twitter account" else it.twitter
        }
    }

    fun extractPersonNumber(memberList: List<Parliament>, memberName: String): Int {
        return memberList.filter{it.first + " " + it.last == memberName}.map { it.personNumber }.joinToString(" ").toInt()
    }


    fun addLike(like: Int, personNumber: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addLike(like, personNumber)
        }
    }


    fun addDislike(dislike: Int, personNumber: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addDislike(dislike, personNumber)
        }
    }

    fun addComment(comment: String, personNumber: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addComment(comment, personNumber)
        }
    }
}
