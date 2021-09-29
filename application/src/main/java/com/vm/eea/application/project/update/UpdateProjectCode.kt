package com.vm.eea.application.project.update

import com.vm.eea.application.project.IProjectRepository
import com.vm.eea.application.project.ProjectId

class UpdateProjectCode(private val projectRepository: IProjectRepository) {

     suspend operator fun invoke(projectId: ProjectId, code: String, description: String) {
        projectRepository.updateCode(projectId,code,description)
    }
}