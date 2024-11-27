package com.coderamigos.stockmarket.models.dataNews

import com.google.gson.annotations.SerializedName

data class NewsResponse(
    @SerializedName("news")
    val news: List<News>
)
