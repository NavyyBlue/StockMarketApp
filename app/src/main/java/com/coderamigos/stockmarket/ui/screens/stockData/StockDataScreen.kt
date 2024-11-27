package com.coderamigos.stockmarket.ui.screens.stockData

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StockDataScreenContent(navController: NavController, viewModel: StockDataViewModel = hiltViewModel()) {
    var symbols by remember { mutableStateOf("") }
    var expanded by remember { mutableStateOf(false) }
    var selectedPeriod by remember { mutableStateOf("1d") }
    val periods = listOf("1d", "5d", "1mo", "3mo", "6mo", "1y", "2y", "5y", "10y", "ytd", "max")
    val stockData = viewModel.stockData.collectAsState().value

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Stock Data") }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "Stock Data", modifier = Modifier.padding(16.dp))

            TextField(
                value = symbols,
                onValueChange = { symbols = it },
                label = { Text("Enter company symbols (comma separated)") },
                modifier = Modifier.padding(16.dp)
            )

            Row(
                modifier = Modifier.padding(16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ){
                Box(modifier = Modifier.padding(16.dp)) {
                    Button(onClick = { expanded = true }) {
                        Text(selectedPeriod)
                    }
                    DropdownMenu(
                        expanded = expanded,
                        onDismissRequest = { expanded = false }
                    ) {
                        periods.forEach { period ->
                            DropdownMenuItem(
                                onClick = {
                                    selectedPeriod = period
                                    expanded = false
                                },
                                text = { Text(period) }
                            )
                        }
                    }
                }

                Button(
                    onClick = { if(symbols.isNotEmpty()) viewModel.fetchStockData(symbols, selectedPeriod) },
                    modifier = Modifier.padding(16.dp)
                ) {
                    Text("Refresh")
                }
            }

            stockData?.let { data ->
                Button(
                    onClick = { navController.navigate("stockPredictScreen" + "/${symbols}") },
                    modifier = Modifier.padding(16.dp)
                ) {
                    Text("Go to Stock Predict")
                }

                Spacer(modifier = Modifier.height(16.dp))
                LazyColumn {
                    data.stocks?.forEach { (company, stockData) ->
                        item {
                            Text(text = "Company: $company", modifier = Modifier.padding(8.dp))
                            StockChart(data = stockData.open, label = "Open")
                            StockChart(data = stockData.high, label = "High")
                            StockChart(data = stockData.low, label = "Low")
                            StockChart(data = stockData.close, label = "Close")
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun StockChart(data: Map<String, Double>, label: String) {
    val points = data.values.map { String.format("%.2f", it).toDouble() }
    val dates = data.keys.toList()
    val maxValue = points.maxOrNull() ?: 0.0
    val minValue = points.minOrNull() ?: 0.0
    val isDarkTheme = isSystemInDarkTheme()

    val textColor = if (isDarkTheme) Color.White else Color.Black
    val lineColor = if (isDarkTheme) Color.Cyan else Color.Blue

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Text(text = label, color = textColor)
        Spacer(modifier = Modifier.height(8.dp))
        Canvas(modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
        ) {
            val width = size.width
            val height = size.height
            val pointDistance = width / (points.size - 1).coerceAtLeast(1)
            val intervalCount = 5
            val valueInterval = (maxValue - minValue) / intervalCount

            // Draw value intervals on the left
            for (i in 0..intervalCount) {
                val y = height - (i * height / intervalCount)
                drawContext.canvas.nativeCanvas.drawText(
                    String.format("%.2f", minValue + i * valueInterval),
                    0f,
                    y,
                    android.graphics.Paint().apply {
                        color = textColor.toArgb()
                        textSize = 24f
                    }
                )
            }

            // Draw start and end dates at the bottom
            if (dates.isNotEmpty()) {
                drawContext.canvas.nativeCanvas.drawText(
                    dates.first(),
                    0f,
                    height + 24f,
                    android.graphics.Paint().apply {
                        color = textColor.toArgb()
                        textSize = 24f
                    }
                )
                drawContext.canvas.nativeCanvas.drawText(
                    dates.last(),
                    width - 100f, // Adjust this value if needed
                    height + 24f,
                    android.graphics.Paint().apply {
                        color = textColor.toArgb()
                        textSize = 24f
                    }
                )
            }

            // Draw the line chart
            for (i in 0 until points.size - 1) {
                val startX = i * pointDistance
                val startY = height - ((points[i] - minValue) / (maxValue - minValue) * height).toFloat()
                val endX = (i + 1) * pointDistance
                val endY = height - ((points[i + 1] - minValue) / (maxValue - minValue) * height).toFloat()

                drawLine(
                    color = lineColor,
                    start = androidx.compose.ui.geometry.Offset(startX, startY),
                    end = androidx.compose.ui.geometry.Offset(endX, endY),
                    strokeWidth = 4f
                )
            }
        }
    }
}