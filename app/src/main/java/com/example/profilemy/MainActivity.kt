package com.example.profilemy

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toolbar
import androidx.activity.ComponentActivity


class MainActivity : ComponentActivity() {

    private lateinit var editText: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val login : Button =findViewById(R.id.loginButton)// Initialising the UI Component ie log in button
        editText = findViewById(R.id.usernameInput) // initialised the edittext ui

        login.setOnClickListener {

            val username = editText.text.toString()  // to get the value from the ui into string
            val intent = Intent(applicationContext, HomePage::class.java)  // component Class/ Name  where activity to be linked
            intent.putExtra("UserName", username)   // pass the data/value to another activity which is linked(homepage)
            startActivity(intent)  // to start teh intent
        }
    }

    override fun onResume() {
        super.onResume()

        // Retrieving stored data from SharedPreferences
        val sharedPreferences = getSharedPreferences("UserPreferences", MODE_PRIVATE)
        val savedName = sharedPreferences.getString("user_name", "")

        // Populating EditText fields with stored data
        editText.setText(savedName)

    }

    override fun onPause() {
        super.onPause()

        // Storing data in SharedPreferences
        val sharedPreferences = getSharedPreferences("UserPreferences", MODE_PRIVATE)
        val editor = sharedPreferences.edit()

        // Retrieving user input and saving it
        editor.putString("user_name", editText.text.toString())
        editor.apply()
    }



}

