package com.wenflow.c_compose_mvvm_clean_architecture.store.presentation.products_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wenflow.c_compose_mvvm_clean_architecture.store.domain.repositroy.ProductRepository
import com.wenflow.c_compose_mvvm_clean_architecture.store.presentation.utils.sendEvent
import com.wenflow.c_compose_mvvm_clean_architecture.utils.Event
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * 17.設定ViewModel
 * @HiltViewModel 註解使這個 ViewModel 類別能夠被 Hilt 管理依賴注入。
 * @Inject 註解表示 Hilt 會自動注入這個 ViewModel 所需的 ProductRepository 實例。
 * 這樣你就不需要手動初始化 ProductRepository，Hilt 會自動處理。
 * ViewModel 用於儲存和管理與 UI 相關的數據，並在配置變更時保留數據的持久性。
 *
 * Android 技術和框架標籤
 * 1.@HiltViewModel:
 * Android 技術: Hilt 是 Android 的依賴注入框架，用來簡化依賴管理。@HiltViewModel 標籤表示 Hilt 會自動管理這個 ViewModel 的依賴注入。
 * 類似 Spring Boot: 在 Spring Boot 中，這類似於 @Component 或 @Service 標籤，用來標識一個 Spring 管理的組件，並且可以進行依賴注入。
 *
 * 2.
 * @Inject constructor:
 * Android 技術: @Inject 標籤用來標識 Hilt 需要自動注入的構造函數參數。這樣，Hilt 會自動提供這個構造函數需要的依賴。
 * 類似 Spring Boot: 在 Spring Boot 中，這類似於 @Autowired，用來自動注入一個組件的依賴。
 *
 * 3.
 * StateFlow 和 MutableStateFlow
 * Kotlin (StateFlow 和 MutableStateFlow):
 * StateFlow 和 MutableStateFlow 是 Kotlin 協程庫的一部分，用於管理和觀察狀態的變化。StateFlow 是一種可觀察的流，
 * 可以用來發送和接收狀態數據。MutableStateFlow 是 StateFlow 的可變版本，允許更新狀態的值。
 * 這些流提供了有效的方式來處理狀態變化，特別是在 Jetpack Compose 中，讓 UI 自動響應狀態變化。
 *
 * Java (LiveData 和 MutableLiveData):
 * 在 Java 中，對應於 Kotlin 的 StateFlow 和 MutableStateFlow 的技術是 LiveData 和 MutableLiveData。LiveData 是 Android 的一部分，設計用於觀察數據變化並自動更新 UI。MutableLiveData 是 LiveData 的可變版本，允許對數據進行修改並通知觀察者。
 * 使用 LiveData 和 MutableLiveData 可以實現類似於 Kotlin 流的數據觀察和狀態管理功能，但它們不支援協程的特性。
 *
 * 4.
 * Kotlin (viewModelScope):
 * viewModelScope 是一個協程範圍，特定於 ViewModel 的生命週期。它確保在 ViewModel 的生命週期內運行的協程會被自動取消，
 * 當 ViewModel 被銷毀時，所有在 viewModelScope 中啟動的協程會被自動取消。這可以防止記憶體洩漏並保持協程的正確運行。
 *
 * Java (AsyncTask 或其他異步操作機制):
 * 在 Java 中，沒有直接對應於 Kotlin viewModelScope 的技術，
 * 但可以使用 AsyncTask 來實現異步操作。AsyncTask 允許在背景執行任務，並在完成後更新 UI。雖然 AsyncTask 不會自動與 ViewModel 的生命週期相關聯，
 * 但它提供了一種在背景執行任務並處理結果的方式。
 * 其他類似技術還包括使用 ExecutorService 或RxJava 的異步處理機制。這些技術可以用來管理異步任務和數據流，但不會自動處理生命週期管理。
 *
 */
@HiltViewModel
class ProductsViewModel @Inject constructor(
    private val productRepository: ProductRepository // 依賴注入的 ProductRepository 實例
) : ViewModel() {

    // 用於管理產品狀態的 MutableStateFlow，初始化時為 ProductsViewState 的默認值
    private val _state = MutableStateFlow(ProductsViewState())

    // 將 _state 的 Flow 公開為只讀的 StateFlow 以供 UI 使用
    val state = _state.asStateFlow()

    //28.
    init {
        getProducts()
    }

    fun getProducts() {
        /**
         * 獲取產品資料的函數。
         * 使用 viewModelScope 來啟動一個協程，確保在 ViewModel 生命週期內運行。
         * 目前此函數尚未實作具體的資料獲取邏輯。
         */
        viewModelScope.launch {
            // 在協程中執行以下操作

            // 設定狀態為正在加載，這樣 UI 可以顯示加載指示器
            _state.update {
                it.copy(isLoading = true)
            }

            // 調用 productRepository 來獲取產品資料
            productRepository.getProducts()
                .onRight { products ->
                    // 當獲取產品成功時，更新狀態以顯示獲取到的產品
                    _state.update {
                        it.copy(products = products)
                    }
                }.onLeft { error ->
                    // 當獲取產品失敗時，更新狀態以顯示錯誤信息
                    _state.update {
                        it.copy(error = error.error.message)
                    }
                    //20.可以利用EventBus 以執行緒方式將錯誤訊息送給Toast
                    sendEvent(Event.Toast(error.error.message))
                }

            // 無論成功還是失敗，都應該在操作完成後關閉加載狀態
            _state.update {
                it.copy(isLoading = false) // 這樣註解解釋
            }
        }
    }
}