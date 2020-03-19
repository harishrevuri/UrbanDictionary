package com.example.urbandictionary.model

import com.google.gson.annotations.SerializedName

/**
 * @author harishrevuri created on March 18th 2020
 */
data class Search(val definition: String, val example: String, @SerializedName("thumbs_up") val thumbsUp: Int, @SerializedName("thumbs_down") val thumbDown: Int)