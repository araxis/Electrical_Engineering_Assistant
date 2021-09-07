package com.vm.eea.domain.project

class UpdateProjectCode(private val projectRepository: IProjectRepository) {
    suspend operator fun invoke(projectId:Long,code:String,description:String){

            projectRepository.updateCode(projectId,code,description)
    }
}