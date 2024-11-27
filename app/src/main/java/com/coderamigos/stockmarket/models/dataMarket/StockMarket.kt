package com.coderamigos.stockmarket.models.dataMarket

import com.google.gson.annotations.SerializedName

data class StockMarket(
    @SerializedName("stocks")
    val stocks: Map<String, StockData>?
)
