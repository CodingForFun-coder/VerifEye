package com.example.verifeye

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebChromeClient
import android.webkit.WebSettings
import android.webkit.WebView
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment

class VideoFragment : Fragment() {

    private var param1: String? = null
    private var param2: String? = null
    private var webView: WebView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_video, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupWebView(view)
        setupWebView2(view)
        setupWebView3(view)
        setupWebView4(view)
    }

    private fun setupWebView(view: View) {
        webView = view.findViewById(R.id.firstYoutube)
        webView?.let { web ->
            val videoHtml = """
                <iframe width="100%" height="100%" src="https://www.youtube.com/embed/7voF2YTBeb8?rel=0&showinfo=0&autoplay=1" frameborder="0" allowfullscreen></iframe>
            """.trimIndent()

            with(web.settings) {
                javaScriptEnabled = true
                domStorageEnabled = true
                loadWithOverviewMode = true
                useWideViewPort = true
                mixedContentMode = WebSettings.MIXED_CONTENT_ALWAYS_ALLOW
            }

            web.loadDataWithBaseURL(null, videoHtml, "text/html", "utf-8", null)
            web.webChromeClient = WebChromeClient()
        }
    }

    private fun setupWebView2(view: View) {
        webView = view.findViewById(R.id.secondYoutube)
        webView?.let { web ->
            val videoHtml = "<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/dXouSNjuMX0?si=oeUr-eZ8RgCwP2MX\" title=\"YouTube video player\" frameborder=\"0\" allow=\"accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share\" referrerpolicy=\"strict-origin-when-cross-origin\" allowfullscreen></iframe>".trimIndent()

            with(web.settings) {
                javaScriptEnabled = true
                domStorageEnabled = true
                loadWithOverviewMode = true
                useWideViewPort = true
                mixedContentMode = WebSettings.MIXED_CONTENT_ALWAYS_ALLOW
            }

            web.loadDataWithBaseURL(null, videoHtml, "text/html", "utf-8", null)
            web.webChromeClient = WebChromeClient()
        }
    }

    private fun setupWebView3(view: View) {
        webView = view.findViewById(R.id.thirdYoutube)
        webView?.let { web ->
            val videoHtml = "<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/EdBiSLBxcXw?si=lV7-HZJLN2kn7S4E\" title=\"YouTube video player\" frameborder=\"0\" allow=\"accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share\" referrerpolicy=\"strict-origin-when-cross-origin\" allowfullscreen></iframe>".trimIndent()

            with(web.settings) {
                javaScriptEnabled = true
                domStorageEnabled = true
                loadWithOverviewMode = true
                useWideViewPort = true
                mixedContentMode = WebSettings.MIXED_CONTENT_ALWAYS_ALLOW
            }

            web.loadDataWithBaseURL(null, videoHtml, "text/html", "utf-8", null)
            web.webChromeClient = WebChromeClient()
        }
    }

    private fun setupWebView4(view: View) {
        webView = view.findViewById(R.id.fourthYoutube)
        webView?.let { web ->
            val videoHtml = "<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/v-8t0EfLzQo?si=-_GZ6jwgl1hW6unW\" title=\"YouTube video player\" frameborder=\"0\" allow=\"accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share\" referrerpolicy=\"strict-origin-when-cross-origin\" allowfullscreen></iframe>".trimIndent()

            with(web.settings) {
                javaScriptEnabled = true
                domStorageEnabled = true
                loadWithOverviewMode = true
                useWideViewPort = true
                mixedContentMode = WebSettings.MIXED_CONTENT_ALWAYS_ALLOW
            }

            web.loadDataWithBaseURL(null, videoHtml, "text/html", "utf-8", null)
            web.webChromeClient = WebChromeClient()
        }
    }

    companion object {
        private const val ARG_PARAM1 = "param1"
        private const val ARG_PARAM2 = "param2"

        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            VideoFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}
