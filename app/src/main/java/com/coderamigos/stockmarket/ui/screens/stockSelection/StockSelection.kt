package com.coderamigos.stockmarket.ui.screens.stockSelection

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StockSelectionScreen(navController: NavController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Stock Selection") }
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
            Button(
                onClick = { navController.navigate("stockDataScreen") },
                modifier = Modifier.padding(8.dp)
            ) {
                Text("Go to Stock Data")
            }

            Button(
                onClick = { navController.navigate("stockNewsScreen") },
                modifier = Modifier.padding(8.dp)
            ) {
                Text("Go to Stock News")
            }
        }
    }
}