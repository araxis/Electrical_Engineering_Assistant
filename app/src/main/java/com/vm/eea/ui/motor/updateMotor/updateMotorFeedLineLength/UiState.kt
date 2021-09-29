package com.vm.eea.ui.motor.updateMotor.updateMotorFeedLineLength


import com.vm.eea.application.Length
import com.vm.eea.ui.LengthField

data class UiState(val length:LengthField= LengthField.empty(Length.Unit.M), val canSubmit:Boolean=false)
