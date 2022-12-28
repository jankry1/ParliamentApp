package com.example.parliamentapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.example.parliamentapp.data.ParliamentApi
import com.example.parliamentapp.data.ParliamentDataBase
import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
/**
 * Name: Yana Krylova
 * Student Number: 2113602
 * Description: adds the data from the API to the database
 */

class MainActivity : AppCompatActivity() {

    //private lateinit var drawerLayout: DrawerLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        addMembers()
//        drawerLayout = binding.drawerLayout
        val navController = this.findNavController(R.id.myNavHostFragment)
        NavigationUI.setupActionBarWithNavController(this, navController)
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = this.findNavController(R.id.myNavHostFragment)
        return navController.navigateUp()
    }

    private fun addMembers() {
        GlobalScope.launch(
            Dispatchers.IO,
            CoroutineStart.DEFAULT) {
            try {
                val listResult = ParliamentApi.retrofitService.getMemberList()
                listResult.forEach {
                    ParliamentDataBase.getDatabase(applicationContext).parliamentDAO().addMember(it)
                }
            } catch (e: Exception) {
                println("Failure: ${e.message}")
            }
        }
    }
}



