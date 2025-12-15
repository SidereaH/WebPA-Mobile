package com.webpa.mobile

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.webpa.mobile.presentation.navigation.BottomBar
import com.webpa.mobile.presentation.navigation.NavGraph
import com.webpa.mobile.ui.theme.WebpamobileTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WebpamobileTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.surface
                ) {
                    val navController = rememberNavController()

                    Scaffold(
                        bottomBar = { BottomBar(navController) }
                    ) { padding ->
                        NavGraph(
                            navController = navController,
                            modifier = Modifier.padding(padding)
                        )
                    }


                }
            }
        }
    }
}

