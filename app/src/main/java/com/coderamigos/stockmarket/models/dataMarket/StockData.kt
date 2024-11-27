package com.coderamigos.stockmarket.models.dataMarket

import com.google.gson.annotations.SerializedName

data class StockData(
    @SerializedName("Open")
    val open: Map<String, Double>,
    @SerializedName("High")
    val high: Map<String, Double>,
    @SerializedName("Low")
    val low: Map<String, Double>,
    @SerializedName("Close")
    val close: Map<String, Double>,
)

