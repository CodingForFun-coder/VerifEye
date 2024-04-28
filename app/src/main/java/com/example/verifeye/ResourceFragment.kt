package com.example.verifeye

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment

class ResourceFragment : Fragment() {

    private var article: TextView? = null
    private var article2: TextView? = null
    private var video: TextView? = null
    private var video2: TextView? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_resource, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        article = view.findViewById(R.id.articleLink)
        article?.setOnClickListener { gotoUrl("https://libguides.uwgb.edu/bias/") }

        article2 = view.findViewById(R.id.articleLink2)
        article2?.setOnClickListener { gotoUrl("https://libguides.bridgewater.edu/c.php?g=944802&p=6811022") }

        video = view.findViewById(R.id.videoLink)
        video?.setOnClickListener { gotoUrl("https://www.youtube.com/watch?v=6F0g4N415uw") }

        video2 = view.findViewById(R.id.videoLink2)
        video2?.setOnClickListener { gotoUrl("https://www.youtube.com/watch?v=ccK9TTA6xiw") }
    }

    private fun gotoUrl(s: String) {
        val uri = Uri.parse(s)
        val intent = Intent(Intent.ACTION_VIEW, uri)
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        startActivity(intent)
    }

}
