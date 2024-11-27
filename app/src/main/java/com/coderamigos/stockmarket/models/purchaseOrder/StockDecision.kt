package com.coderamigos.stockmarket.models.purchaseOrder

import com.google.gson.annotations.SerializedName

data class StockDecision(
    @SerializedName("symbol")
    val symbol: String,
    @SerializedName("days")
    val days: Int,
    @SerializedName("threshold")
    val threshold: Int,
    @SerializedName("decision")
    val decision: String,
    @SerializedName("percentage_change")
    val percentageChange: Double,
    @SerializedName("predictions")
    val predictions: Predictions
)
