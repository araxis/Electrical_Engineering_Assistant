package com.vm.eea.application.calculators.motorCapacitorDrive

import com.vm.eea.application.CapacitorContactorResult
import com.vm.eea.application.CapacitorResult
import com.vm.eea.application.FuseResult

sealed class MotorCapacitorDriveResult{
    data class Use(val capacitor: CapacitorResult,
                   val capacitorContactor: CapacitorContactorResult,
                   val fuse: FuseResult
    ):MotorCapacitorDriveResult()
    object UseLess:MotorCapacitorDriveResult()
}
