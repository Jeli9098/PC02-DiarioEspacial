package com.esan.diarioespacial

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.MaterialTheme
import com.esan.diarioespacial.ui.navigation.AppNavigation

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            // Si tu template creó DiarioEspacialTheme { }, puedes usarlo en vez de MaterialTheme
            MaterialTheme {
                AppNavigation()
            }
        }
    }
}
