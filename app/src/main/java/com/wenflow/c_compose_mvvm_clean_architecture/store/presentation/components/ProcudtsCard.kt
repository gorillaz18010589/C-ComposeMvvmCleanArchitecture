package com.wenflow.c_compose_mvvm_clean_architecture.store.presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.wenflow.c_compose_mvvm_clean_architecture.store.domain.models.Product

// 16. 設定 itemCard
@Composable
fun ProductCard(
    modifier: Modifier = Modifier, // 用於修改 Card 外觀的 Modifier，預設為空的 Modifier
    product: Product // 傳遞 Product 物件，包含要顯示的圖片和標題
) {
    // Card 是一個 Material Design 的組件，用來顯示包含內容的卡片
    Card(
        modifier = modifier, // 應用傳遞進來的 Modifier，設定 Card 的佈局參數
        shape = RoundedCornerShape(10.dp), // 設定 Card 的圓角為 10.dp
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp), // 設定 Card 的陰影高度為 2.dp
        colors = CardDefaults.cardColors(containerColor = Color.White) // 設定 Card 的背景顏色為白色
    ) {
        // Column 用來垂直排列內容
        Column(modifier = Modifier.padding(15.dp)) { // 設定 Column 內部的內邊距為 15.dp
            // 用於異步加載圖片並顯示
            AsyncImage(
                model = product.image, // 設定要加載的圖片來源
                contentDescription = null, // 設定圖片的內容描述，這裡為 null 代表不提供描述
                modifier = Modifier
                    .fillMaxHeight() // 讓圖片佔滿 Card 的高度
                    .aspectRatio(1f), // 設定圖片的長寬比為 1:1
                contentScale = ContentScale.FillBounds // 設定圖片縮放方式為填滿邊界
            )
            Spacer(modifier = Modifier.height(5.dp)) // 在圖片和標題之間添加 5.dp 高度的間隔
            Text(text = product.title, style = MaterialTheme.typography.titleMedium) // 顯示產品標題，使用 MaterialTheme 的標題樣式
        }
    }
}
