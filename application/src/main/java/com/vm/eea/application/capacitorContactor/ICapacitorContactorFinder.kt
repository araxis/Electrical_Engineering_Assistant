package com.vm.eea.application.capacitorContactor

import com.vm.eea.application.ReactivePower

interface ICapacitorContactorFinder {
    suspend  fun withGreaterOrEqual(reactivePower: ReactivePower): CapacitorContactor?
}