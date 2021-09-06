package com.vm.eea.ui

data class PropertyItem(val Name:String,
                        val Value:String,
                        val visible:Boolean=true,
                        val updateViewRoute:()->INavigationCommand )
