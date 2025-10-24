package com.example.profilemy

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.io.IOException
import java.util.Locale


class Storage : AppCompatActivity(), View.OnClickListener {

    private lateinit var read: Button
    private lateinit var write: Button
    private lateinit var userInput: EditText
    private lateinit var fileContent: TextView
    private val filename = "my-file.txt"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_storage)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        read = findViewById(R.id.read_button)
        write = findViewById(R.id.write_button)
        userInput = findViewById(R.id.userInput)
        fileContent = findViewById(R.id.content)

        read.setOnClickListener(this)
        write.setOnClickListener(this)
    }

    override fun onClick(view: View) {
        val b = view as Button
        val bText = b.text.toString().lowercase(Locale.getDefault())

        when (bText) {
            "write" -> writeData()
            "read" -> readData()
        }
    }

    private fun writeData() {
        try {
            // Write the input text to the file
            val fos = openFileOutput(filename, MODE_PRIVATE)
            val data = userInput.text.toString()
            fos.write(data.toByteArray())
            fos.flush()
            fos.close()
        } catch (e: IOException) {
            e.printStackTrace()
        }

        userInput.setText(" ") // Clear the input field
        printMessage("Writing to file $filename completed...")
    }

    private fun readData() {
        try {
            // Read the content of the file
            val fin = openFileInput(filename)
            var a: Int
            val temp = StringBuilder()
            while ((fin.read().also { a = it }) != -1) {
                temp.append(a.toChar())
            }

            // Set the file content to the TextView
            fileContent.text = temp.toString()
            fin.close()
        } catch (e: IOException) {
            e.printStackTrace()
        }
        printMessage("Reading from file $filename completed...")
    }

    // A simple method to print messages to the fileContent TextView
    private fun printMessage(message: String) {
        fileContent.append(
            """
            
            $message
            """.trimIndent()
        )
    }
}
