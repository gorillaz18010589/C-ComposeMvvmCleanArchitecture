package com.wenflow.c_compose_mvvm_clean_architecture.store.domain.models

//api網址:https://fakestoreapi.com/products
//1.在domain/model底下 產生Product data接受outPut,data省略了java get跟set
data class Product(
    val id: Int,
    val title: String,
    val price: Double,
    val description: String,
    val category: String,
    val image: String,
    val rating: Rating
)

data class Rating(
    val rate: Double,
    val count: Int
)
