package com.wenflow.c_compose_mvvm_clean_architecture.store.presentation.utils

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wenflow.c_compose_mvvm_clean_architecture.utils.EventBus
import kotlinx.coroutines.launch

/**
 * 19.
 * 讓 ViewModel 能夠在協程中異步地發送事件到 EventBus。這樣做的好處包括：
 * 協程支持: 使用 viewModelScope 確保協程的生命週期與 ViewModel 保持一致，有助於處理異步操作。
 * 擴展函數: 讓 ViewModel 能夠輕鬆地調用 sendEvent 方法，發送事件而不需要直接處理 EventBus 的細節。
 * 非阻塞: 事件發送是非阻塞的，這意味著 ViewModel 的其他操作不會因為發送事件而被阻塞。
 * 這樣的設計有助於實現乾淨的架構，將事件發送的細節隱藏在 ViewModel 擴展函數中，並利用協程進行異步處理。**/
fun ViewModel.sendEvent(event: Any) {
    viewModelScope.launch {
        EventBus.sendEvent(event)
    }
}