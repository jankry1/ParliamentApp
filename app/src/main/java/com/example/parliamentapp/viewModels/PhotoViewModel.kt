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
 * Description: fetching the picture data from the database
 */
class PhotoViewModel(application: Application): AndroidViewModel(application) {

    private val repository = ParliamentRepository(ParliamentDataBase.getDatabase(getApplication()).parliamentDAO())
    val pictureUrl: LiveData<List<Parliament>> = repository.allData

    fun extractPictureUrl(memberList: List<Parliament>, memberName: String): String {
        return memberList.filter { it.first + " " + it.last == memberName }.joinToString(" ") { it.picture }
    }
}
