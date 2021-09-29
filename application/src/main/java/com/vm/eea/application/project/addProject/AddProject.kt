package com.vm.eea.application.project.addProject

import com.vm.eea.application.CosPhi
import com.vm.eea.application.ITransactionProvider
import com.vm.eea.application.SupplyPath
import com.vm.eea.application.panel.IPanelRepository
import com.vm.eea.application.panel.Panel
import com.vm.eea.application.panel.PanelCode
import com.vm.eea.application.project.IProjectCodeExistChecker
import com.vm.eea.application.project.IProjectRepository


class AddProject(
    private val transactionProvider: ITransactionProvider,
    private val projectCodeExistChecker: IProjectCodeExistChecker,
    private val panelRepository: IPanelRepository,
    private val newProjectFactory: NewProjectFactory,
    private val projectRepository:IProjectRepository
){

    suspend operator fun invoke(code:String,description:String){
        val isExist=projectCodeExistChecker(code)
        if(isExist) throw ProjectCodeDuplicatedException("project code($code) is duplicated")
        transactionProvider.runAsTransaction {
            val newProject=newProjectFactory(code,description)
            val projectId= projectRepository.add(newProject)
            val mdp=Panel(projectId,
                PanelCode("MDP"),"Main distribution panel",true, CosPhi(.9), SupplyPath("/")
            )
            panelRepository.add(mdp)
        }
    }


}