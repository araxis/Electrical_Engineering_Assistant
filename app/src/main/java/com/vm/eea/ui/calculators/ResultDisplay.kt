package com.vm.eea.ui.calculators

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp


@Composable
fun <T> ResultDisplay(state: FormState<T>, mapToString:(T)->String) {


        val calculating:@Composable (String)->Unit={ Text(text = it,modifier = Modifier.fillMaxWidth(),textAlign = TextAlign.Center)}
        val failed:@Composable (String)->Unit={Text(text = it,modifier = Modifier.fillMaxWidth(),textAlign = TextAlign.Center)}
        val succeed: @Composable (T)->Unit = {Text(text = mapToString(it),modifier = Modifier.fillMaxWidth(),textAlign = TextAlign.Center)}


    ResultDisplay(state,calculating,failed,succeed)

}

@Composable
@JvmName("ResultDisplay2")
fun <T> ResultDisplay(state: FormState<T>, succeedContent:@Composable (T)->Unit) {


    val calculating:@Composable (String)->Unit={ Text(text = it,modifier = Modifier.fillMaxWidth(),textAlign = TextAlign.Center)}
    val failed:@Composable (String)->Unit={Text(text = it,modifier = Modifier.fillMaxWidth(),textAlign = TextAlign.Center)}
    val succeed: @Composable (T)->Unit = {succeedContent(it)}


    ResultDisplay(state,calculating,failed,succeed)

}

@Composable
fun <T> ResultDisplay(state: FormState<T>,
                      calculating:@Composable (String)->Unit,
                      failed:@Composable (String)->Unit,
                      succeed:@Composable (T)->Unit  ) {

    val content:@Composable ()->Unit = when(state){
        is FormState.Calculating -> {
            {calculating(state.value)}
        }
        is FormState.Failed -> {
            {failed(state.error)}
        }
        is FormState.Ready -> {
            {succeed(state.value)}
        }
    }

    ResultDisplay(content)

}

@Composable
fun  ResultDisplay(content:@Composable ()->Unit ) {
    Surface(modifier = Modifier.fillMaxWidth()) {
        Box(modifier = Modifier.fillMaxWidth().padding(8.dp)) {
            content()
        }

    }

}