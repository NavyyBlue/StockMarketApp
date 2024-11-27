package com.coderamigos.stockmarket.ui.screens.stockDecision

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.coderamigos.stockmarket.data.StockMarketRepository
import com.coderamigos.stockmarket.models.purchaseOrder.StockDecision
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class StockDecisionViewModel @Inject constructor(
    private val stockMarketRepository: StockMarketRepository
) : ViewModel() {

    private val _decisionData = MutableStateFlow<StockDecision?>(null)
    val decisionData: StateFlow<StockDecision?> = _decisionData

    fun fetchDecisionData(symbol: String, days: Int, threshold: Int) {
        viewModelScope.launch {
            val decision = stockMarketRepository.getDecisionData(symbol, days, threshold)
            Log.d("StockDecisionViewModel", "fetchDecisionData: $decision")
            _decisionData.value = decision
        }
    }
}