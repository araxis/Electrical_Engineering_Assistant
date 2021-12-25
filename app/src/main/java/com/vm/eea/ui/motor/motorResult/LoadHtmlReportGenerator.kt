package com.vm.eea.ui.motor.motorResult


import com.vm.eea.ui.GroupPropertyItem
import kotlinx.html.*
import kotlinx.html.stream.appendHTML

class LoadHtmlReportGenerator {

    fun generate(info:List<GroupPropertyItem>):String{
        val html = StringBuilder().appendHTML().html {
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
                               width: 50%;
                               padding:10px;
                               white-space: nowrap;
                            }
                            th {
                              width: 50%;
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
                info.forEach {
                    p{}
                    table {

                        caption { +it.key }
                        thead {
                            tr {
                                th { +"Property" }
                                th { +"value" }
                            }
                        }
                        tbody {
                           it.items.forEach {
                               tr {
                                   td { +it.Name }
                                   td { +it.value }
                               }
                           }
                        }
                    }
                }

            }
        }.toString()
        return html
    }
}