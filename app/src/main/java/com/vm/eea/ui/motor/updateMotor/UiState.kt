package com.vm.eea.ui.motor.updateMotor

import com.vm.eea.domain.PowerSystem
import com.vm.eea.domain.ServiceMode
import com.vm.eea.domain.SupplyPath
import com.vm.eea.domain.FeedingMode
import com.vm.eea.domain.LoadId
import com.vm.eea.domain.Motor
import com.vm.eea.domain.KW
import com.vm.eea.domain.toEfficiency
import com.vm.eea.domain.toPowerfactor

data class UiState(val motor: Motor) {
    companion object{
        fun init()=UiState(Motor(
            demandFactor = .9.toPowerfactor,
            powerfactor = .9.toPowerfactor,
            efficiency = 90.toEfficiency,
            code = "",
            power = 0.KW,
            description = "",
            projectId = -1,
            id = LoadId(-1),
            serviceMode = ServiceMode.Service,
            powerSupplyPath = SupplyPath("/"),
            feedingMode = FeedingMode(normal = true, emergency = false),
            system = PowerSystem.ThreePhase
        ))
    }
}