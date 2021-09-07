package com.vm.eea.domain.project

import com.vm.eea.domain.ThermalResistivity
import com.vm.eea.domain.defaultSiolResistivity.DefaultSoilResistivity
import com.vm.eea.domain.defaultSiolResistivity.IDefaultSoilResistivityRepository
import com.vm.eea.domain.ITransactionProvider

class UpdateProjectSoilResistivity(
    private val transactionProvider: ITransactionProvider,
    private val projectRepository: IProjectRepository,
    private val defaultSoilResistivityRepository: IDefaultSoilResistivityRepository
) {

    suspend operator fun invoke(projectId:Long, value: ThermalResistivity, addToDefaults:Boolean=false){

       if(addToDefaults){
           transactionProvider.runAsTransaction {
               projectRepository.updateSoilResistivity(projectId,value)
               val defaultAltitude=DefaultSoilResistivity(value,true,0)
               defaultSoilResistivityRepository.insert(defaultAltitude)
           }
       }else{
           projectRepository.updateSoilResistivity(projectId,value)
       }



    }
}