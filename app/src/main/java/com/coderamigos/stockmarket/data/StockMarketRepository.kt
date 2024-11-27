package com.coderamigos.stockmarket.data

import com.coderamigos.stockmarket.api.ApiService
import com.coderamigos.stockmarket.models.dataMarket.StockMarket
import com.coderamigos.stockmarket.models.dataPrediction.StockPrediction
import com.coderamigos.stockmarket.models.purchaseOrder.StockDecision
import javax.inject.Inject

class StockMarketRepository @Inject constructor(
    private val apiService: ApiService
){
    suspend fun getStockMarketData(symbol: String, selectedPeriod: String): StockMarket? {
        return try {
            apiService.getRealTimeData(symbol, selectedPeriod)
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

    suspend fun getPredictData(symbol: String, period: Int): StockPrediction? {
        return try {
            apiService.getPredictData(symbol, period)
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

    suspend fun getDecisionData(symbol: String, period: Int, threshold: Int): StockDecision? {
        return try {
            apiService.getDecisionData(symbol, period, threshold)
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }
}