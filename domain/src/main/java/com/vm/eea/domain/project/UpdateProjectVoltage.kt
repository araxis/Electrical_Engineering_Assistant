package com.vm.eea.domain.project

import com.vm.eea.domain.ITransactionProvider
import com.vm.eea.domain.PowerSystem
import com.vm.eea.domain.Voltage
import com.vm.eea.domain.defaultVoltage.DefaultVoltage
import com.vm.eea.domain.defaultVoltage.IDefaultVoltageRepository

class UpdateProjectVoltage(
    private val transactionProvider: ITransactionProvider,
    private val projectRepository:IProjectRepository,
    private val defaultVoltageRepository: IDefaultVoltageRepository) {
    suspend operator fun invoke(projectId:Long, value: Voltage, system: PowerSystem, addToDefaults:Boolean){
          transactionProvider.runAsTransaction {
              when(system){
                  PowerSystem.SinglePhase -> projectRepository.updateOnePhaseVoltage(projectId,value)
                  PowerSystem.ThreePhase -> projectRepository.updateThreePhaseVoltage(projectId,value)
              }
              if(addToDefaults){
                  val default=DefaultVoltage(value,system,true,0)
                  defaultVoltageRepository.insert(default)
              }
          }
    }
}