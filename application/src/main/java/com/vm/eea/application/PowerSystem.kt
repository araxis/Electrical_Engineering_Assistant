package com.vm.eea.application

import kotlin.math.sqrt

enum class PowerSystem {

   SinglePhase,TwoPhase,ThreePhase;

   override fun toString()=name
   operator fun invoke()=toString()



   val conf:Double  get()= when(this){
      SinglePhase -> 1.0
      TwoPhase -> 1.0
      ThreePhase -> sqrt(3.0)
   }
}