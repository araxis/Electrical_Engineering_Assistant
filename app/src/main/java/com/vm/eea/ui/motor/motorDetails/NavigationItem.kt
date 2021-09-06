package com.vm.eea.ui.motor.motorDetails

import com.vm.eea.ui.INavigationCommand

data class NavigationItem(val name:String,val value:String,val visible:Boolean=true,val updateUiPath: INavigationCommand)