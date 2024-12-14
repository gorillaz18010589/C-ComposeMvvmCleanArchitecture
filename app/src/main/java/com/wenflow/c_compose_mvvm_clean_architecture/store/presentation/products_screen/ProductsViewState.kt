package com.wenflow.c_compose_mvvm_clean_architecture.store.presentation.products_screen

import com.wenflow.c_compose_mvvm_clean_architecture.store.domain.models.Product

//11.創建ProductsViewState
data class ProductsViewState(
    val isLoading: Boolean = false,
    val products: List<Product> = emptyList(), //products 預設為一個已初始化的空列表，並且是不可變的
    val error: String? = null // 錯誤訊息，預設為 null，表示沒有錯誤 java -> private final String error = null;
)