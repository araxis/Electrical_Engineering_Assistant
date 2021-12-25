package com.vm.eea.application.vsd

import com.vm.eea.application.Power
import com.vm.eea.application.vsd.Vsd

interface IVsdFinder {
    suspend  fun withGreaterOrEqual(power: Power): Vsd?
}