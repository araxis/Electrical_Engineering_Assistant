package com.vm.eea.domain.project

import com.vm.eea.domain.UnitOfLength

class UpdateProjectUnitOfLength(private val projectRepository: IProjectRepository) {
    suspend operator fun invoke(projectId:Long,unit: UnitOfLength){
        projectRepository.updateUnitOfLength(projectId,unit)
    }
}