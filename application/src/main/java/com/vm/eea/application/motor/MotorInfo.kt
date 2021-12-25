package com.vm.eea.application.motor

import com.vm.eea.application.*
import com.vm.eea.application.project.ProjectId
import com.vm.eea.application.protectionDevice.ProtectionType

data class MotorInfo(val projectId: ProjectId,
                     val motorId: MotorId,
                     val code:MotorCode,
                     val description:String,
                     val power: Power,
                     val powerfactor: CosPhi,
                     val demandFactor: CosPhi,
                     val current: Current,
                     val apparentPower: ApparentPower,
                     val reactivePower: ReactivePower,
                     val efficiency: Efficiency,
                     val system: PowerSystem,
                     val startMode: StartMode,
                     val ratedSpeed: Speed,
                     val serviceMode: ServiceMode,
                     val powerSupplyPath: SupplyPath,
                     val feedingMode: FeedingMode,
                     val powerInWatt:Double,
                     val isBiDirect:Boolean,
                     val hasOverLoadRelay:Boolean,
                     val protectionType: ProtectionType,
                     val voltage: Voltage,

)