package com.example.verifeye

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class DetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val nameTextView: TextView = findViewById(R.id.nameDetailTextView)
        val detailsTextView: TextView = findViewById(R.id.detailsDetailTextView)

        val mediaBiasInfo = intent.extras?.getSerializable("MediaBiasInfo") as? MediaBiasInfo

        mediaBiasInfo?.let {
            Log.d("DetailActivity", "Original Name: ${it.name}")
            val cleanName = it.name.replace(" – Bias and Credibility", "").trim()
            Log.d("DetailActivity", "Cleaned Name: $cleanName")

            nameTextView.text = cleanName
            val details = "Bias: ${it.bias}\nFactual: ${it.factual}\nCredibility: ${it.credibility}"
            detailsTextView.text = details
        }
    }
}
