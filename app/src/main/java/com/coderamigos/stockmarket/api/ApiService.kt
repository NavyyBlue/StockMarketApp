package com.coderamigos.stockmarket.api

import com.coderamigos.stockmarket.models.dataMarket.StockMarket
import com.coderamigos.stockmarket.models.dataNews.NewsRequest
import com.coderamigos.stockmarket.models.dataNews.NewsResponse
import com.coderamigos.stockmarket.models.dataPrediction.StockPrediction
import com.coderamigos.stockmarket.models.purchaseOrder.StockDecision
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface ApiService {

    @GET("/market/realtime")
    suspend fun getRealTimeData(
        @Query("symbols") symbols: String,
        @Query("period") period: String
    ): StockMarket

    @GET("/market/predict")
    suspend fun getPredictData(
        @Query("symbol") symbol: String,
        @Query("days") period: Int
    ): StockPrediction

    @GET("/market/decision")
    suspend fun getDecisionData(
        @Query("symbol") symbol: String,
        @Query("days") period: Int,
        @Query("threshold") threshold: Int
    ): StockDecision

    @POST("/news/analyze")
    suspend fun analyzeNews(
        @Body request: NewsRequest
    ): NewsResponse
}