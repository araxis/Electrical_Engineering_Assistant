package com.vm.eea.domain.project

import kotlinx.coroutines.flow.Flow

class GetProject(private val projectRepository: IProjectRepository) {
     operator fun invoke(projectId:Long): Flow<Project>{
        return projectRepository.getProjectFlow(projectId)
    }
}