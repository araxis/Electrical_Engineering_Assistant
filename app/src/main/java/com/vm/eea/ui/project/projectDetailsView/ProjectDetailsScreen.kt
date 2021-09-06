package com.vm.eea.ui.project.projectDetailsView

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Icon
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.NavigateNext
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.vm.eea.R
import com.vm.eea.ui.NavigationCommand
import com.vm.eea.ui.PropertyItem
import com.vm.eea.ui.components.IconItem
import com.vm.eea.ui.components.Page1
import com.vm.eea.ui.components.NameValueRowItem


@Composable
fun ProjectDetailsScreen(viewModel: ProjectDetailViewModel){
    val state by viewModel.container.stateFlow.collectAsState()

    Page1(pageTitle = "Details") {
        ProjectPropertyList(state.navigationItems){viewModel.onItemSelect(it)}
    }


}


@Composable
fun ProjectPropertyList(navigationItems:List<PropertyItem>, onItemClick:(PropertyItem)->Unit){
    LazyColumn(Modifier.fillMaxSize()){
        itemsIndexed(navigationItems){ _, item->
            Surface(modifier = Modifier
                .fillMaxWidth()
                .clickable { onItemClick(item) }) {
                PropertyItem(item,modifier = Modifier.padding(top = 8.dp,bottom = 8.dp))
            }


        }
    }
}

@Composable
fun PropertyItem(item: PropertyItem,
                 modifier: Modifier =Modifier,
){

    val image: Painter = painterResource(id = R.drawable.v)
    NameValueRowItem(modifier=modifier,nameContent={
        IconItem(first = {

            Image(painter = image,contentDescription = "",
                modifier = Modifier
                    .padding(end = 8.dp)
                    .size(40.dp))
        },second = {
            Text(text = item.Name,modifier = Modifier.padding(start = 8.dp))
        })
    },valueContent={
        IconItem(first = {
            Text(text = item.Value, modifier = Modifier
                .padding(end = 8.dp))
        },
            second = {

                Icon(imageVector = Icons.Filled.NavigateNext,
                    "")


            })
    })

}



@Preview(showBackground = true)
@Composable
fun PreviewProjectPropertyList(){
    val items= listOf(
        PropertyItem("pf",".8") { NavigationCommand("") },
        PropertyItem("pf",".8") { NavigationCommand("") },
        PropertyItem("pf",".8") { NavigationCommand("") },
        PropertyItem("pf",".8") { NavigationCommand("") })
    ProjectPropertyList(items){}
}

@Preview(showBackground = true)
@Composable
fun PropertyItemPreview(){
 PropertyItem(PropertyItem("power factor",
     ".8") { NavigationCommand("") })
}