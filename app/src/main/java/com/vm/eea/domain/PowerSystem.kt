package com.vm.eea.domain

enum class PowerSystem {

   SinglePhase,TwoPhase,ThreePhase;

   override fun toString()=name
   operator fun invoke()=toString()
}