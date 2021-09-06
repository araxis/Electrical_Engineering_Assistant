package com.vm.eea.ui

import androidx.navigation.NavType
import androidx.navigation.compose.NamedNavArgument
import androidx.navigation.compose.navArgument
import com.vm.eea.domain.Environment
import com.vm.eea.domain.PowerSystem
import com.vm.eea.domain.RelationType
import com.vm.eea.domain.project.WireSizeType


class Destination{
    companion object{


        const val NewDefaultVoltage="defaults/voltage/{system}"
    }
}


object Destinations{

     object Default:IDestination {
        override val arguments: List<NamedNavArgument> = emptyList()
       override val route: String=""
       operator fun invoke() = NavigationCommand(route)
   }
    object Home:IDestination  {
        override val arguments: List<NamedNavArgument> = emptyList()
        override val route: String="home"
    }

    object ProjectList:IDestination {
        override val arguments: List<NamedNavArgument> = emptyList()
        override val route: String="projectList"
        operator fun invoke():INavigationCommand=NavigationCommand(NewProject.route)
    }

    object NewProject:IDestination{
        override val arguments: List<NamedNavArgument> = emptyList()
        override val route: String="newSimpleProject"
        operator fun invoke():INavigationCommand=NavigationCommand(route)
    }



    object UpdateProjectVoltage:IDestination{
        override val arguments: List<NamedNavArgument> =listOf(
                navArgument("projectId"){type= NavType.LongType},
                navArgument("system") { type = NavType.EnumType(PowerSystem::class.java) })
        override val route="project/update/voltage/{projectId}/{system}"

        operator fun invoke(projectId:Long,system:PowerSystem)=object :INavigationCommand{
            override val route="project/update/voltage/$projectId/$system"

        }

    }

    object ProjectCenter:IDestination{
        override val arguments = listOf(navArgument("projectId"){type= NavType.LongType})
        override val route="project/center/{projectId}"
        operator fun invoke(projectId: Long)
                =NavigationCommand("project/center/$projectId")
    }


    object ProjectAltitudeUpdate:IDestination{
        override val arguments: List<NamedNavArgument> =listOf(navArgument("projectId"){type= NavType.LongType})
        override val route="project/update/altitude/{projectId}"

        operator fun invoke(projectId:Long)=object :INavigationCommand{
            override val route="project/update/altitude/$projectId"

        }

    }
    object ProjectTemperatureUpdate:IDestination{
        override val arguments: List<NamedNavArgument> =listOf(
            navArgument("projectId"){type= NavType.LongType},
            navArgument("environment") { type = NavType.EnumType(Environment::class.java) })
        override val route="project/update/temperature/{environment}/{projectId}"

        operator fun invoke(projectId:Long,environment: Environment)=object :INavigationCommand{
            override val route="project/update/temperature/$environment/$projectId"
        }
    }
    object ProjectSoilResistivityUpdate:IDestination{
        override val arguments: List<NamedNavArgument> =listOf(navArgument("projectId"){type= NavType.LongType})
        override val route="project/update/soilResistivity/{projectId}"

        operator fun invoke(projectId:Long)=NavigationCommand("project/update/soilResistivity/$projectId")
    }
    object UpdateProjectPowerFactor:IDestination{
        override val arguments: List<NamedNavArgument> =listOf(
            navArgument("projectId"){type= NavType.LongType},
            navArgument("system") { type = NavType.EnumType(PowerSystem::class.java) })
        override val route="project/update/powerFactor/{system}/{projectId}"

