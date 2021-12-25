package com.vm.eea.application.motor

import com.vm.eea.application.PanelId
import com.vm.eea.application.Voltage

interface IMotorRepository {

    suspend fun add(motor:Motor):MotorId
    suspend fun remove(motorId: MotorId)
    suspend fun getMotor(motorId: MotorId):Motor
    suspend fun getMotorInfo(motorId: MotorId):MotorInfo
    suspend fun getPanelMotors(panelId: PanelId):List<MotorInfo>
    suspend fun update(motor: Motor)
    suspend fun getVoltage(motorId: MotorId): Voltage
}