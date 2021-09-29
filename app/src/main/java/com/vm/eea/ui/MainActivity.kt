package com.vm.eea.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.ExperimentalMaterialApi
import com.google.accompanist.pager.ExperimentalPagerApi
import org.koin.android.ext.android.inject

class MainActivity : ComponentActivity() {
    private val navigationManager: NavigationManager by inject()

    @ExperimentalPagerApi
    @ExperimentalMaterialApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
             setContent {

                EeaApp(navigationManager)

        }
    }
}









