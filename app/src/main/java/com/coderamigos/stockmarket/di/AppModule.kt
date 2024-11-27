package com.coderamigos.stockmarket.di

import com.coderamigos.stockmarket.api.ApiService
import com.coderamigos.stockmarket.data.StockMarketRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideApiService(): ApiService {
        return RetrofitInstance.api
    }

    @Provides
    @Singleton
    fun provideStockMarketRepository(apiService: ApiService): StockMarketRepository {
        return StockMarketRepository(apiService)
    }
}