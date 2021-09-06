package com.vm.eea.domain.project

import com.vm.eea.ITransactionProvider
import com.vm.eea.domain.PowerFactor
import com.vm.eea.domain.PowerSystem
import com.vm.eea.domain.defaultPowerfactor.AddNewDefaultPowerfactor
import com.vm.eea.domain.defaultPowerfactor.DefaultPowerfactor
import com.vm.eea.withTransaction

class UpdateProjectPowerfactor(
    private val transactionProvider: ITransactionProvider,
    private val addNewDefaultPowerfactor: AddNewDefaultPowerfactor,
    private val projectRepository: IProjectRepository,
){

    suspend operator fun invoke(projectId:Long, value: PowerFactor,system:PowerSystem, addToDefaults:Boolean=false){
        transactionProvider.withTransaction {
            when(system){
                PowerSystem.SinglePhase -> projectRepository.updateSinglePhasePowerFactor(projectId,value.value)
                PowerSystem.ThreePhase -> projectRepository.updateThreePhasePowerFactor(projectId,value.value)
            }
            if(addToDefaults){
               addNewDefaultPowerfactor(DefaultPowerfactor(value,system,true,0))
            }
        }
    }

}