package com.vm.eea.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.InternalTextApi
import androidx.compose.ui.tooling.preview.Preview
import org.koin.android.ext.android.inject

class MainActivity : ComponentActivity() {
    private val navigationManager: NavigationManager by inject()
    @ExperimentalFoundationApi
    @ExperimentalMaterialApi
    @InternalTextApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        setContent {
            EeaApp(navigationManager)
        }
    }
}





@ExperimentalFoundationApi
@ExperimentalMaterialApi
@InternalTextApi
@Preview(showBackground = true)
@Composable
fun DefaultPreview() {

    EeaApp(NavigationManager())


}



