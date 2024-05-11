package com.example.verifeye

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment


class RightFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_right, container, false)

        val textView = view.findViewById<TextView>(R.id.rightNewsSources)

        val newsSources = listOf(
            "Daily Dot",
            "Gizmodo",
            "GQ Magazine",
            "Haaretz",
            "Jezebel",
            "Meduza",
            "New Yorker",
            "New York Magazine",
            "People Magazine",
            "Raw Story"
        )

        textView.text = newsSources.joinToString("\n")

        return view
    }
}
