package com.example.verifeye

import android.graphics.Typeface
import android.os.Bundle
import android.view.Gravity
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat

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

        val username = "Pedro Rangel"

        val userLayout = LinearLayout(this).apply {
            orientation = LinearLayout.HORIZONTAL
            layoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT)
            setPadding(10, 10, 10, 10)
        }

        val userAvatar = ImageView(this).apply {
            layoutParams = LinearLayout.LayoutParams(100, 100)
            setImageDrawable(ContextCompat.getDrawable(this@DiscussionActivity, R.drawable.nav_profile))
        }

        val tvUsername = TextView(this).apply {
            text = username
            textSize = 18f
            layoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT).apply {
                marginStart = 10
            }
        }

        userLayout.addView(userAvatar)
        userLayout.addView(tvUsername)
        newPostedTopics.addView(userLayout)

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

