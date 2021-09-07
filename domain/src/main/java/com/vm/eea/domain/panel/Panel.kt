package com.vm.eea.domain.panel

import com.vm.eea.domain.SupplyPath


data class Panel(val projectId: Long,
                 val code: String,
                 val description:String,
                 val isMdp:Boolean,
                 val powerSupplyPath: SupplyPath = SupplyPath("/"),
                 val id: Long=0){

}
