package com.vm.eea.ui

import androidx.navigation.NamedNavArgument
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.vm.eea.application.Environment
import com.vm.eea.application.PowerSystem
import com.vm.eea.application.project.ProjectId
import com.vm.eea.application.*
import com.vm.eea.application.motor.MotorId
import com.vm.eea.application.project.RelationType


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

        operator fun invoke(projectId: ProjectId, system: PowerSystem)=object :INavigationCommand{
            override val route="project/update/voltage/${projectId.id}/$system"

        }

    }

    object ProjectCenter:IDestination{
        override val arguments = listOf(navArgument("projectId"){type= NavType.LongType})
        override val route="project/center/{projectId}"
        operator fun invoke(projectId: ProjectId)
                =NavigationCommand("project/center/${projectId.id}")
    }


    object ProjectAltitudeUpdate:IDestination{
        override val arguments: List<NamedNavArgument> =listOf(navArgument("projectId"){type= NavType.LongType})
        override val route="project/update/altitude/{projectId}"

        operator fun invoke(projectId:ProjectId)=object :INavigationCommand{
            override val route="project/update/altitude/${projectId.id}"

        }

    }
    object ProjectTemperatureUpdate:IDestination{
        override val arguments: List<NamedNavArgument> =listOf(
            navArgument("projectId"){type= NavType.LongType},
            navArgument("environment") { type = NavType.EnumType(Environment::class.java) })
        override val route="project/update/temperature/{environment}/{projectId}"

        operator fun invoke(projectId:ProjectId,environment: Environment)=object :INavigationCommand{
            override val route="project/update/temperature/$environment/${projectId.id}"
        }
    }
    object ProjectSoilResistivityUpdate:IDestination{
        override val arguments: List<NamedNavArgument> =listOf(navArgument("projectId"){type= NavType.LongType})
        override val route="project/update/soilResistivity/{projectId}"

        operator fun invoke(projectId:ProjectId)=
            NavigationCommand("project/update/soilResistivity/${projectId.id}")
    }
    object UpdateProjectPowerFactor:IDestination{
        override val arguments: List<NamedNavArgument> =listOf(
            navArgument("projectId"){type= NavType.LongType},
            navArgument("system") { type = NavType.EnumType(PowerSystem::class.java) })
        override val route="project/update/cosPhi/{system}/{projectId}"

        operator fun invoke(projectId:ProjectId,system: PowerSystem)=
            NavigationCommand("project/update/cosPhi/$system/${projectId.id}")
    }
    object UpdateProjectMaxVoltDrop:IDestination{
        override val arguments: List<NamedNavArgument> =listOf(
            navArgument("projectId"){type= NavType.LongType},
            navArgument("relationType") { type = NavType.EnumType(RelationType::class.java) })
        override val route="project/update/voltDrop/{relationType}/{projectId}"
        operator fun invoke(projectId:ProjectId,relationType: RelationType)=
            NavigationCommand("project/update/voltDrop/$relationType/${projectId.id}")
    }
    object ProjectCircuitCountUpdate:IDestination{
        override val arguments: List<NamedNavArgument> =listOf(navArgument("projectId"){type= NavType.LongType})
        override val route="project/update/circuitCount/{projectId}"
        operator fun invoke(projectId:ProjectId)=
            NavigationCommand("project/update/circuitCount/${projectId.id}")
    }
    object UpdateProjectWireSize:IDestination{
        override val arguments: List<NamedNavArgument> =listOf(
            navArgument("projectId"){type= NavType.LongType},
            navArgument("type") { type = NavType.EnumType(WireSizeType::class.java) })
        override val route="project/update/cableSize/{type}/{projectId}"
        operator fun invoke(projectId:ProjectId,type: WireSizeType)=
            NavigationCommand("project/update/cableSize/$type/${projectId.id}")
    }
    object UpdateProjectUnitOfPower:IDestination{
        override val arguments: List<NamedNavArgument> =listOf(navArgument("projectId"){type= NavType.LongType})
        override val route="project/update/powerUnit/{projectId}"
        operator fun invoke(projectId:ProjectId)=
            NavigationCommand("project/update/powerUnit/${projectId.id}")
    }
    object UpdateProjectUnitOfWireSize:IDestination{
        override val arguments: List<NamedNavArgument> =listOf(navArgument("projectId"){type= NavType.LongType})
        override val route="project/update/unitOfWireSize/{projectId}"
        operator fun invoke(projectId:ProjectId)=
            NavigationCommand("project/update/unitOfWireSize/${projectId.id}")
    }
    object UpdateProjectConductor:IDestination{
        override val arguments: List<NamedNavArgument> =listOf(navArgument("projectId"){type= NavType.LongType})
        override val route="project/update/conductor/{projectId}"
        operator fun invoke(projectId:ProjectId)=
            NavigationCommand("project/update/conductor/${projectId.id}")
    }
    object UpdateProjectInsulation:IDestination{
        override val arguments: List<NamedNavArgument> =listOf(navArgument("projectId"){type= NavType.LongType})
        override val route="project/update/insulation/{projectId}"
        operator fun invoke(projectId:ProjectId)=
            NavigationCommand("project/update/insulation/${projectId.id}")
    }
    object UpdateProjectCode:IDestination{
        override val arguments: List<NamedNavArgument> =listOf(navArgument("projectId"){type= NavType.LongType})
        override val route="project/update/code/{projectId}"
        operator fun invoke(projectId:ProjectId)=
            NavigationCommand("project/update/code/${projectId.id}")
    }
    object UpdateProjectMethodOfInstallation:IDestination{
        override val arguments: List<NamedNavArgument> =listOf(navArgument("projectId"){type= NavType.LongType})
        override val route="project/update/methodOfInstallation/{projectId}"
        operator fun invoke(projectId:ProjectId)=
            NavigationCommand("project/update/methodOfInstallation/${projectId.id}")
    }
    object UpdateProjectUnitOfTemperature:IDestination{
        override val arguments: List<NamedNavArgument> =listOf(navArgument("projectId"){type= NavType.LongType})
        override val route="project/update/unitOfTemperature/{projectId}"
        operator fun invoke(projectId:ProjectId)=
            NavigationCommand("project/update/unitOfTemperature/${projectId.id}")
    }
    object UpdateProjectUnitOfLength:IDestination{
        override val arguments: List<NamedNavArgument> =listOf(navArgument("projectId"){type= NavType.LongType})
        override val route="project/update/unitOfLength/{projectId}"
        operator fun invoke(projectId:ProjectId)=
            NavigationCommand("project/update/unitOfLength/${projectId.id}")
    }
    object ProjectDetails:IDestination {
        override val arguments: List<NamedNavArgument>
            get() = listOf(navArgument("projectId") { type = NavType.LongType })
            override var route: String="project/details/{projectId}"
       operator fun invoke(projectId:ProjectId) =
           NavigationCommand("project/details/${projectId.id}" )
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
        operator fun invoke(projectId: ProjectId)
                =NavigationCommand("addPanel/project/${projectId.id}")
    }

    object PanelDetails:IDestination{
        override val arguments = listOf(navArgument("id"){type= NavType.LongType})
        override val route="details/panel/{id}"
        operator fun invoke(panelId: PanelId)
                =NavigationCommand("details/panel/${panelId.id}")
    }

    object UpdatePanelCode:IDestination{
        override val arguments = listOf(navArgument("id"){type= NavType.LongType})
        override val route="update/panel/code/{id}"
        operator fun invoke(panelId: PanelId)
                =NavigationCommand("update/panel/code/${panelId.id}")
    }

    object UpdatePanelFeeder:IDestination{
        override val arguments = listOf(navArgument("id"){type= NavType.LongType})
        override val route="update/panel/feeder/{id}"
        operator fun invoke(panelId: PanelId)
                =NavigationCommand("update/panel/feeder/${panelId.id}")
    }

    object UpdatePanelFeedLength:IDestination{
        override val arguments = listOf(navArgument("relationId"){type= NavType.LongType})
        override val route="update/panel/feedLength/{relationId}"
        operator fun invoke(relationId: RelationId)
                =NavigationCommand("update/panel/feedLength/${relationId.id}")
    }

    object UpdatePanelFeedMethodOfInstallation:IDestination{
        override val arguments = listOf(navArgument("relationId"){type= NavType.LongType})
        override val route="update/panel/methodOfInstallation/{relationId}"
        operator fun invoke(relationId: RelationId)
                =NavigationCommand("update/panel/methodOfInstallation/${relationId.id}")
    }

    object UpdatePanelFeedVoltDrop:IDestination{
        override val arguments = listOf(navArgument("relationId"){type= NavType.LongType})
        override val route="update/panel/voltDrop/{relationId}"
        operator fun invoke(relationId: RelationId)
                =NavigationCommand("update/panel/voltDrop/${relationId.id}")
    }

    object UpdatePanelFeedTemperature:IDestination{
        override val arguments: List<NamedNavArgument> =listOf(
            navArgument("relationId"){type= NavType.LongType},
            navArgument("environment") { type = NavType.EnumType(Environment::class.java) })
        override val route="update/panel/temperature/{environment}/{relationId}"

        operator fun invoke(relationId: RelationId,environment: Environment)=object :INavigationCommand{
            override val route="update/panel/temperature/$environment/${relationId.id}"
        }
    }

    object UpdatePanelFeedSoilResistivity:IDestination{
        override val arguments = listOf(navArgument("relationId"){type= NavType.LongType})
        override val route="update/panel/soilResistivity/{relationId}"
        operator fun invoke(relationId: RelationId)
                =NavigationCommand("update/panel/soilResistivity/${relationId.id}")
    }

    object UpdatePanelFeedConductor:IDestination{
        override val arguments = listOf(navArgument("relationId"){type= NavType.LongType})
        override val route="update/panel/conductor/{relationId}"
        operator fun invoke(relationId: RelationId)
                =NavigationCommand("update/panel/conductor/${relationId.id}")
    }

    object UpdatePanelFeedInsulation:IDestination{
        override val arguments = listOf(navArgument("relationId"){type= NavType.LongType})
        override val route="update/panel/insulation/{relationId}"
        operator fun invoke(relationId: RelationId)
                =NavigationCommand("update/panel/insulation/${relationId.id}")
    }

    object UpdatePanelFeedCircuitCount:IDestination{
        override val arguments = listOf(navArgument("relationId"){type= NavType.LongType})
        override val route="update/panel/circuitCount/{relationId}"
        operator fun invoke(relationId: RelationId)
                =NavigationCommand("update/panel/circuitCount/${relationId.id}")
    }



}

