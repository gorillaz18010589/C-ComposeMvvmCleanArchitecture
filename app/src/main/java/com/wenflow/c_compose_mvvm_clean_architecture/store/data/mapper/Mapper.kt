package com.wenflow.c_compose_mvvm_clean_architecture.store.data.mapper

import com.wenflow.c_compose_mvvm_clean_architecture.store.domain.models.ApiError
import com.wenflow.c_compose_mvvm_clean_architecture.store.domain.models.NetworkError
import retrofit2.HttpException
import java.io.IOException

//6.因為implRequired:
//Either<NetworkError, List<Product>>
//Found:
//Either<Throwable, List<Product>>
//Either第一個型別是Throwable,所以我們透過這方式將我們定義的NetworkError,變成Throwable的
// 擴展函數：將 Throwable 轉換為 NetworkError
fun Throwable.toNetworkError(): NetworkError {
    // 根據異常的具體類型決定錯誤類型
    val error = when (this) {
        is IOException -> ApiError.NetWorkError // 如果異常是 IOException，表示網絡錯誤
        is HttpException -> ApiError.UnKnownResponse // 如果異常是 HttpException，表示未知的 HTTP 響應錯誤
        else -> ApiError.UnKnownError // 其他異常類型，表示未知錯誤
    }
    // 返回一個 NetworkError 實例，包含錯誤信息和原始異常
    return NetworkError(
        error = error, // 錯誤類型
        t = this // 原始異常
    )
}