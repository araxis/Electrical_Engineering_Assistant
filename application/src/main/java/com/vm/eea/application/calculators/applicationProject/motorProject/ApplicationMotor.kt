package com.vm.eea.application.calculators.applicationProject.motorProject

import com.vm.eea.application.*
import com.vm.eea.application.motor.MotorId
import com.vm.eea.application.project.ProjectId
import com.vm.eea.application.protectionDevice.ProtectionType

data class ApplicationMotor(
                       val projectCode: String,
                       val projectId: ProjectId,
                       val motorId: MotorId,
                       val power: Power,
                       val voltage: Voltage,
                       val powerfactor: CosPhi,
                       val demandFactor: CosPhi,
                       val applyLocalCosPhiCorrection:Boolean,
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
                       val protectionType: ProtectionType
)