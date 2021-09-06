package com.vm.eea.domain.project

import com.vm.eea.domain.Temperature
import com.vm.eea.domain.defaultGroundTemperature.DefaultTemperature
import com.vm.eea.domain.defaultGroundTemperature.IDefaultTemperatureRepository
import com.vm.eea.ITransactionProvider
import com.vm.eea.domain.Environment

class UpdateProjectTemperature(
    private val transactionProvider: ITransactionProvider,
    private val projectRepository: IProjectRepository,
    private val defaultTemperatureRepository: IDefaultTemperatureRepository,
) {

    suspend operator fun invoke(projectId:Long,value: Temperature,environment: Environment,addToDefaults:Boolean=false){

        transactionProvider.runAsTransaction {
            when(environment){
                Environment.Ambient -> projectRepository.updateAmbientTemperature(projectId,value)
                Environment.Ground -> projectRepository.updateSoilTemperature(projectId,value)
            }
            if(addToDefaults){
                val default=DefaultTemperature(value,true,environment,0)
                defaultTemperatureRepository.insert(default)
            }
        }






    }
}