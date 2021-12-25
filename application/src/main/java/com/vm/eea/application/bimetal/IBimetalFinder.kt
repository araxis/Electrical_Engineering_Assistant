package com.vm.eea.application.bimetal

import com.vm.eea.application.bimetal.Bimetal
import com.vm.eea.application.Current

interface IBimetalFinder {
    suspend operator fun invoke(current: Current): Bimetal?
}