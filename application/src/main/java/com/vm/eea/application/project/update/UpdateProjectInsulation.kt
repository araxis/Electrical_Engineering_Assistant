package com.vm.eea.application.project.update

import com.vm.eea.application.project.IProjectRepository
import com.vm.eea.application.Insulation
import com.vm.eea.application.project.ProjectId

class UpdateProjectInsulation(private val repository: IProjectRepository) {
     suspend operator fun invoke(projectId: ProjectId, value: Insulation) {
        repository.updateInsulation(projectId,value)
    }
}