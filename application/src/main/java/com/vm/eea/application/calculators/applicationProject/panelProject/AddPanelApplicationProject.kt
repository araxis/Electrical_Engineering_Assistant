package com.vm.eea.application.calculators.applicationProject.panelProject

import com.vm.eea.application.*
import com.vm.eea.application.panel.CoincidenceFactor
import com.vm.eea.application.panel.IPanelRepository
import com.vm.eea.application.panel.Panel
import com.vm.eea.application.panel.PanelCode
import com.vm.eea.application.project.IProjectRepository
import com.vm.eea.application.project.NewProjectFactory
import com.vm.eea.application.calculators.applicationProject.motorProject.IApplicationProjectCodeResolver

class AddPanelApplicationProject(private val transactionProvider: ITransactionProvider,
                                 private val panelRepository: IPanelRepository,
                                 private val newProjectFactory: NewProjectFactory,
                                 private val projectRepository: IProjectRepository,
                                 private val applicationProjectCodeResolver: IApplicationProjectCodeResolver
) {

    suspend operator fun invoke(){
        val projectCode=applicationProjectCodeResolver.panelProjectCode()
        if(projectRepository.isExist(projectCode)) return
        val panelCode="MDP"
        transactionProvider.runAsTransaction {
            val newProject=newProjectFactory(projectCode,"")
            val projectId= projectRepository.add(newProject)
            val mdp= Panel(projectId,
                PanelCode(panelCode),"Main distribution panel",true,
                CosPhi(.9), CoincidenceFactor(1), SupplyPath("/1")
            )
            panelRepository.add(mdp)


        }
    }
}