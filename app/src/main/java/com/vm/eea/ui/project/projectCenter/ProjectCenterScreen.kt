package com.vm.eea.ui.project.projectCenter

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.vm.eea.domain.panel.Panel
import com.vm.eea.ui.components.Page1
import com.vm.eea.ui.models.SimpleMotor

@Composable
fun ProjectCenterScreen(viewModel: ProjectCenterViewModel) {
    val state by viewModel.container.stateFlow.collectAsState()
    val fab=@Composable{when(state.currentTab){
        ProjectCenterTab.Panels ->ExtendedFloatingActionButton(
            onClick = { viewModel.onAddNewPanel() },
            text = { Text(text = "Panel")},
            icon = {Icon( Icons.Filled.Add,contentDescription = "Localized description")},

        )
        ProjectCenterTab.Motors -> ExtendedFloatingActionButton(
            onClick = { viewModel.onAddNewMotor() },
            text = { Text(text = "Motor")},
            icon = {Icon( Icons.Filled.Add,contentDescription = "Localized description")},

            )
    }}
    Page1(pageTitle = "Project",
        floatingActionButton =fab,
         actions = {IconButton(onClick = {viewModel.onSetting()}) {
                 Icon(Icons.Filled.Settings, null)}}) {

        ProjectCenterForm(state.currentTab,state.panels,state.motors,
            onTabChange = {viewModel.onTabSelect(it)},
            onPanelSelect = {viewModel.onPanelSelect(it)},
            onMotorSelect = {viewModel.onMotorSelect(it)})
    }
}

@Composable
fun ProjectCenterForm(currentTab:ProjectCenterTab,
                      panels: List<Panel>, motors:List<SimpleMotor>,
                      onPanelSelect: (Panel) -> Unit,
                      onMotorSelect:(SimpleMotor)->Unit,
                      onTabChange:(ProjectCenterTab)->Unit){
    TabRow(selectedTabIndex = currentTab.ordinal,backgroundColor = Color.Transparent) {
        Tab(selected = currentTab == ProjectCenterTab.Panels, onClick = { onTabChange(ProjectCenterTab.Panels)}) {
            Text(text = "Panels",modifier = Modifier.padding(bottom = 16.dp))
        }
        Tab(selected = currentTab == ProjectCenterTab.Motors, onClick = { onTabChange(ProjectCenterTab.Motors )}) {
            Text(text = "Motors")
        }
    }

    when(currentTab){
        ProjectCenterTab.Panels ->{

                PanelList(panels){onPanelSelect(it)}



        }
        ProjectCenterTab.Motors -> MotorList(motors){onMotorSelect(it)}
    }

}

@Composable
fun PanelList(panels:List<Panel>, onPanelSelect:(Panel)->Unit){

        LazyColumn(modifier = Modifier.fillMaxSize()) {
            items(panels) {
                Surface(elevation = 0.dp,modifier = Modifier.clickable { onPanelSelect(it) }) {
                PanelItem(it)
                }
            }
            item{
                Spacer(modifier = Modifier.height(64.dp))
            }
        }




}

@Composable
fun MotorList(motors:List<SimpleMotor>, onPanelSelect:(SimpleMotor)->Unit){
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        items(motors) {
            Surface(elevation = 0.dp,modifier = Modifier.clickable { onPanelSelect(it) }) {
                MotorItem(it)
            }
        }
        item{
            Spacer(modifier = Modifier.height(64.dp))
        }
    }
}

@Composable
fun PanelItem(panel: Panel, modifier: Modifier=Modifier){

        Column(Modifier.fillMaxWidth()) {
            Text(modifier=Modifier.padding(start = 16.dp,end =16.dp,top=8.dp),
                text = panel.code,
                style = MaterialTheme.typography.caption.copy(fontSize = 22.sp),
            )
            Text(modifier=Modifier.padding(start = 16.dp,end =16.dp,bottom = 8.dp),
                text = panel.description,
                style = MaterialTheme.typography.subtitle1.copy(fontStyle = FontStyle.Italic),
            )
        }


}

@Composable
fun MotorItem(item: SimpleMotor, modifier: Modifier=Modifier){
    Surface(modifier = modifier,elevation = 0.dp) {
        Column(Modifier.fillMaxWidth()) {
            Text(modifier=Modifier.padding(start = 16.dp,end =16.dp,top=8.dp),
                text = item.code,
                style = MaterialTheme.typography.caption.copy(fontSize = 22.sp),
            )
            Text(modifier=Modifier.padding(start = 16.dp,end =16.dp,bottom = 8.dp),
                text = item.description,
                style = MaterialTheme.typography.subtitle1.copy(fontStyle = FontStyle.Italic),
            )
        }
    }

}