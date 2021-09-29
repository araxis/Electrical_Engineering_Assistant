package com.vm.eea.application.project

import com.vm.eea.application.PowerSystem
import com.vm.eea.application.Voltage


interface IGetProjectVoltage {
    suspend operator fun invoke(projectId: ProjectId,system: PowerSystem): Voltage
}