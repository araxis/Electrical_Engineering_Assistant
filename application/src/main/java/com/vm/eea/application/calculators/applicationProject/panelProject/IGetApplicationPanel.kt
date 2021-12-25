package com.vm.eea.application.calculators.applicationProject.panelProject

import kotlinx.coroutines.flow.Flow

interface IGetApplicationPanel {
      operator fun invoke(): Flow<ApplicationPanel>
}