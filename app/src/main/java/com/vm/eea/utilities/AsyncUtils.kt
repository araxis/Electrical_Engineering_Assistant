package com.vm.eea.utilities

import androidx.compose.runtime.Composable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavDeepLink
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.vm.eea.ui.IDestination
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import java.util.concurrent.Executors


fun Int.isOdd()=this % 2 !=0

fun  NavGraphBuilder.composable(destination:IDestination,
                                deepLinks: List<NavDeepLink> = emptyList(),
                                content: @Composable (NavBackStackEntry) -> Unit) {

    this.composable(destination.route,destination.arguments,deepLinks,content)
}



fun ViewModel.onMain(body: suspend () -> Unit): Job {
    return viewModelScope.launch(Dispatchers.Main) {
        body()
    }
}

fun ViewModel.onIO(body: suspend () -> Unit): Job {
    return viewModelScope.launch(Dispatchers.IO) {
        body()
    }
}

fun ViewModel.onDefault(body: suspend () -> Unit): Job {
    return viewModelScope.launch(Dispatchers.Default) {
        body()
    }
}