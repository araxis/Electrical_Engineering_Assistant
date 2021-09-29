package com.vm.eea.application.project

import com.vm.eea.application.ThermalResistivity

interface IGetProjectSoilThermalResistivity {

    suspend operator fun invoke(projectId: ProjectId): ThermalResistivity
}