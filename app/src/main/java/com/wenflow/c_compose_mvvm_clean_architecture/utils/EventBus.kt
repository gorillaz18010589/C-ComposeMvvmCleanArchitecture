package com.wenflow.c_compose_mvvm_clean_architecture.utils

import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow

//18.設定EventBus,來傳遞View跟ViewModel之間Toast訊息

/*sealed 是 Kotlin 中的一個關鍵字，用於創建密封類或密封接口。這些密封類型具有以下特性：

1. 密封類和密封接口（Sealed Classes and Interfaces）
密封類（Sealed Classes）

定義範圍： 密封類限制了所有可能的子類型，這些子類型必須在同一個檔案中定義。這意味著，密封類的所有子類都必須在其自身的檔案中聲明，或在密封類所在的檔案中聲明。
用途： 常用於處理有限的數據類型或狀態。例如，在處理不同的結果狀態時，密封類可以幫助確保所有可能的情況都被考慮到。
密封接口（Sealed Interfaces）

定義範圍： 與密封類類似，密封接口的所有實現也必須在相同的檔案中定義。
用途： 密封接口提供了一種方式來定義具有有限實現的接口，這些實現可以進一步用於模式匹配或類型處理。
2. 特性
所有子類必須在同一個檔案中： 密封類和密封接口的所有實現都必須在定義它們的檔案中或在同一個檔案中聲明。
有限的子類型： 密封類和密封接口的子類型是有限的，這使得編譯器能夠進行更嚴格的檢查，確保所有可能的子類型都被考慮到。
有助於模式匹配： 在使用密封類或密封接口時，編譯器可以幫助檢查所有可能的子類型，這使得模式匹配更加安全和全面。

類似java這樣
public class EventBus {
    private final PublishSubject<Object> eventSubject = PublishSubject.create();

    // 發送事件
    public void sendEvent(Object event) {
        eventSubject.onNext(event);
    }

    // 觀察事件
    public Disposable subscribeToEvents(io.reactivex.rxjava3.core.Consumer<Object> onNext) {
        return eventSubject.subscribe(onNext);
    }
}
*/
//第一版
//object EventBus{
//    //創建一個 Channel，用於儲存和傳遞事件。Channel 是 Kotlin 協程中的一種工具，用於在協程之間傳遞數據。
//    private val _events = Channel<Any>()
//
//    //將 Channel 轉換為 Flow。Flow 是 Kotlin 協程中的一種流式數據處理工具，用於異步地接收數據流。這樣可以讓觀察者以流的方式接收事件。
//    private val events = _events.receiveAsFlow()
//
//    //發送事件到 Channel。此函數是掛起函數（suspend function），因為發送事件是異步操作。
//    suspend fun sendEvent(event: Any){
//        _events.send(event)
//    }
//}
//
//sealed interface Event{
//    data class Toast(val message: String): Event
//    //舉例
////    data class dialog(val title: String, val text: String): Event
//
//}
object EventBus {
    private val _events = Channel<Any>()
    val events = _events.receiveAsFlow()

    suspend fun sendEvent(event: Any) {
        _events.send(event)
    }
}

sealed interface Event {
    data class Toast(val message: String) : Event
    object NavigateToHomeScreen : Event
}