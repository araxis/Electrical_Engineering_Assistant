package com.vm.eea.ui.panel.panelResult

import android.content.Intent
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.FileProvider
import com.vm.eea.R
import com.vm.eea.ui.components.GroupPropertyList
import com.vm.eea.ui.components.Page1
import com.vm.eea.ui.motor.motorResult.Effect
import kotlinx.coroutines.launch
import java.io.File

@Composable
fun PanelResultScreen(viewModel: PanelResultViewModel) {
    val state by viewModel.container.stateFlow.collectAsState()
    val context = LocalContext.current
    val effect by viewModel.container.sideEffectFlow.collectAsState(initial = Effect.None())
    val scope= rememberCoroutineScope()
    LaunchedEffect(effect) {
        launch {
            scope.launch {
                when(effect){
                    is Effect.Share -> {

                        val path = context.filesDir
                        val letDirectory = File(path, "reports")
                        letDirectory.mkdirs()
                        val file = File(letDirectory, "load list.html")
                        if(file.exists()){
                            file.delete()
                        }
                        file.appendText(state.report)

                        val uri = FileProvider.getUriForFile(
                            context,
                            "com.vm.eea..ui.MainActivity.provider",
                            file)

                        val shareIntent = Intent()
                        shareIntent.action = Intent.ACTION_SEND
                        shareIntent.putExtra(Intent.EXTRA_STREAM,uri)
                        shareIntent.type = "text/html"
                        context.startActivity(Intent.createChooser(shareIntent, "Share report"))
                    }
                    is Effect.None ->{}
                }


            }
        }
    }
    Page1(pageTitle = "Panel Result",actions = {
        IconButton(onClick = {
        viewModel.share()

    }) { Icon(painter = painterResource(id = R.drawable.share), null) }
        IconButton(onClick = {viewModel.preview()}) {
            Icon(painter = painterResource(id = R.drawable.preview), null)
        }
    }) {
        if(state.preview){
            AndroidView(factory = {
                WebView(it).apply {
                    layoutParams = ViewGroup.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.MATCH_PARENT
                    )

                    webViewClient = WebViewClient()
                    loadDataWithBaseURL(null,state.report , "text/html" , "utf-8",null)
                }
            },modifier = Modifier.fillMaxSize(), update = {
                it.loadDataWithBaseURL(null,state.report , "text/html" , "utf-8",null)
            })
        }else{
            GroupPropertyList(state.resultItems)
        }
    }
}