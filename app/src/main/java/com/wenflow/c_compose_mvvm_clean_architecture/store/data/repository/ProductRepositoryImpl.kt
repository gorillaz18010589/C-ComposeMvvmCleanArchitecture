package com.wenflow.c_compose_mvvm_clean_architecture.store.data.repository

import arrow.core.Either
import com.wenflow.c_compose_mvvm_clean_architecture.store.data.mapper.toNetworkError
import com.wenflow.c_compose_mvvm_clean_architecture.store.data.remote.ProductsApi
import com.wenflow.c_compose_mvvm_clean_architecture.store.domain.models.NetworkError
import com.wenflow.c_compose_mvvm_clean_architecture.store.domain.models.Product
import com.wenflow.c_compose_mvvm_clean_architecture.store.domain.repositroy.ProductRepository
import kotlinx.coroutines.CancellationException
import javax.inject.Inject

//5.實作ProductRepository
//承接26 -> 進行注入
class ProductRepositoryImpl @Inject constructor(
    private val productsApi: ProductsApi
) : ProductRepository {

    override suspend fun getProducts(): Either<NetworkError, List<Product>> {
        return Either.catch {
            productsApi.getProducts()
            //7.Mapper裡寫了擴充函示
            // 使用 mapLeft 對 Either.left 中的錯誤進行映射。
            // 如果 Either 中包含錯誤（即 Either.left），
            // 則調用 mapLeft 來將錯誤轉換為 NetworkError 類型。
        }.mapLeft { it.toNetworkError() }
    }
}

//舊版方式
//override suspend fun getProducts(): Either<NetworkError, List<Product>> {
//        try {
//            productsApi.getProducts()
//        }catch (e: Exception){
//            if(e is CancellationException){
//                throw e
//            }
//            e.printStackTrace()
//        }
//    }