package com.vm.eea.application.motor

import com.vm.eea.application.*
import com.vm.eea.application.project.ProjectId



data class Motor(val projectId: ProjectId,
                 val code:MotorCode,
                 val description:String,
                 val power: Power,
                 val powerfactor: CosPhi,
                 val demandFactor: CosPhi,
                 val applyLocalCosPhiCorrection:Boolean,
                 val efficiency: Efficiency,
                 val system: PowerSystem,
                 val startMode: StartMode,
                 val serviceMode: ServiceMode,
                 val powerSupplyPath: SupplyPath,
                 val feedingMode: FeedingMode,
                 val powerInWatt:Double)