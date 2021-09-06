package com.vm.eea.ui.models

import com.vm.eea.domain.panel.IPanelRepository
import kotlinx.coroutines.flow.map

class GetSimplePanels(private val panelRepository: IPanelRepository) {

    operator fun invoke(projectId: Long)=panelRepository
        .getPanels(projectId).map { items->items.map { SimplePanel(it.id,it.code,it.description) } }
}