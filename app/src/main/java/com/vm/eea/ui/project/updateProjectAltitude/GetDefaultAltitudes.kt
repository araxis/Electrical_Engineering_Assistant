package com.vm.eea.ui.project.updateProjectAltitude

import com.vm.eea.domain.defaultAltitude.IDefaultAltitudeRepository

class GetDefaultAltitudes(private val repository: IDefaultAltitudeRepository) {

     operator fun invoke()=repository.getValuesFlow()
}