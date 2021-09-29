package com.vm.eea.application

enum class PowerSystem {

   SinglePhase,TwoPhase,ThreePhase;

   override fun toString()=name
   operator fun invoke()=toString()
}