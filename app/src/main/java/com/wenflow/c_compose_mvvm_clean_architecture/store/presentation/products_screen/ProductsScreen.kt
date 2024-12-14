package com.wenflow.c_compose_mvvm_clean_architecture.store.presentation.products_screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.wenflow.c_compose_mvvm_clean_architecture.store.presentation.utils.components.LoadingDialog
import com.wenflow.c_compose_mvvm_clean_architecture.store.presentation.utils.components.MyTopAppBar

//8.UI撰寫
/**
 * internal vs. public vs. private
 * public: 預設的可見性，任何地方都可以訪問。
 * private: 僅限於同一個類別或文件內。
 * internal: 介於 public 和 private 之間，限制在同一個模組內。
 * 有時候可能同一個類別名稱你不想用錯,或開放外部使用
 * 使用場景與詳細解釋
 * 假設你在開發一個大型應用程式，這個應用程式被分為多個模組，例如「資料處理」、「網路通訊」、「使用者介面」等。如果你希望某些功能僅在「資料處理」模組內部使用，而不暴露給其他模組，就可以使用 internal。
 *
 * 這樣做的好處是，你可以隱藏不需要讓外部模組知道的實作細節，減少其他開發者不小心誤用代碼的風險。
 *
 */

//9.ProductsScreen,Screen代表整個頁面,ViewModel將在此處接受
//@Composable
//internal fun ProductsScreen(
//    //22.ViewModel初始化
//    viewModel: ProductsViewModel = hiltViewModel()
//) {
//    //https://www.youtube.com/watch?v=TosPS55y_IY
//    // 影片43:13 collectAsStateWithLcycle 可能因為這個,需要改成java 17
//    // 23,要用implementation("androidx.lifecycle:lifecycle-runtime-compose:2.6.2")
//    /**
//     * 從 ViewModel 中的 StateFlow 收集值，並將其最新值表示為 State。
//     * 此版本考慮了 Composable 的生命週期來收集 StateFlow。
//     *
//     * `collectAsStateWithLifecycle` 函數確保當 Composable 處於有效的生命週期狀態（例如 STARTED）時開始收集，
//     * 當 Composable 不再處於有效狀態（例如 STOPPED）時停止收集。這有助於防止記憶體洩漏，
//     * 並確保您的 UI 只有在活躍時才會響應。
//     */
//    val state by viewModel.state.collectAsStateWithLifecycle()
//
//    ProductsContent(state = state)
//}

@Composable
internal fun ProductsScreen() {
    val viewModel: ProductsViewModel = hiltViewModel()
    val state by viewModel.state.collectAsStateWithLifecycle()
    ProductsContent(state = state)
}


//10.ProductsContent,Content代表一個模塊,這邊僅僅是UI處理
@Composable
fun ProductsContent(
    state: ProductsViewState
) {
    LoadingDialog(isLoading = state.isLoading)
    Scaffold(
        modifier = Modifier.fillMaxWidth(),
        topBar = { //14.設定寫好的topAppBar
            MyTopAppBar(title = "Products")
        }
    ) {
        //15.設定item
        LazyVerticalStaggeredGrid(
            modifier = Modifier.padding(top = it.calculateTopPadding()),
            columns = StaggeredGridCells.Fixed(2),
            horizontalArrangement = Arrangement.spacedBy(10.dp),
            verticalItemSpacing = 10.dp
        ) {

        }

    }

}


