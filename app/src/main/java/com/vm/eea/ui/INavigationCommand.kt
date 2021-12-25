package com.vm.eea.ui

interface INavigationCommand {

    val route: String

}

class NavigationCommand(override val route: String): INavigationCommand