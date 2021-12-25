package com.vm.eea.application.ssd

import com.vm.eea.application.Power
import com.vm.eea.application.ssd.Ssd

interface ISsdFinder {
    suspend  fun withGreaterOrEqual(power: Power): Ssd?
}