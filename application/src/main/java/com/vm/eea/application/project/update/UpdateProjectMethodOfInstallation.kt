package com.vm.eea.application.project.update

import com.vm.eea.application.project.IProjectRepository
import com.vm.eea.application.MethodOfInstallation
import com.vm.eea.application.project.ProjectId

class UpdateProjectMethodOfInstallation(private val repository: IProjectRepository) {
     suspend operator fun invoke(projectId: ProjectId, value: MethodOfInstallation) {
        repository.updateMethodOfInstallation(projectId,value)
    }
}