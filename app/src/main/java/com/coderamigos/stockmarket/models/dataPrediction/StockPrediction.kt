package com.coderamigos.stockmarket.models.dataPrediction
import com.google.gson.annotations.SerializedName

data class StockPrediction(
    @SerializedName("prediction")
    val prediction: Map<String, Double>
)
