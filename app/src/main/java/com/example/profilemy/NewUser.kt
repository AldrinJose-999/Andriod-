package com.example.profilemy

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class NewUser : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_new_user)

        val newUser: EditText = findViewById(R.id.newUser)
        val addDB: Button = findViewById(R.id.AddDB)
        val readDB: Button = findViewById(R.id.ReadDB)
        val dbListView: ListView = findViewById(R.id.listdb)
        val dbHelper = DBHandler(this)

// Add data to database
        addDB.setOnClickListener {
            val name = newUser.text.toString()
            if (name.isNotEmpty()) {
                val isInserted = dbHelper.insertUser(name)
                if (isInserted) {
                    Toast.makeText(this, "User Added", Toast.LENGTH_SHORT).show()
                    newUser.text.clear()
                } else {
                    Toast.makeText(this, "Error Adding User", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "Enter a name first!", Toast.LENGTH_SHORT).show()
            }
        }

// Read data from database
        readDB.setOnClickListener {
            val dataList = dbHelper.getAllUsers() // get list of user names
            if (dataList.isNotEmpty()) {
                // Create ArrayAdapter
                val adapter = ArrayAdapter(
                    this,
                    R.layout.list_item,
                    R.id.itemText,
                    dataList.toMutableList()
                )


                dbListView.adapter = adapter

            } else {
                Toast.makeText(this, "No records found", Toast.LENGTH_SHORT).show()
            }
        }


    }




}