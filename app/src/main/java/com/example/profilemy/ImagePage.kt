package com.example.profilemy

import android.graphics.BitmapFactory
import android.os.Bundle
import android.os.Environment
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import java.io.File

class ImagePage : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_image_page)

        val textView: TextView =findViewById(R.id.userName)//ui component ie text view to display user name

        val username = intent.getStringExtra("UserName") // get the value by use the key
        textView.text =username

        val imageView1 : ImageView = findViewById(R.id.imageView1)
        val imageView2 : ImageView = findViewById(R.id.imageView2)
        val imageView3 : ImageView = findViewById(R.id.imageView3)

        // 1️⃣ Image from drawable (already set in XML, optional here)
        imageView1.setImageResource(R.drawable.c1)

        // 2️⃣ Image from URL using Glide
        val imageUrl = "https://www.w3schools.com/images/picture.jpg"
        Glide.with(this)
            .load(imageUrl)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .into(imageView2)


        // 3️⃣ Image from local file

        val imag : String = "/storage/emulated/0/Download/images.jpeg"
        val file = File(imag)
        if (file.exists()) {
            Glide.with(this).load(file).into(imageView3)
        } else {
            Toast.makeText(this, "File not found ${file.absolutePath}", Toast.LENGTH_SHORT).show()
        }



    }
}