        operator fun invoke(projectId:Long,system:PowerSystem)=NavigationCommand("project/update/powerFactor/$system/$projectId")
    }
    object UpdateProjectMaxVoltDrop:IDestination{
        override val arguments: List<NamedNavArgument> =listOf(
            navArgument("projectId"){type= NavType.LongType},
            navArgument("relationType") { type = NavType.EnumType(RelationType::class.java) })
        override val route="project/update/voltDrop/{relationType}/{projectId}"
        operator fun invoke(projectId:Long,relationType: RelationType)=NavigationCommand("project/update/voltDrop/$relationType/$projectId")
    }
    object ProjectCircuitCountUpdate:IDestination{
        override val arguments: List<NamedNavArgument> =listOf(navArgument("projectId"){type= NavType.LongType})
        override val route="project/update/circuitCount/{projectId}"
        operator fun invoke(projectId:Long)=NavigationCommand("project/update/circuitCount/$projectId")
    }
    object UpdateProjectWireSize:IDestination{
        override val arguments: List<NamedNavArgument> =listOf(
            navArgument("projectId"){type= NavType.LongType},
            navArgument("type") { type = NavType.EnumType(WireSizeType::class.java) })
        override val route="project/update/cableSize/{type}/{projectId}"
        operator fun invoke(projectId:Long,type: WireSizeType)=NavigationCommand("project/update/cableSize/$type/$projectId")
    }
    object UpdateProjectUnitOfPower:IDestination{
        override val arguments: List<NamedNavArgument> =listOf(navArgument("projectId"){type= NavType.LongType})
        override val route="project/update/powerUnit/{projectId}"
        operator fun invoke(projectId:Long)=NavigationCommand("project/update/powerUnit/$projectId")
    }
    object UpdateProjectUnitOfWireSize:IDestination{
        override val arguments: List<NamedNavArgument> =listOf(navArgument("projectId"){type= NavType.LongType})
        override val route="project/update/unitOfWireSize/{projectId}"
        operator fun invoke(projectId:Long)=NavigationCommand("project/update/unitOfWireSize/$projectId")
    }
    object UpdateProjectConductor:IDestination{
        override val arguments: List<NamedNavArgument> =listOf(navArgument("projectId"){type= NavType.LongType})
        override val route="project/update/conductor/{projectId}"
        operator fun invoke(projectId:Long)=NavigationCommand("project/update/conductor/$projectId")
    }
    object UpdateProjectInsulation:IDestination{
        override val arguments: List<NamedNavArgument> =listOf(navArgument("projectId"){type= NavType.LongType})
        override val route="project/update/insulation/{projectId}"
        operator fun invoke(projectId:Long)=NavigationCommand("project/update/insulation/$projectId")
    }
    object UpdateProjectCode:IDestination{
        override val arguments: List<NamedNavArgument> =listOf(navArgument("projectId"){type= NavType.LongType})
        override val route="project/update/code/{projectId}"
        operator fun invoke(projectId:Long)=NavigationCommand("project/update/code/$projectId")
    }
    object UpdateProjectMethodOfInstallation:IDestination{
        override val arguments: List<NamedNavArgument> =listOf(navArgument("projectId"){type= NavType.LongType})
        override val route="project/update/methodOfInstallation/{projectId}"
        operator fun invoke(projectId:Long)=NavigationCommand("project/update/methodOfInstallation/$projectId")
    }
    object UpdateProjectUnitOfTemperature:IDestination{
        override val arguments: List<NamedNavArgument> =listOf(navArgument("projectId"){type= NavType.LongType})
        override val route="project/update/unitOfTemperature/{projectId}"
        operator fun invoke(projectId:Long)=NavigationCommand("project/update/unitOfTemperature/$projectId")
    }
    object UpdateProjectUnitOfLength:IDestination{
        override val arguments: List<NamedNavArgument> =listOf(navArgument("projectId"){type= NavType.LongType})
        override val route="project/update/unitOfLength/{projectId}"
        operator fun invoke(projectId:Long)=NavigationCommand("project/update/unitOfLength/$projectId")
    }
    object ProjectDetails:IDestination {
        override val arguments: List<NamedNavArgument>
            get() = listOf(navArgument("projectId") { type = NavType.LongType })
            override var route: String="project/details/{projectId}"
       operator fun invoke(projectId:Long) = NavigationCommand("project/details/${projectId}" )
    }

    object Back:IDestination{
        override val arguments: List<NamedNavArgument> = emptyList()
        override val route: String="back"

    operator fun invoke() =NavigationCommand(route)



    }
}

object PanelDestinations{
    object AddPanel:IDestination{
        override val arguments = listOf(navArgument("projectId"){type= NavType.LongType})
        override val route="addPanel/project/{projectId}"
        operator fun invoke(projectId: Long)
                =NavigationCommand("addPanel/project/$projectId")
    }

    object PanelDetails:IDestination{
        override val arguments = listOf(navArgument("panelId"){type= NavType.LongType})
        override val route="details/panel/{panelId}"
        operator fun invoke(panelId: Long)
                =NavigationCommand("details/panel/$panelId")
    }

    object UpdatePanelCode:IDestination{
        override val arguments = listOf(navArgument("panelId"){type= NavType.LongType})
        override val route="update/panel/code/{panelId}"
        operator fun invoke(panelId: Long)
                =NavigationCommand("update/panel/code/$panelId")
    }

    object UpdatePanelFeeder:IDestination{
        override val arguments = listOf(navArgument("panelId"){type= NavType.LongType})
        override val route="update/panel/feeder/{panelId}"
        operator fun invoke(panelId: Long)
                =NavigationCommand("update/panel/feeder/$panelId")
    }

    object UpdatePanelFeedLength:IDestination{
        override val arguments = listOf(navArgument("relationId"){type= NavType.LongType})
        override val route="update/panel/feedLength/{relationId}"
        operator fun invoke(relationId: Long)
                =NavigationCommand("update/panel/feedLength/$relationId")
    }

