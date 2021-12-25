package com.vm.eea.application.calculators.applicationProject.motorProject

import com.vm.eea.application.*
import com.vm.eea.application.motor.addMotor.AddMotor
import com.vm.eea.application.panel.CoincidenceFactor
import com.vm.eea.application.panel.IPanelRepository
import com.vm.eea.application.panel.Panel
import com.vm.eea.application.panel.PanelCode
import com.vm.eea.application.project.IProjectRepository
import com.vm.eea.application.project.NewProjectFactory
import com.vm.eea.application.protectionDevice.ProtectionType

class AddMotorApplicationProject(private val transactionProvider: ITransactionProvider,
                                 private val panelRepository: IPanelRepository,
                                 private val newProjectFactory: NewProjectFactory,
                                 private val projectRepository: IProjectRepository,
                                 private val addMotor: AddMotor,
                                 private val applicationProjectCodeResolver: IApplicationProjectCodeResolver
) {

    suspend operator fun invoke(){
        val projectCode=applicationProjectCodeResolver.motorProjectCode()
        if(projectRepository.isExist(projectCode)) return
        val motorCode="M1"
        val panelCode="MDP"
        transactionProvider.runAsTransaction {
            val newProject=newProjectFactory(projectCode,"")
            val projectId= projectRepository.add(newProject)
            val mdp= Panel(projectId,
                PanelCode(panelCode),"Main distribution panel",true,
                CosPhi(.9), CoincidenceFactor(1), SupplyPath("/1")
            )
           val panelId= panelRepository.add(mdp)
           addMotor(motorCode,"",0.KW, CosPhi(.85),
                CosPhi(.9),false,Efficiency(90),Speed(1200),isBiDirect = false,
                false,ProtectionType.CircuitBreaker,
                PowerSystem.ThreePhase,StartMode.Dol,FeedingMode(true, emergency = true),panelId,
                Length(10,Length.Unit.M))

        }
    }
}