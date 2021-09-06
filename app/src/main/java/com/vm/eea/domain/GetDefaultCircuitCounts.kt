package com.vm.eea.domain

import kotlinx.coroutines.flow.flow

class GetDefaultCircuitCounts {

    operator fun invoke()= flow {
       emit( listOf(
           CircuitCount(1),
           CircuitCount(2),
           CircuitCount(3),
           CircuitCount(4),
           CircuitCount(5),
           CircuitCount(6),
           CircuitCount(7),
           CircuitCount(8),
           CircuitCount(9),
           CircuitCount(12),
           CircuitCount(16),
           CircuitCount(20),

       ))
    }

}