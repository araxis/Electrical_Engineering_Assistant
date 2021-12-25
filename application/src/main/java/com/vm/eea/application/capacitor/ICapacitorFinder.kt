package com.vm.eea.application.capacitor

import com.vm.eea.application.ReactivePower

interface ICapacitorFinder {
    suspend  fun withGreaterOrEqual(reactivePower: ReactivePower): Capacitor?
}