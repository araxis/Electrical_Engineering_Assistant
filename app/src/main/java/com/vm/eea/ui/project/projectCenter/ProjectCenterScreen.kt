package com.vm.eea.ui.project.projectCenter

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
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
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.accompanist.pager.ExperimentalPagerApi
import com.vm.eea.application.project.ProjectPanelsResult
import com.vm.eea.application.project.IGetProjectMotors
import com.vm.eea.application.project.IGetProjectPanels
import com.vm.eea.application.project.ProjectMotorsResult
import com.vm.eea.ui.components.Page1

@ExperimentalFoundationApi
@Composable
fun ProjectCenterScreen(viewModel: ProjectCenterViewModel) {
    val state by viewModel.container.stateFlow.collectAsState()
    val fab=@Composable{when(state.currentTab){
        UiState.ProjectCenterTab.Panels ->ExtendedFloatingActionButton(
            onClick = { viewModel.onAddNewPanel() },
            text = { Text(text = "Panel")},
            icon = {Icon( Icons.Filled.Add,contentDescription = "Localized description")},

        )
        UiState.ProjectCenterTab.Motors -> ExtendedFloatingActionButton(
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

@OptIn(ExperimentalAnimationApi::class)
@ExperimentalFoundationApi
@Composable
fun ProjectCenterForm(currentTab: UiState.ProjectCenterTab,
                      panels: List<ProjectPanelsResult>, motors:List<ProjectMotorsResult>,
                      onPanelSelect: (ProjectPanelsResult) -> Unit,
                      onMotorSelect:(ProjectMotorsResult)->Unit,
                      onTabChange:(UiState.ProjectCenterTab)->Unit){
    TabRow(selectedTabIndex = currentTab.ordinal,backgroundColor = Color.Transparent) {
        Tab(selected = currentTab == UiState.ProjectCenterTab.Panels, onClick = { onTabChange(UiState.ProjectCenterTab.Panels)}) {
            Text(text = "Panels",modifier = Modifier.padding(16.dp))
        }
        Tab(selected = currentTab == UiState.ProjectCenterTab.Motors, onClick = { onTabChange(UiState.ProjectCenterTab.Motors )}) {
            Text(text = "Motors",modifier = Modifier.padding(16.dp))
        }
    }

    when(currentTab){
        UiState.ProjectCenterTab.Panels ->{

                PanelList(panels){onPanelSelect(it)}



        }
        UiState.ProjectCenterTab.Motors -> MotorList(motors){onMotorSelect(it)}
    }

}

@OptIn(ExperimentalAnimationApi::class)
@ExperimentalFoundationApi
@Composable
fun PanelList(panels:List<ProjectPanelsResult>, onPanelSelect:(ProjectPanelsResult)->Unit){

        LazyColumn(modifier = Modifier.fillMaxSize()) {
            items(panels) {
                Surface(elevation = 0.dp,modifier = Modifier
                    .clickable { onPanelSelect(it) }) {
                PanelItem(it)
                }
            }
            item{
                Spacer(modifier = Modifier.height(64.dp))
            }
        }

}

@ExperimentalAnimationApi
@Composable
fun MotorList(motors:List<ProjectMotorsResult>, onPanelSelect:(ProjectMotorsResult)->Unit){
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        items(motors) {
            Surface(elevation = 0.dp,modifier = Modifier
                .clickable { onPanelSelect(it) }) {
                MotorItem(it)
            }
        }
        item{
            Spacer(modifier = Modifier.height(64.dp))
        }
    }
}

@OptIn(ExperimentalPagerApi::class)
@ExperimentalAnimationApi
@ExperimentalFoundationApi
@Composable
fun PanelItem(panel: ProjectPanelsResult){

    Column(
        Modifier
            .fillMaxWidth()
            .padding(8.dp)) {
        Text(
            text = buildAnnotatedString {
                withStyle(style =  SpanStyle(fontSize = 22.sp)){
                    append(panel.code)
                }
                withStyle(style = SpanStyle(fontSize = 16.sp,
                    fontStyle = FontStyle.Italic,
                    color = Color.DarkGray)){
                    append(" (${panel.totalCurrent.toFormatString(empty = "0")})")
                }

            }
        )

        Text(
            text = panel.description,
            style = MaterialTheme.typography.subtitle1.copy(fontStyle = FontStyle.Italic),
        )

    }
}

@OptIn(ExperimentalPagerApi::class)
@ExperimentalAnimationApi
@Composable
fun MotorItem(item: ProjectMotorsResult){

    Column(Modifier
        .fillMaxWidth()
        .padding(8.dp)) {
        Text(
            text = buildAnnotatedString {
                withStyle(style =  SpanStyle(fontSize = 22.sp)){
                    append(item.code)
                }
                withStyle(style = SpanStyle(fontSize = 16.sp,
                    fontStyle = FontStyle.Italic,
                    color = Color.DarkGray)){
                    append(" (${item.current.toFormatString(empty = "0")})")
                }

            }
        )

        Text( text = item.description,
            style = MaterialTheme.typography.subtitle1.copy(fontStyle = FontStyle.Italic),
        )
    }



}