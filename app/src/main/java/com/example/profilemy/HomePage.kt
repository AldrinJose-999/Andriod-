package com.example.profilemy

import android.os.Bundle
import android.widget.Adapter
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class HomePage : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_page)// set tHe UI Components from layout

        val textView: TextView =findViewById(R.id.tvUserName)//ui component ie text view to display user name

        val username = intent.getStringExtra("UserName") // get the value by use the key
        textView.text =username

        val listView : ListView = findViewById(R.id.list_item)

        // Load array from strings.xml
        val fruits = resources.getStringArray(R.array.FruitsName)

        // Create ArrayAdapter
        val adapter = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1,
            fruits
        )

        // Set adapter to ListView
        listView.adapter = adapter
    }


}