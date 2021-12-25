package com.vm.eea.application.calculators.panelFullResult

import com.vm.eea.application.PanelId
import com.vm.eea.application.calculators.CosPhiCalculator
import com.vm.eea.application.calculators.PanelCircuitBreakerCalculator
import com.vm.eea.application.calculators.RequiredVarCalculator
import com.vm.eea.application.panel.IPanelRepository

class PanelCalculator(
    private val panelRepository: IPanelRepository,
    private val cosPhiCalculator: CosPhiCalculator,
    private val requiredVarCalculator: RequiredVarCalculator,
    private val panelCircuitBreakerCalculator: PanelCircuitBreakerCalculator
) {

    suspend operator fun invoke(panelId: PanelId):PanelCalculationResult{
        val panelInfo=panelRepository.getInfo(panelId)
        val cosPhi=cosPhiCalculator(panelInfo.reactivePower,panelInfo.apparentPower)
        val requiredVar=requiredVarCalculator(panelInfo.activePower,cosPhi,panelInfo.demandFactor)
        val circuitBreaker=panelCircuitBreakerCalculator(panelInfo.current)
        return PanelCalculationResult(panelInfo.demandFactor,panelInfo.coincidenceFactor,
            current = panelInfo.current * (panelInfo.coincidenceFactor.value),
            activePower = panelInfo.activePower,
            requiredReactivePower=requiredVar,
            reactivePower = panelInfo.reactivePower,
            apparentPower = panelInfo.apparentPower,
            cosPhi = cosPhi,
            protection = circuitBreaker,
            lineLineVoltage = panelInfo.lineLineVoltage,
            lineNullVoltage = panelInfo.lineNullVoltage

        )
    }

}