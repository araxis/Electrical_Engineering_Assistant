package com.vm.eea.data.project

import com.vm.eea.application.ThermalResistivity
import com.vm.eea.application.project.IGetProjectSoilThermalResistivity
import com.vm.eea.application.project.ProjectId
import com.vm.eea.data.AppDatabase

class GetProjectSoilThermalResistivity(private val db:AppDatabase):IGetProjectSoilThermalResistivity {
    override suspend fun invoke(projectId: ProjectId): ThermalResistivity {
        return db.projectReadDao().getProjectSoilThermalResistivity(projectId.id)
            .let { ThermalResistivity(it.value,it.unit) }
    }
}