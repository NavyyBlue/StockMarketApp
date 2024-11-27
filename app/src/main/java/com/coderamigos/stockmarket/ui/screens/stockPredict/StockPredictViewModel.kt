package com.coderamigos.stockmarket.ui.screens.stockPredict

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.coderamigos.stockmarket.data.StockMarketRepository
import com.coderamigos.stockmarket.models.dataPrediction.StockPrediction
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class StockPredictViewModel @Inject constructor(
    private val stockMarketRepository: StockMarketRepository
) : ViewModel() {

    private val _predictionData = MutableStateFlow<StockPrediction?>(null)
    val predictionData: StateFlow<StockPrediction?> = _predictionData

    fun fetchPredictData(symbol: String, days: Int) {
        viewModelScope.launch {
            val prediction = stockMarketRepository.getPredictData(symbol, days)
            Log.d("StockPredictViewModel", "fetchPredictData: $prediction")
            _predictionData.value = prediction
        }
    }
}