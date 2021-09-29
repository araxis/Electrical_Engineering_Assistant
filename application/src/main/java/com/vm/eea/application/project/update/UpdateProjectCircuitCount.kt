package com.vm.eea.application.project.update

import com.vm.eea.application.CircuitCount
import com.vm.eea.application.project.IProjectRepository
import com.vm.eea.application.project.ProjectId

class UpdateProjectCircuitCount(private val repository: IProjectRepository) {
     suspend operator fun invoke(projectId: ProjectId, value: CircuitCount) {
        repository.updateCircuitCount(projectId,value)
    }
}