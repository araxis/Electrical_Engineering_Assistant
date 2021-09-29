package com.vm.eea.application.project.update

import com.vm.eea.application.Conductor
import com.vm.eea.application.project.IProjectRepository
import com.vm.eea.application.project.ProjectId

class UpdateProjectConductor(private val repository: IProjectRepository) {
     suspend operator fun invoke(projectId: ProjectId, conductor: Conductor) {
        repository.updateConductor(projectId,conductor)
    }
}