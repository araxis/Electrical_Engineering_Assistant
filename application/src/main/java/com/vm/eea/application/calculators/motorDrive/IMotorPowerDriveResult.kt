package com.vm.eea.application.calculators.motorDrive

import com.vm.eea.application.ContactorResult
import com.vm.eea.application.ItemResult
import com.vm.eea.application.SsdResult
import com.vm.eea.application.VsdResult
import com.vm.eea.application.contactor.Contactor

sealed interface IMotorPowerDriveResult

data class MotorDolDriveResult(val mainContactor: ContactorResult): IMotorPowerDriveResult

data class MotorBiDirectDolDriveResult(val mainContactor: ItemResult<Contactor>,
                               val biDirectContactor: ItemResult<Contactor>): IMotorPowerDriveResult

data class BiDirectSdDriveResult(val mainContactor: ContactorResult,
                                 val deltaContactor: ContactorResult,
                                 val starContactor: ContactorResult,
                                 val biDirectContactor: ContactorResult): IMotorPowerDriveResult
data class SdDriveResult(val mainContactor: ContactorResult,
                                 val deltaContactor: ContactorResult,
                                 val starContactor: ContactorResult): IMotorPowerDriveResult

data class BiDirectMotorSsdDriveResult(val ssd: SsdResult,
                                       val leftContactor: ContactorResult,
                                       val rightContactor: ContactorResult): IMotorPowerDriveResult
data class SsdDriveResult(val ssd: SsdResult): IMotorPowerDriveResult

data class MotorVsdResult(val vsd: VsdResult): IMotorPowerDriveResult



