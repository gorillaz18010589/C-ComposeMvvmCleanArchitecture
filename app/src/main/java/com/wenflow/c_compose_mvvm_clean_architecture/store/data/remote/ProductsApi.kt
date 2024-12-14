package com.wenflow.c_compose_mvvm_clean_architecture.store.data.remote

import com.wenflow.c_compose_mvvm_clean_architecture.store.domain.models.Product
import retrofit2.http.GET

//4.不同業務api可以不同實作
//https://www.youtube.com/watch?v=TosPS55y_IY 17:49
interface ProductsApi {
    @GET("products")
    suspend fun getProducts() : List<Product>;
}