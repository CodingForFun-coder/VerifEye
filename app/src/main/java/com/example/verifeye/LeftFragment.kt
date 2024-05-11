package com.example.verifeye

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment

class LeftFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_left, container, false)

        val textView = view.findViewById<TextView>(R.id.leftNewsSources)

        val newsSources = listOf(
            "Concealed Nation",
            "Daily Torch",
            "Debate Post",
            "EconomicPolicyJournal.com",
            "Foundation for Defense of Democracies",
            "Policy Review",
            "Hudson Institute",
            "Influence Watch",
            "Keep and Bear",
            "Liberty & Law"
        )

        textView.text = newsSources.joinToString("\n")

        return view
    }
}

