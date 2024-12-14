package com.wenflow.c_compose_mvvm_clean_architecture

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.repeatOnLifecycle
import com.wenflow.c_compose_mvvm_clean_architecture.store.presentation.products_screen.ProductsScreen
import com.wenflow.c_compose_mvvm_clean_architecture.ui.theme.CComposeMVVMCleanArchitectureTheme
import com.wenflow.c_compose_mvvm_clean_architecture.utils.Event
import com.wenflow.c_compose_mvvm_clean_architecture.utils.EventBus
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CComposeMVVMCleanArchitectureTheme {
                val lifecycle = LocalLifecycleOwner.current.lifecycle
                LaunchedEffect(key1 = lifecycle) {
                    repeatOnLifecycle(state = Lifecycle.State.STARTED){
                        EventBus.events.collect{ event ->
                            if(event is Event.Toast){
                                Toast.makeText(this@MainActivity, event.message, Toast.LENGTH_SHORT).show()
                            }
                        }
                    }
                }
                Scaffold(
                    modifier = Modifier.fillMaxSize()
                ) {
                    // ProductsScreen() 需要放在 Scaffold 的 content lambda 裡面
                    ProductsScreen()
                }
            }
        }
    }
}

