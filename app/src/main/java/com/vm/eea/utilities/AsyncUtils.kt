package com.vm.eea.utilities

import androidx.compose.runtime.Composable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavDeepLink
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.vm.eea.ui.IDestination
import kotlinx.coroutines.*


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

fun <T> debounce(delayMillis: Long = 300L,scope: CoroutineScope,action: (T) -> Unit): (T) -> Unit {
    var debounceJob: Job? = null
    return { param: T ->
        if (debounceJob == null) {
            debounceJob = scope.launch {
                action(param)
                delay(delayMillis)
                debounceJob = null
            }
        }
    }
}

fun <T> debounceUntilLast(
    delayMillis: Long = 300L,
    scope: CoroutineScope,
    action: (T) -> Unit
): (T) -> Unit {
    var debounceJob: Job? = null
    return { param: T ->
        debounceJob?.cancel()
        debounceJob = scope.launch {
            delay(delayMillis)
            action(param)
        }
    }
}