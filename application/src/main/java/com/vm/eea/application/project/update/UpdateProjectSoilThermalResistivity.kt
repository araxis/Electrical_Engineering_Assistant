package com.vm.eea.application.project.update

import com.vm.eea.application.project.IProjectRepository
import com.vm.eea.application.ThermalResistivity
import com.vm.eea.application.project.ProjectId

class UpdateProjectSoilThermalResistivity(private val repository: IProjectRepository) {
     suspend operator fun invoke(projectId: ProjectId, value: ThermalResistivity) {
        repository.updateThermalResistivity(projectId,value)
    }
}