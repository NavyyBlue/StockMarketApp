package com.coderamigos.stockmarket.ui.screens.stockNews

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.coderamigos.stockmarket.data.StockMarketRepository
import com.coderamigos.stockmarket.models.dataNews.NewsRequest
import com.coderamigos.stockmarket.models.dataNews.NewsResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class StockNewsViewModel @Inject constructor(
    private val repository: StockMarketRepository
) : ViewModel() {

    private val _newsData = MutableStateFlow<NewsResponse?>(null)
    val newsData: StateFlow<NewsResponse?> = _newsData

    fun fetchNewsData(country: String) {
        viewModelScope.launch {
            val request = NewsRequest(country)
            val newsResponse = repository.analyzeNews(request)
            _newsData.value = newsResponse
        }
    }
}