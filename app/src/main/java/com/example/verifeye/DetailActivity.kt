package com.example.verifeye

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class DetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val nameTextView = findViewById<TextView>(R.id.nameDetailTextView)
        val detailsTextView = findViewById<TextView>(R.id.detailsDetailTextView)

        val mediaBiasInfo = intent.getSerializableExtra("MediaBiasInfo") as MediaBiasInfo?

        // Populate the TextViews
        mediaBiasInfo?.let {
            nameTextView.text = it.name
            val details = "Bias: ${it.bias}\nFactual: ${it.factual}\nCredibility: ${it.credibility}"
            detailsTextView.text = details
        }
    }
}