    object UpdatePanelFeedMethodOfInstallation:IDestination{
        override val arguments = listOf(navArgument("relationId"){type= NavType.LongType})
        override val route="update/panel/methodOfInstallation/{relationId}"
        operator fun invoke(relationId: Long)
                =NavigationCommand("update/panel/methodOfInstallation/$relationId")
    }

    object UpdatePanelFeedVoltDrop:IDestination{
        override val arguments = listOf(navArgument("relationId"){type= NavType.LongType})
        override val route="update/panel/voltDrop/{relationId}"
        operator fun invoke(relationId: Long)
                =NavigationCommand("update/panel/voltDrop/$relationId")
    }

    object UpdatePanelFeedTemperature:IDestination{
        override val arguments: List<NamedNavArgument> =listOf(
            navArgument("relationId"){type= NavType.LongType},
            navArgument("environment") { type = NavType.EnumType(Environment::class.java) })
        override val route="update/panel/temperature/{environment}/{relationId}"

        operator fun invoke(relationId: Long,environment: Environment)=object :INavigationCommand{
            override val route="update/panel/temperature/$environment/$relationId"
        }
    }

    object UpdatePanelFeedSoilResistivity:IDestination{
        override val arguments = listOf(navArgument("relationId"){type= NavType.LongType})
        override val route="update/panel/soilResistivity/{relationId}"
        operator fun invoke(relationId: Long)
                =NavigationCommand("update/panel/soilResistivity/$relationId")
    }

    object UpdatePanelFeedConductor:IDestination{
        override val arguments = listOf(navArgument("relationId"){type= NavType.LongType})
        override val route="update/panel/conductor/{relationId}"
        operator fun invoke(relationId: Long)
                =NavigationCommand("update/panel/conductor/$relationId")
    }

    object UpdatePanelFeedInsulation:IDestination{
        override val arguments = listOf(navArgument("relationId"){type= NavType.LongType})
        override val route="update/panel/insulation/{relationId}"
        operator fun invoke(relationId: Long)
                =NavigationCommand("update/panel/insulation/$relationId")
    }

    object UpdatePanelFeedCircuitCount:IDestination{
        override val arguments = listOf(navArgument("relationId"){type= NavType.LongType})
        override val route="update/panel/circuitCount/{relationId}"
        operator fun invoke(relationId: Long)
                =NavigationCommand("update/panel/circuitCount/$relationId")
    }

}

object MotorDestinations{
    object AddMotor:IDestination{
        override val arguments = listOf(navArgument("projectId"){type= NavType.LongType})
        override val route="add/motor/project/{projectId}"
        operator fun invoke(projectId: Long)
                =NavigationCommand("add/motor/project/$projectId")
    }

    object MotorDetails:IDestination{
        override val arguments = listOf(navArgument("motorId"){type= NavType.LongType})
        override val route="motor/details/{motorId}"
        operator fun invoke(motorId: Long)
                =NavigationCommand("motor/details/$motorId")
    }

    object UpdateMotorCode:IDestination{
        override val arguments = listOf(navArgument("motorId"){type= NavType.LongType})
        override val route="motor/update/code/{motorId}"
        operator fun invoke(motorId: Long)
                =NavigationCommand("motor/update/code/$motorId")
    }

    object UpdateMotorFeeder:IDestination{
        override val arguments = listOf(navArgument("motorId"){type= NavType.LongType})
        override val route="motor/update/feeder/{motorId}"
        operator fun invoke(motorId: Long)
                =NavigationCommand("motor/update/feeder/$motorId")
    }

    object UpdateMotorPower:IDestination{
        override val arguments = listOf(navArgument("motorId"){type= NavType.LongType})
        override val route="motor/update/power/{motorId}"
        operator fun invoke(motorId: Long)
                =NavigationCommand("motor/update/power/$motorId")
    }
    object UpdateMotorPowerfactor:IDestination{
        override val arguments = listOf(navArgument("motorId"){type= NavType.LongType})
        override val route="motor/update/powerfactor/{motorId}"
        operator fun invoke(motorId: Long)
                =NavigationCommand("motor/update/powerfactor/$motorId")
    }
    object UpdateMotorDemandFactor:IDestination{
        override val arguments = listOf(navArgument("motorId"){type= NavType.LongType})
        override val route="motor/update/demandFactor/{motorId}"
        operator fun invoke(motorId: Long)
                =NavigationCommand("motor/update/demandFactor/$motorId")
    }
}

interface IDestination{
    val arguments: List<NamedNavArgument>
    val route: String
}

interface INavigationCommand {

    val route: String

}

class NavigationCommand(override val route: String):INavigationCommand