object MotorDestinations{
    object AddMotor:IDestination{
        override val arguments = listOf(navArgument("projectId"){type= NavType.LongType})
        override val route="add/motor/project/{projectId}"
        operator fun invoke(projectId: ProjectId)
                =NavigationCommand("add/motor/project/${projectId.id}")
    }

    object MotorDetails:IDestination{
        override val arguments = listOf(navArgument("motorId"){type= NavType.LongType})
        override val route="motor/details/{motorId}"
        operator fun invoke(motorId: MotorId)
                =NavigationCommand("motor/details/${motorId.id}")
    }

    object UpdateMotorCode:IDestination{
        override val arguments = listOf(navArgument("motorId"){type= NavType.LongType})
        override val route="motor/update/code/{motorId}"
        operator fun invoke(motorId: MotorId)
                =NavigationCommand("motor/update/code/${motorId.id}")
    }

    object UpdateMotorFeeder:IDestination{
        override val arguments = listOf(navArgument("motorId"){type= NavType.LongType})
        override val route="motor/update/feeder/{motorId}"
        operator fun invoke(motorId: MotorId)
                =NavigationCommand("motor/update/feeder/${motorId.id}")
    }

    object UpdateMotorPower:IDestination{
        override val arguments = listOf(navArgument("motorId"){type= NavType.LongType})
        override val route="motor/update/power/{motorId}"
        operator fun invoke(motorId: MotorId)
                =NavigationCommand("motor/update/power/${motorId.id}")
    }
    object UpdateMotorStartMode:IDestination{
        override val arguments = listOf(navArgument("motorId"){type= NavType.LongType})
        override val route="motor/update/startMode/{motorId}"
        operator fun invoke(motorId: MotorId)
                =NavigationCommand("motor/update/startMode/${motorId.id}")
    }
    object UpdateMotorPowerfactor:IDestination{
        override val arguments = listOf(navArgument("motorId"){type= NavType.LongType})
        override val route="motor/update/demandFactor/{motorId}"
        operator fun invoke(motorId: MotorId)
                =NavigationCommand("motor/update/demandFactor/${motorId.id}")
    }
    object UpdateMotorDemandFactor:IDestination{
        override val arguments = listOf(navArgument("motorId"){type= NavType.LongType})
        override val route="motor/update/demandFactor/{motorId}"
        operator fun invoke(motorId: MotorId)
                =NavigationCommand("motor/update/demandFactor/${motorId.id}")
    }

