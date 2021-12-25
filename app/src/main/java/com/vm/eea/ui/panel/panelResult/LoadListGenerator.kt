package com.vm.eea.ui.panel.panelResult

import com.vm.eea.application.calculators.panelFullResult.PanelCalculationResult
import com.vm.eea.application.motor.MotorInfo
import com.vm.eea.utilities.display
import com.vm.eea.utilities.displayOrZero
import kotlinx.html.*
import kotlinx.html.stream.appendHTML

class LoadListGenerator {

      fun generate(panelResult: PanelCalculationResult,motors:List<MotorInfo>):String{

      val html=StringBuilder().appendHTML().html{
          head {
              style {
                  unsafe {
                      raw("""
                            table {
                              width: 100%;
                              border-collapse: collapse;
                            }
                            table, th, td {
                              border: 1px solid black;                              
                            }
                             td{
                               width: auto;
                               padding:10px;
                               white-space: nowrap;
                            }
                            th {
                              width: auto;
                              padding:10px;
                              white-space: nowrap;
                              background-color: #04AA6D;
                              color: white;
                            }
                            caption {
                            padding:10px;
                            }
                       
                        """)
                  }
              }
          }
          body {

                  table {
                      caption { +"Panel Specifications" }
                      thead {
                          tr {
                              th { +"Parameter" }
                              th { +"Value" }
                          }
                      }
                      tbody {
                          tr {
                              td { +"Total active power" }
                              td { +panelResult.activePower.displayOrZero() }
                          }
                          tr {
                              td { +"Total reactive power" }
                              td { +panelResult.reactivePower.displayOrZero() }
                          }
                          tr {
                              td { +"Total apparent power" }
                              td { +panelResult.apparentPower.displayOrZero() }
                          }
                          tr {
                              td { +"Total current" }
                              td { +panelResult.current.displayOrZero() }
                          }
                          tr {
                              td { +"Power factor" }
                              td { +panelResult.cosPhi.displayOrZero() }
                          }
                          tr {
                              td { +"Desired power factor" }
                              td { +panelResult.demandFactor.displayOrZero() }
                          }
                          tr {
                              td { +"Required VAr" }
                              td { +panelResult.requiredReactivePower.displayOrZero() }
                          }
                          tr {
                              td { +"Coincidence factor" }
                              td { +panelResult.coincidenceFactor.displayOrZero() }
                          }
                          tr {
                              td { +"Circuit breaker" }
                              td { +panelResult.protection.display() }
                          }
                      }
                  }
                  table {
                      caption { +"Load list" }
                      thead {
                          tr {
                              th { +"#" }
                              th { +"Power" }
                              th { +"Voltage" }
                              th { +"Apparent power" }
                              th { +"Reactive power" }
                              th { +"Power factor" }
                              th { +"Start mode" }
                              th { +"Current" }
                          }
                      }
                      tbody {

                          motors.forEachIndexed { i, it ->
                              tr {
                                  td { +((i+1).toString()) }
                                  td { +it.power.displayOrZero() }
                                  td { +it.voltage.displayOrZero() }
                                  td { +it.apparentPower.displayOrZero() }
                                  td { +it.reactivePower.displayOrZero() }
                                  td { +it.powerfactor.displayOrZero() }
                                  td { +it.startMode.name }
                                  td { +it.current.displayOrZero() }
                              }
                          }
                      }
                  }


          }
      }

        return html.toString()
    }
}