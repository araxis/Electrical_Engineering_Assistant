package com.vm.eea.application.calculators

import com.vm.eea.application.*


interface IPowerConsumer {
   val power: Power
   val voltage: Voltage
   val system: PowerSystem
   val cosPhi: CosPhi
   val efficiency: Efficiency



}