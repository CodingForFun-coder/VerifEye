package com.example.verifeye

import java.io.Serializable
data class MediaBiasInfo(
    val name: String,
    val url: String,
    val bias: String,
    val factual: String,
    val credibility: String
): Serializable
