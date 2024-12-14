package com.wenflow.c_compose_mvvm_clean_architecture.di

import androidx.compose.ui.unit.Constraints
import com.wenflow.c_compose_mvvm_clean_architecture.store.data.remote.ProductsApi
import com.wenflow.c_compose_mvvm_clean_architecture.utils.Constant
import com.wenflow.c_compose_mvvm_clean_architecture.utils.Constant.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

/**
 *23. dagger
 * Component： 代表一個依賴圖的根節點，用來提供物件。
 * Module： 提供物件的建構方法，通常用來提供單例物件或配置參數。
 * Provides： 標註在方法上，表示該方法提供了一個物件。
 * Inject： 標註在建構子或欄位上，表示該物件需要被注入。
 * */

/**
 * Dagger Hilt 模組，用於提供應用程式級別的依賴。
 *
 * 此模組使用 Retrofit 提供 ProductsApi 的單例實例，以進行網路請求。
 *
 * @InstallIn(SingletonComponent::class) - 指定此模組安裝在 SingletonComponent 中，
 * 這意味著提供的依賴將在應用程式的生命週期內存在。
 *
 * @Module - 標記此類為 Dagger 模組，包含用於提供依賴的方法。
 *
 * @Provides - 指示此方法將提供 ProductsApi 的實例。
 *
 * @Singleton - 確保提供的 ProductsApi 實例在整個應用程式中是單例。
 */
@InstallIn(Singleton::class)
@Module
object AppModule {

    @Provides
    @Singleton
    fun provideProductApi(): ProductsApi{
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ProductsApi::class.java)
    }

}
