package com.vm.eea.application.panel


interface IPanelCodeExistChecker {
    suspend operator fun invoke(code:PanelCode):Boolean
}