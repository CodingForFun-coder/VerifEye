package com.example.verifeye

import android.graphics.Typeface
import android.os.Bundle
import android.view.Gravity
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity


class DiscussionActivity : AppCompatActivity() {
    private lateinit var topicTitle: EditText
    private lateinit var topicDescription: EditText
    private lateinit var newPostedTopics: LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_discussion)

        topicTitle = findViewById(R.id.topicTitle)
        topicDescription = findViewById(R.id.topicDescription)
        newPostedTopics = findViewById(R.id.newPostedTopics)
        val btnPostTopic: Button = findViewById(R.id.postTopic)
        btnPostTopic.setOnClickListener { postTopic() }
    }

    private fun postTopic() {
        val title = topicTitle.text.toString()
        val description = topicDescription.text.toString()

        val tvTitle = TextView(this).apply {
            text = title
            setTypeface(null, Typeface.BOLD)
            gravity = Gravity.CENTER_HORIZONTAL
            textSize = 25f
        }
        newPostedTopics.addView(tvTitle)

        val tvDescription = TextView(this).apply {
            text = description
            textSize = 20f
            setPadding(50, 10, 50, 10)
        }
        newPostedTopics.addView(tvDescription)

        topicTitle.text.clear()
        topicDescription.text.clear()
    }
}
