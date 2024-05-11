package com.example.verifeye

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment

class CenterFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_center, container, false)

        val textView = view.findViewById<TextView>(R.id.centerNewsSources)

        val newsSources = listOf(
            "Ballotpedia",
            "C-Span",
            "Congress.gov",
            "FactCheck.org",
            "United State House of Representatives",
            "How Stuff Works",
            "How-To Geek",
            "Pew Research",
            "Reuters",
            "The Conversation"
        )

        textView.text = newsSources.joinToString("\n")

        return view
    }
}
