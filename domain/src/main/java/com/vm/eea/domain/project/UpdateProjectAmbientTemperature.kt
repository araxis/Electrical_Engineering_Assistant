package com.vm.eea.domain.project

import com.vm.eea.domain.Temperature
import com.vm.eea.domain.defaultGroundTemperature.DefaultTemperature
import com.vm.eea.domain.defaultGroundTemperature.IDefaultTemperatureRepository
import com.vm.eea.domain.ITransactionProvider
import com.vm.eea.domain.Environment

class UpdateProjectAmbientTemperature(
    private val transactionProvider: ITransactionProvider,
    private val projectRepository: IProjectRepository,
    private val defaultTemperatureRepository: IDefaultTemperatureRepository,
) {

    suspend operator fun invoke(projectId:Long, value: Temperature, addToDefaults:Boolean=false){

       if(addToDefaults){
           transactionProvider.runAsTransaction {
               projectRepository.updateSoilTemperature(projectId,value)
               val default=DefaultTemperature(value,true, Environment.Ambient,0)
               defaultTemperatureRepository.insert(default)
           }
       }else{
           projectRepository.updateSoilTemperature(projectId,value)
       }



    }
}