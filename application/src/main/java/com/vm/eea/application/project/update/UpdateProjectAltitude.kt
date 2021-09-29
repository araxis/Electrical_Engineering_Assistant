package com.vm.eea.application.project.update

import com.vm.eea.application.Length
import com.vm.eea.application.project.IProjectRepository
import com.vm.eea.application.project.ProjectId

class UpdateProjectAltitude(private val repository: IProjectRepository) {
     suspend operator fun invoke(projectId: ProjectId, altitude: Length) {
          repository.updateAltitude(projectId,altitude)
    }
}