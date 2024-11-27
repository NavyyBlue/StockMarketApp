package com.coderamigos.stockmarket.ui.screens.stockDecision

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.coderamigos.stockmarket.ui.screens.stockData.StockChart

@Composable
fun StockDecisionScreen(symbol : String, navController: NavController,viewModel: StockDecisionViewModel = hiltViewModel()) {
    var symbol by remember { mutableStateOf(symbol) }
    var days by remember { mutableStateOf("") }
    var threshold by remember { mutableStateOf("5") }
    val decisionData by viewModel.decisionData.collectAsState()

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        TextField(
            value = symbol,
            enabled = false,
            onValueChange = { symbol = it },
            label = { Text("Symbol") },
            modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp)
        )

        TextField(
            value = days,
            onValueChange = { days = it },
            label = { Text("Days") },
            modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp)
        )
        TextField(
            value = threshold,
            onValueChange = { threshold = it },
            label = { Text("Threshold") },
            modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp)
        )
        Button(
            onClick = {
                viewModel.fetchDecisionData(symbol, days.toIntOrNull() ?: 0, threshold.toIntOrNull() ?: 0)
            },
            modifier = Modifier.padding(top = 8.dp)
        ) {
            Text("Get Decision")
        }

        decisionData?.let { data ->
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = "Decision: ${data.decision}")
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "Percentage change: ${data.percentageChange}%")
            Spacer(modifier = Modifier.height(8.dp))
            StockChart(data = data.predictions.prediction, label = "Predicted Prices")
        }
    }
}