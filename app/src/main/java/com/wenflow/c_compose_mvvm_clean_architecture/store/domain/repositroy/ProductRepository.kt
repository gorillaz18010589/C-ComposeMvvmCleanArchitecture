package com.wenflow.c_compose_mvvm_clean_architecture.store.domain.repositroy

import arrow.core.Either
import com.wenflow.c_compose_mvvm_clean_architecture.store.domain.models.NetworkError
import com.wenflow.c_compose_mvvm_clean_architecture.store.domain.models.Product

//https://www.youtube.com/watch?v=TosPS55y_IY 13:45
//https://developer.android.com/topic/architecture?hl=zh-tw
//api網址:https://fakestoreapi.com/products
interface ProductRepository {
    //3.Either<>：
    //Either 是一種函數型程式設計中的代數資料類型，通常用於表示兩種可能的結果之一。通常情況下，Either 類型有兩種：Left 和
    // Right，分別表示失敗和成功。例如，在處理錯誤時，Left 可以表示錯誤，而 Right 表示成功的結果。
    suspend fun getProducts(): Either<NetworkError, List<Product>>
}