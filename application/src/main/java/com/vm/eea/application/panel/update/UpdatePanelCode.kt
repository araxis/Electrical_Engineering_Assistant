package com.vm.eea.application.panel.update

import com.vm.eea.application.PanelId
import com.vm.eea.application.panel.IPanelCodeExistChecker
import com.vm.eea.application.panel.IPanelRepository
import com.vm.eea.application.panel.PanelCode

class UpdatePanelCode(private val panelRepository: IPanelRepository,
                      private val panelCodeExistChecker: IPanelCodeExistChecker ) {
    suspend operator fun invoke(panelId: PanelId, code:String, description:String){
        val panelCode=PanelCode(code)
        val isExist=panelCodeExistChecker(panelCode)
        if(isExist) throw Exception("Duplicated code")
        panelRepository.updateCode(panelId,panelCode,description)
    }
}