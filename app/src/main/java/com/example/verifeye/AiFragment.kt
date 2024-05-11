package com.example.verifeye

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import okhttp3.Call
import okhttp3.Callback
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody
import okhttp3.Response
import org.json.JSONObject
import java.io.IOException

class AiFragment : Fragment() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var welcomeTextView: TextView
    private lateinit var messageEditText: EditText
    private lateinit var sendButton: ImageButton
    private var messageList: MutableList<MessageAI> = mutableListOf()
    private lateinit var messageAdapter: MessageAdapter
    private val client = OkHttpClient.Builder().build()
    private val jsonMediaType = "application/json; charset=utf-8".toMediaTypeOrNull()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_ai, container, false)
        recyclerView = view.findViewById(R.id.recyclerView)
        welcomeTextView = view.findViewById(R.id.welcomeText)
        messageEditText = view.findViewById(R.id.messageText)
        sendButton = view.findViewById(R.id.sendButton)

        messageAdapter = MessageAdapter(messageList)
        recyclerView.apply {
            adapter = messageAdapter
            layoutManager = LinearLayoutManager(context).apply { stackFromEnd = true }
        }

        sendButton.setOnClickListener {
            val question = messageEditText.text.toString().trim()
            if (question.isNotEmpty()) {
                addToChat(question, MessageAI.SENT_BY_ME)
                messageEditText.setText("")
                callAPI(question)
                welcomeTextView.visibility = View.GONE
            }
        }

        return view
    }

    private fun addToChat(message: String, sentBy: String) {
        activity?.runOnUiThread {
            messageList.add(MessageAI(message, sentBy))
            messageAdapter.notifyDataSetChanged()
            recyclerView.smoothScrollToPosition(messageAdapter.itemCount - 1)
        }
    }

    private fun addResponse(response: String) {
        activity?.runOnUiThread {
            messageList.removeAt(messageList.size - 1)
            addToChat(response, MessageAI.SENT_BY_BOT)
        }
    }

    private fun callAPI(question: String) {
        messageList.add(MessageAI("Typing...", MessageAI.SENT_BY_BOT))

        val jsonBody = JSONObject().apply {
            put("model", "gpt-3.5-turbo-instruct")
            put("prompt", question)
            put("max_tokens", 4000)
            put("temperature", 0)
        }

        val body = RequestBody.create(jsonMediaType, jsonBody.toString())
        val request = Request.Builder()
            .url("https://api.openai.com/v1/completions")
            .header("Authorization", "Bearer sk-proj-RPQ9i07xjjsCAXWscVzWT3BlbkFJBPYrlG7DSk1NOqQkbw5r")
            .post(body)
            .build()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                addResponse("Failed to load response due to ${e.message}")
            }

            override fun onResponse(call: Call, response: Response) {
                if (response.isSuccessful) {
                    val resultText = JSONObject(response.body?.string() ?: "").getJSONArray("choices")
                        .getJSONObject(0).getString("text").trim()
                    addResponse(resultText)
                } else {
                    addResponse("Failed to load response due to server error: ${response.code}")
                }
            }
        })
    }
}

