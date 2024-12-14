package com.wenflow.c_compose_mvvm_clean_architecture.store.presentation.utils.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties

//12.設定Dialog
@Composable
fun LoadingDialog(isLoading: Boolean) {
    // 如果正在載入 (isLoading 為 true)，顯示對話框
    if (isLoading) {
        Dialog(
            // 當對話框外部被點擊或返回按鈕按下時觸發的事件 (通常用來關閉對話框)
            onDismissRequest = { /*TODO: 處理對話框關閉的邏輯*/ },

            // 設定對話框的屬性
            properties = DialogProperties(
                // 設定為 false，表示使用者點擊對話框外部時不會自動關閉對話框
                dismissOnClickOutside = false
            )
        ) {
            // 使用 Box 佈局來包裹對話框內容
            Box(
                // 設定對話框的寬度、圓角形狀及背景顏色
                modifier = Modifier
                    .width(200.dp) // 設定對話框的寬度為 200dp
                    .clip(RoundedCornerShape(15.dp)) // 設定對話框的圓角為 15dp
                    .background(Color.White), // 設定對話框的背景顏色為白色

                // 設定對話框內部內容的對齊方式為居中
                contentAlignment = Alignment.Center
            ) {
                // 顯示一個圓形進度條，並設定內部的 padding
                CircularProgressIndicator(
                    modifier = Modifier.padding(10.dp) // 設定圓形進度條的內間距為 10dp
                )
            }
        }
    }
}
