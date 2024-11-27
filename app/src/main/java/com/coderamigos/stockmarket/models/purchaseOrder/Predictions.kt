package com.coderamigos.stockmarket.models.purchaseOrder

import com.google.gson.annotations.SerializedName

data class Predictions(
    @SerializedName("prediction")
    val prediction: Map<String, Double>
)
