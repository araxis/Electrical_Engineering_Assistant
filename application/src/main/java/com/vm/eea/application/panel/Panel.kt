package com.vm.eea.application.panel

import com.vm.eea.application.CosPhi
import com.vm.eea.application.SupplyPath
import com.vm.eea.application.project.ProjectId


data class Panel(val projectId: ProjectId,
                 val code: PanelCode,
                 val description:String,
                 val isMdp:Boolean,
                 val demandFactor: CosPhi,
                 val powerSupplyPath: SupplyPath

)