    object UpdateMotorEfficiency:IDestination{
        override val arguments = listOf(navArgument("motorId"){type= NavType.LongType})
        override val route="motor/update/efficiency/{motorId}"
        operator fun invoke(motorId: MotorId)
                =NavigationCommand("motor/update/efficiency/${motorId.id}")
    }

    object UpdateMotorFeedLineLength:IDestination{
        override val arguments = listOf(navArgument("relationId"){type= NavType.LongType})
        override val route="motor/update/lineLength/{relationId}"
        operator fun invoke(relationId: RelationId)
                =NavigationCommand("motor/update/lineLength/${relationId.id}")
    }
    object UpdateMotorRelationMethodOdInstallation:IDestination{
        override val arguments = listOf(navArgument("relationId"){type= NavType.LongType})
        override val route="motor/update/methodOfInstallation/{relationId}"
        operator fun invoke(relationId: RelationId)
                =NavigationCommand("motor/update/methodOfInstallation/${relationId.id}")
    }
    object UpdateMotorRelationMaxVoltDrop:IDestination{
        override val arguments = listOf(navArgument("relationId"){type= NavType.LongType})
        override val route="motor/update/maxVoltDrop/{relationId}"
        operator fun invoke(relationId: RelationId)
                =NavigationCommand("motor/update/maxVoltDrop/${relationId.id}")
    }
    object UpdateMotorRelationTemperature:IDestination{
           override val arguments: List<NamedNavArgument> =listOf(
            navArgument("relationId"){type= NavType.LongType},
            navArgument("environment") { type = NavType.EnumType(Environment::class.java) })
        override val route="motor/update/temperature/{environment}/{relationId}"

