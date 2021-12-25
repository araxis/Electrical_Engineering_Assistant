package com.vm.eea.ui

import androidx.navigation.NamedNavArgument
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.vm.eea.application.Environment
import com.vm.eea.application.PanelId
import com.vm.eea.application.RelationId
import com.vm.eea.application.motor.MotorId
import com.vm.eea.application.project.ProjectId


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
        operator fun invoke():INavigationCommand=NavigationCommand(route)
    }







    object Back:IDestination{
        override val arguments: List<NamedNavArgument> = emptyList()
        override val route: String="back"

    operator fun invoke() =NavigationCommand(route)



    }
}



object MotorDestinations{

    object UpdateMotor:IDestination{
        override val arguments = listOf(navArgument("motorId"){type= NavType.LongType})
        override val route="motor/update/{motorId}"
        operator fun invoke(motorId: MotorId)
                =NavigationCommand("motor/update/${motorId.id}")
    }


    object UpdateMotorFeeder:IDestination{
        override val arguments = listOf(navArgument("motorId"){type= NavType.LongType})
        override val route="motor/update/feeder/{motorId}"
        operator fun invoke(motorId: MotorId)
                =NavigationCommand("motor/update/feeder/${motorId.id}")
    }



}

object MotorCalculatorDestinations{

    object Current:IDestination{
       override val arguments: List<NamedNavArgument> = listOf()
       override val route="calculators/motor/current"
       operator fun invoke()=NavigationCommand(route)

   }
    object Power:IDestination{
        override val arguments: List<NamedNavArgument> = listOf()
        override val route="calculators.motors/power"
        operator fun invoke()=NavigationCommand(route)

    }
    object Voltage:IDestination{
        override val arguments: List<NamedNavArgument> = listOf()
        override val route="calculators/motor/voltage"
        operator fun invoke()=NavigationCommand(route)

    }
    object CosPhi:IDestination{
        override val arguments: List<NamedNavArgument> = listOf()
        override val route="calculators/motor/cosPhi"
        operator fun invoke()=NavigationCommand(route)

    }
    object Efficiency:IDestination{
        override val arguments: List<NamedNavArgument> = listOf()
        override val route="calculators/motor/efficiency"
        operator fun invoke()=NavigationCommand(route)

    }
    object Speed:IDestination{
        override val arguments: List<NamedNavArgument> = listOf()
        override val route="calculators/motor/speed"
        operator fun invoke()=NavigationCommand(route)

    }
    object Slip:IDestination{
        override val arguments: List<NamedNavArgument> = listOf()
        override val route="calculators/motor/slip"
        operator fun invoke()=NavigationCommand(route)

    }
    object Torque:IDestination{
        override val arguments: List<NamedNavArgument> = listOf()
        override val route="calculators/motor/torque"
        operator fun invoke()=NavigationCommand(route)

    }
    object StartMode:IDestination{
        override val arguments: List<NamedNavArgument> = listOf()
        override val route="calculators/motor/startMode"
        operator fun invoke()=NavigationCommand(route)

    }
    object ApparentPower:IDestination{
        override val arguments: List<NamedNavArgument> = listOf()
        override val route="calculators/apparentPower"
        operator fun invoke()=NavigationCommand(route)

    }
    object CircuitBreaker:IDestination{
        override val arguments: List<NamedNavArgument> = listOf()
        override val route="calculators/protection"
        operator fun invoke()=NavigationCommand(route)

    }

    object Guard:IDestination{
        override val arguments: List<NamedNavArgument> = listOf()
        override val route="calculators/guard"
        operator fun invoke()=NavigationCommand(route)

    }

    object FullMotorForm:IDestination{
        override val arguments: List<NamedNavArgument> = listOf()
        override val route="calculators/motor/full"
        operator fun invoke()=NavigationCommand(route)

    }

    object FullMotorResult:IDestination{
        override val arguments = listOf(navArgument("motorId"){type= NavType.LongType})
        override val route="calculators/motor/full/result/{motorId}"
        operator fun invoke(motorId: MotorId):INavigationCommand {
          return  NavigationCommand("calculators/motor/full/result/${motorId.id}")

        }

    }

    object FullPanelForm:IDestination{
        override val arguments: List<NamedNavArgument> = listOf()
        override val route="calculators/panel/full"
        operator fun invoke()=NavigationCommand(route)

    }

    object FullPanelResult:IDestination{
        override val arguments = listOf(navArgument("panelId"){type= NavType.LongType})
        override val route="calculators/panel/full/result/{panelId}"
        operator fun invoke(panelId: PanelId):INavigationCommand {
            return  NavigationCommand("calculators/panel/full/result/${panelId.id}")

        }

    }

    object AddPanelMotor:IDestination{
        override val arguments: List<NamedNavArgument> =listOf(
            navArgument("panelId"){type= NavType.LongType})
        override val route="calculators/panel/full/{panelId}"
        operator fun invoke(panelId: PanelId):INavigationCommand {
            return  NavigationCommand("calculators/panel/full/${panelId.id}")

        }

    }

}

object ReportDestinations{
    object ReportViewer:IDestination{
        override val arguments = listOf(navArgument("report"){type= NavType.StringType})
        override val route="reportViewer/{report}"
        operator fun invoke(report:String):INavigationCommand {
            return  NavigationCommand("reportViewer/${report}")
        }

    }
}
interface IDestination{
    val arguments: List<NamedNavArgument>
    val route: String
}


