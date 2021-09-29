package com.vm.eea.ui.project.updateProjectSoilThermalResistivity

import com.vm.eea.application.project.IGetProjectSoilThermalResistivity
import com.vm.eea.application.project.update.UpdateProjectSoilThermalResistivity
import com.vm.eea.data.project.GetProjectSoilThermalResistivity
import org.koin.dsl.module

val updateProjectSoilThermalResistivityModule= module {
    factory<IGetProjectSoilThermalResistivity> { GetProjectSoilThermalResistivity(get()) }
    factory { UpdateProjectSoilThermalResistivity(get()) }
}