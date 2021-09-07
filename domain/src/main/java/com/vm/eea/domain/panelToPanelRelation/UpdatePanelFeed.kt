package com.vm.eea.domain.panelToPanelRelation

import com.vm.eea.domain.ITransactionProvider
import com.vm.eea.domain.*
import com.vm.eea.domain.defaultGroundTemperature.DefaultTemperature
import com.vm.eea.domain.defaultGroundTemperature.IDefaultTemperatureRepository
import com.vm.eea.domain.defaultSiolResistivity.DefaultSoilResistivity
import com.vm.eea.domain.defaultSiolResistivity.IDefaultSoilResistivityRepository

class UpdatePanelFeed(private val transactionProvider: ITransactionProvider,
                      private val defaultTemperatureRepository: IDefaultTemperatureRepository,
                      private val defaultSoilResistivityRepository: IDefaultSoilResistivityRepository,
                      private val repository: IPanelToPanelRelationRepository) {

    suspend operator fun invoke(relationId:Long,length: Length){
        repository.updateLength(relationId,length)
    }

    suspend operator fun invoke(relationId:Long,value: MethodOfInstallation){
        repository.updateMethodOfInstallation(relationId,value)
    }

    suspend operator fun invoke(relationId:Long,value: VoltDrop){
        repository.updateVoltDrop(relationId,value)
    }

    suspend operator fun invoke(relationId:Long, value: Temperature, environment: Environment, addToDefaults:Boolean=true){
        transactionProvider.runAsTransaction {
            when(environment){
                Environment.Ambient -> repository.updateAmbientTemperature(relationId,value)
                Environment.Ground -> repository.updateGroundTemperature(relationId,value)
            }
            if(addToDefaults){
                val defaultTemperature=DefaultTemperature(value,true,environment,0)
                defaultTemperatureRepository.insert(defaultTemperature)
            }
        }


    }

    suspend operator fun invoke(relationId: Long, value: ThermalResistivity, addToDefaults: Boolean=true){
        if(addToDefaults){
            transactionProvider.runAsTransaction {
                repository.updateSoilThermalResistivity(relationId,value)
                val default=DefaultSoilResistivity(value,true,0)
                defaultSoilResistivityRepository.insert(default)

            }
        }else{
            repository.updateSoilThermalResistivity(relationId,value)
        }
    }

    suspend operator fun invoke(relationId:Long,value: Conductor){
        repository.updateConductor(relationId, value)
    }

    suspend operator fun invoke(relationId:Long,value: Insulation){
        repository.updateInsulation(relationId, value)
    }

    suspend operator fun invoke(relationId:Long,value: CircuitCount){
        repository.updateCircuitCount(relationId, value)
    }
}