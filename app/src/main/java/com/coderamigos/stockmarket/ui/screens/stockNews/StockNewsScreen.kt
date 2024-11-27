package com.coderamigos.stockmarket.ui.screens.stockNews

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.coderamigos.stockmarket.models.dataNews.News

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StockNewsScreen(viewModel: StockNewsViewModel = hiltViewModel()) {
    var country by remember { mutableStateOf("") }
    val newsData by viewModel.newsData.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Stock News") }
            )
        }
    ) { paddingValues ->
        Column(modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)
            .padding(16.dp)
        ) {
            TextField(
                value = country,
                onValueChange = { country = it },
                label = { Text("Country") },
                modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp)
            )
            Button(
                onClick = {
                    viewModel.fetchNewsData(country)
                },
                modifier = Modifier.padding(top = 8.dp)
            ) {
                Text("Get News")
            }

            newsData?.let { data ->
                Spacer(modifier = Modifier.height(16.dp))
                LazyColumn {
                    items(data.news.size) { index ->
                        NewsCard(data.news[index])
                        Spacer(modifier = Modifier.height(8.dp))
                    }
                }
            }
        }
    }
}

@Composable
fun NewsCard(news: News) {
    val sentimentColor = when (news.titleSentiment) {
        "Positive" -> Color.Green
        "Negative" -> Color.Red
        else -> Color.Gray
    }

    Card(
        shape = RoundedCornerShape(8.dp),
        modifier = Modifier.fillMaxWidth().padding(8.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = news.titles, fontSize = 20.sp, fontWeight = FontWeight.Bold)
            Text(text = news.name, fontSize = 16.sp, fontWeight = FontWeight.Normal)
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(4.dp))
                    .background(sentimentColor)
                    .padding(4.dp)
            ) {
                Text(text = news.titleSentiment, fontSize = 16.sp, fontWeight = FontWeight.Normal, color = Color.White)
            }
        }
    }
}