package com.example.parliamentapp.data

import androidx.lifecycle.LiveData


/**
 * Name: Yana Krylova
 * Student Number: 2113602
 * Description: Repository for all politician data
 */

class ParliamentRepository (parliamentDAO: ParliamentDAO) {

    val allData: LiveData<List<Parliament>> = parliamentDAO.readAllData()
    val allComment: LiveData<List<Comment>> = parliamentDAO.readAllComment()
    val allLike: LiveData<List<Like>> = parliamentDAO.readAllLike()
    val allDislike: LiveData<List<Dislike>> = parliamentDAO.readAllDislike()

//function that adds data to the database when the user adds a comment
    suspend fun addComment(comment: String, personNumber: Int) {
        ParliamentDataBase.getDatabase().parliamentDAO().addComment(Comment(0, comment, personNumber))
    }
//a function that adds data to the database when the user adds a like
    suspend fun addLike(like: Int, personNumber: Int) {
        ParliamentDataBase.getDatabase().parliamentDAO().addLike(Like(0, like, personNumber))
    }
//a function that adds data to the database when the user adds a dislike
    suspend fun addDislike(dislike: Int, personNumber: Int) {
        ParliamentDataBase.getDatabase().parliamentDAO().addDislike(Dislike(0, dislike, personNumber))
    }
}