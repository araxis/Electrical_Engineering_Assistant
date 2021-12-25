package com.vm.eea.application.calculators.motorFullResult

import com.vm.eea.application.motor.IMotorRepository
import com.vm.eea.application.motor.MotorId

class PanelMotorResultCalculator(private val calculator:MotorResultCalculator,
private val motorRepository: IMotorRepository) {

    suspend operator fun invoke(motorId: MotorId):MotorCalculationResult{
        val motor=motorRepository.getMotor(motorId)
        val voltage=motorRepository.getVoltage(motorId)
        return calculator(motor.power,voltage,motor.efficiency,motor.powerfactor,motor.ratedSpeed,motor.demandFactor,
        motor.startMode,motor.protectionType,motor.hasOverLoadRelay,motor.isBiDirect,motor.system)
    }
}