        operator fun invoke(relationId: RelationId,environment: Environment)=object :INavigationCommand{
            override val route="motor/update/temperature/$environment/${relationId.id}"
        }

    }


    object UpdateMotorRelationSoilResistivity:IDestination{
        override val arguments = listOf(navArgument("relationId"){type= NavType.LongType})
        override val route="motor/update/soilResistivity/{relationId}"
        operator fun invoke(relationId: RelationId)
                =NavigationCommand("motor/update/soilResistivity/${relationId.id}")
    }

    object UpdateMotorRelationInsulation:IDestination{
        override val arguments = listOf(navArgument("relationId"){type= NavType.LongType})
        override val route="motor/update/insulation/{relationId}"
        operator fun invoke(relationId: RelationId)
                =NavigationCommand("motor/update/insulation/${relationId.id}")
    }
    object UpdateMotorRelationConductor:IDestination{
        override val arguments = listOf(navArgument("relationId"){type= NavType.LongType})
        override val route="motor/update/conductor/{relationId}"
        operator fun invoke(relationId: RelationId)
                =NavigationCommand("motor/update/conductor/${relationId.id}")
    }
    object UpdateMotorRelationCircuitCount:IDestination{
        override val arguments = listOf(navArgument("relationId"){type= NavType.LongType})
        override val route="motor/update/circuitCount/{relationId}"
        operator fun invoke(relationId: RelationId)
                =NavigationCommand("motor/update/circuitCount/${relationId.id}")
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


