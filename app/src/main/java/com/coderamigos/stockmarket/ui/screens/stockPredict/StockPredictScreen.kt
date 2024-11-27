package com.coderamigos.stockmarket.ui.screens.stockPredict

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.coderamigos.stockmarket.ui.screens.stockData.StockChart

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StockPredictScreen(symbol: String, navController: NavController, viewModel: StockPredictViewModel = hiltViewModel()) {
    val predictionData by viewModel.predictionData.collectAsState()
    var symbol by remember { mutableStateOf(symbol) }
    var days by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Stock Prediction") }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            TextField(
                value = symbol,
                enabled = false,
                onValueChange = { symbol = it },
                label = { Text("Enter stock symbol") },
                modifier = Modifier.fillMaxWidth().padding(bottom = 16.dp)
            )

            TextField(
                value = days,
                onValueChange = { days = it },
                label = { Text("Enter number of days") },
                modifier = Modifier.fillMaxWidth().padding(bottom = 16.dp)
            )

            Button(
                onClick = { viewModel.fetchPredictData(symbol, days.toIntOrNull() ?: 0) },
                modifier = Modifier.padding(top = 16.dp)
            ) {
                Text("Get Prediction")
            }

            predictionData?.let { data ->

                Button(
                    onClick = { navController.navigate("stockDecisionScreen" + "/${symbol}") },
                    modifier = Modifier.padding(top = 16.dp)
                ) {
                    Text("Go to Purchase Decision")
                }

                StockChart(data = data.prediction, label = "Predicted Prices")
            }
        }
    }
}