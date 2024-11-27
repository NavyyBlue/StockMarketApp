package com.coderamigos.stockmarket.models.dataNews

import com.google.gson.annotations.SerializedName

data class News(
    @SerializedName("titles")
    val titles: String,
    @SerializedName("summaries")
    val summaries: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("titlePolarity")
    val titlePolarity: Double,
    @SerializedName("titleSentiment")
    val titleSentiment: String,
    @SerializedName("summaryPolarity")
    val summaryPolarity: Double,
    @SerializedName("summarySentiment")
    val summarySentiment: String
)
