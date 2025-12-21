package com.webpa.mobile

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController
import com.webpa.mobile.presentation.auth.SessionViewModel
import com.webpa.mobile.presentation.navigation.RootNavGraph
import com.webpa.mobile.ui.theme.WebpamobileTheme
import dagger.hilt.android.AndroidEntryPoint
@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WebpamobileTheme {
                val navController = rememberNavController()
                val sessionVm: SessionViewModel = hiltViewModel()

                val isAuthorized by sessionVm.isAuthorized.collectAsState()

                RootNavGraph(
                    navController = navController,
                    isAuthorized = isAuthorized
                )
            }
        }
    }
}




