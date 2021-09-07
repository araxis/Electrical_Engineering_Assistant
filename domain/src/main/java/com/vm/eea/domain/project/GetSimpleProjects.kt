package com.vm.eea.domain.project

import kotlinx.coroutines.flow.Flow

class GetSimpleProjects(private val projectRepository: IProjectRepository) {
     operator fun invoke():Flow<List<SimpleProject>> {

         return projectRepository.getSimpleProjectsFlow()
     }
}

