package com.vm.eea.application.contactor

import com.vm.eea.application.Current

interface IContactorFinder {
    suspend  fun withGreaterOrEqual(current: Current): Contactor?
    suspend  fun withGLess(current: Current): Contactor?
}