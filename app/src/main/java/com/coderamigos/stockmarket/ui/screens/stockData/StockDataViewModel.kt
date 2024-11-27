package com.coderamigos.stockmarket.ui.screens.stockData

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.coderamigos.stockmarket.data.StockMarketRepository
import com.coderamigos.stockmarket.models.dataMarket.StockMarket
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class StockDataViewModel @Inject constructor(
    private val stockMarketRepository: StockMarketRepository
) : ViewModel() {

    private val _stockData = MutableStateFlow<StockMarket?>(null)
    val stockData: StateFlow<StockMarket?> = _stockData

    fun fetchStockData(symbols: String, period: String) {
        viewModelScope.launch {
            Log.d("StockDataViewModel", "fetchStockData: ")
            val stockMarketData = stockMarketRepository.getStockMarketData(symbols, period)
            Log.d("StockDataViewModel", "fetchStockData: $stockMarketData")
            _stockData.value = stockMarketData
        }
    }
}