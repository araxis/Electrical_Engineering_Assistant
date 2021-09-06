package com.vm.eea.ui

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class NavigationManager {

    private val _commands:MutableStateFlow<INavigationCommand> = MutableStateFlow(Destinations.Default())
    val commands:StateFlow<INavigationCommand> =_commands

    fun navigate(directions: INavigationCommand) {
        _commands.value = directions
    }

    fun back() {
        _commands.value=Destinations.Back()
    }


}