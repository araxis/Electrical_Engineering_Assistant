package com.vm.eea.ui.motor.motorResult

import com.vm.eea.ui.GroupPropertyItem

data class MotorResultState(val resultItems:List<GroupPropertyItem> = listOf(),
                            val report:String="",val preview:Boolean=false)