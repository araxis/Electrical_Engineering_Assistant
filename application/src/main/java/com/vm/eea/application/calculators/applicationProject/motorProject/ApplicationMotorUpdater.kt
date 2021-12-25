package com.vm.eea.application.calculators.applicationProject.motorProject

import com.vm.eea.application.ITransactionProvider
import com.vm.eea.application.Power
import com.vm.eea.application.PowerSystem
import com.vm.eea.application.Voltage
import com.vm.eea.application.motor.IMotorRepository
import com.vm.eea.application.project.IProjectRepository

class ApplicationMotorUpdater(
    private val projectRepository: IProjectRepository,
    private val motorRepository: IMotorRepository,
    private val transactionProvider: ITransactionProvider,
){



    suspend fun update(motor: ApplicationMotor){
        transactionProvider.runAsTransaction {
              val originalMotor=motorRepository.getMotor(motor.motorId)
              val updated=originalMotor.copy(
                      demandFactor = motor.demandFactor,
                      powerfactor = motor.powerfactor,
                      startMode = motor.startMode,
                      protectionType = motor.protectionType,
                      isBiDirect = motor.isBiDirect,
                      hasOverLoadRelay = motor.hasOverLoadRelay,
                      ratedSpeed = motor.ratedSpeed,
                      serviceMode = motor.serviceMode,
                      system = motor.system,
                      efficiency = motor.efficiency,
                      powerInWatt = (motor.power to Power.Unit.W).value,
                      feedingMode = motor.feedingMode,
                      power = motor.power
                  )


            motorRepository.update(updated)
            val voltageInVolt = (motor.voltage to Voltage.Unit.V).value
            when(motor.system){
                PowerSystem.SinglePhase ->projectRepository.updateLineToNullVoltage(motor.projectId,voltageInVolt)
                PowerSystem.ThreePhase,PowerSystem.TwoPhase -> projectRepository.updateLineToLineVoltage(motor.projectId,voltageInVolt)
            }
        }
    }

}