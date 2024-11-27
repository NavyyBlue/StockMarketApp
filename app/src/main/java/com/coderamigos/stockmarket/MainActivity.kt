package com.coderamigos.stockmarket

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.coderamigos.stockmarket.models.purchaseOrder.StockDecision
import com.coderamigos.stockmarket.ui.screens.stockData.StockDataScreenContent
import com.coderamigos.stockmarket.ui.screens.stockDecision.StockDecisionScreen
import com.coderamigos.stockmarket.ui.screens.stockPredict.StockPredictScreen
import com.coderamigos.stockmarket.ui.theme.StockMarketTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            StockMarketTheme {
                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = "stockDataScreen") {
                    composable("stockDataScreen") {
                        StockDataScreenContent(navController = navController)
                    }
                    composable("stockPredictScreen" + "/{symbol}") { backStackEntry ->
                        val symbol = backStackEntry.arguments?.getString("symbol")
                        StockPredictScreen(symbol = symbol ?: "", navController = navController)
                    }
                    composable("stockDecisionScreen" + "/{symbol}") { backStackEntry ->
                        val symbol = backStackEntry.arguments?.getString("symbol")
                        StockDecisionScreen(symbol = symbol ?: "", navController = navController)
                    }
                }
            }
        }
    }
}