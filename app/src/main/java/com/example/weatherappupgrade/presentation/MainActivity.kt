package com.example.weatherappupgrade.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.ActivityResultLauncher
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.rounded.CheckCircle
import androidx.compose.material3.*
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.weatherappupgrade.presentation.ui.theme.Purple40
import com.example.weatherappupgrade.presentation.ui.theme.WeatherAppUpgradeTheme
import kotlinx.coroutines.launch
import retrofit2.http.Body

class MainActivity : ComponentActivity() {
//    private val viewModel: WeatherViewModel by viewModels()
    private lateinit var permissionLauncher: ActivityResultLauncher<Array<String>>
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
//        permissionLauncher = registerForActivityResult(
//            ActivityResultContracts.RequestMultiplePermissions()
//        ) {
//            viewModel.loadWeatherInfo()
//        }
//        permissionLauncher.launch(arrayOf(
//            Manifest.permission.ACCESS_FINE_LOCATION,
//            Manifest.permission.ACCESS_COARSE_LOCATION,
//        ))
        setContent {
            val interactionSource = remember { MutableInteractionSource() }

            WeatherAppUpgradeTheme {
                Box (
                    modifier = Modifier.fillMaxSize()
                ) {
                    Column(
                        modifier = Modifier.fillMaxSize().background(Purple40)
                    ){
                        Text("Customer some of components", modifier = Modifier.align(Alignment.CenterHorizontally), color = Color.White )
                        Spacer(modifier = Modifier.height(16.dp))

                        Surface (modifier = Modifier.fillMaxWidth().size(60.dp)){
                            Row (modifier = Modifier.run { fillMaxSize().align(alignment = Alignment.CenterHorizontally).align(alignment = Alignment.End)}){
                                Column (modifier = Modifier.size(width = 120.dp, height = 45.dp)) {
                                    FilledButton(onClick = {})
                                }
                                Column (modifier = Modifier.size(width = 120.dp, height = 45.dp)) {
                                    OutlinedButtonExample(onClick = {})
                                }
                                Column (modifier = Modifier.size(width = 120.dp, height = 45.dp)) {
                                    TextButtonExample(onClick = {})
                                }
                            }
                        }

                        Surface (modifier = Modifier.fillMaxWidth().size(80.dp)) {
                            Text("Bottom sheet !", modifier = Modifier.align(Alignment.CenterHorizontally), color = Color.Black )
                            BottomSheetDemo()
                        }
                    }


                }
            }
        }
    }

}

@Composable
fun FilledButton(onClick: () -> Unit) {
    Button(
        onClick = { onClick() }
    ) {
        Icon(Icons.Rounded.CheckCircle, contentDescription = "Localized description")
        Spacer(modifier = Modifier.width(4.dp))
        Text("Filled")
    }
}

@Composable
fun OutlinedButtonExample(onClick: () -> Unit) {
    OutlinedButton(onClick = { onClick() }) {
        Text("Outlined")
    }
}

@Composable
fun TextButtonExample(onClick: () -> Unit) {
    TextButton(
        onClick = { onClick() }
    ) {
        Text("Text Button")
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomSheetDemo() {
    // [START android_compose_layout_material_bottom_sheet]
//    ModalBottomSheet(onDismissRequest = { /* Executed when the sheet is dismissed */ }) {
//        // Sheet content
//    }
    // [END android_compose_layout_material_bottom_sheet]

    // [START android_compose_layout_material_bottom_sheet2]
    val sheetState = rememberModalBottomSheetState()
    val scope = rememberCoroutineScope()
    var showBottomSheet by remember { mutableStateOf(false) }
    Scaffold(
        floatingActionButton = {
            ExtendedFloatingActionButton(
                text = { Text("Show bottom sheet") },
                icon = { Icon(Icons.Filled.Add, contentDescription = "") },
                onClick = {
                    showBottomSheet = true
                }
            )
        }
    ) { contentPadding ->
        // Screen content
        // [START_EXCLUDE silent]
        Box(modifier = Modifier.padding(contentPadding)) { /* ... */ }
        // [END_EXCLUDE]

        if (showBottomSheet) {
            ModalBottomSheet(
                onDismissRequest = {
                    showBottomSheet = false
                },
                sheetState = sheetState
            ) {
                // Sheet content
                Button(
                    modifier = Modifier.padding(horizontal = 0.dp, vertical = 16.dp),
                    onClick = {
                        scope.launch { sheetState.hide() }.invokeOnCompletion {
                            if (!sheetState.isVisible) {
                                showBottomSheet = false
                            }
                        }
                }) {
                    Text("Hide bottom sheet")
                }
                Spacer(modifier = Modifier.height(16.dp))
            }
        }
    }
    // [END android_compose_layout_material_bottom_sheet2]
}

@Composable
fun TabDemo (){
    var state by remember { mutableStateOf(0) }
    val titles = listOf("Tab 1", "Tab 2", "Tab 3 with lots of text")

    Column(){

    }
}

@Composable
fun HorizontalDivider () {
    Surface (modifier = Modifier.width(10.dp).background(Color.Black)) {  }
}

@ExperimentalMaterial3Api
@Composable
fun PrimaryScrollableTabRow(
    selectedTabIndex: Int,
    modifier: Modifier = Modifier,
    scrollState: ScrollState = rememberScrollState(),
    containerColor: Color = TabRowDefaults.containerColor,
    contentColor: Color = TabRowDefaults.contentColor,
    edgePadding: Dp = TabRowDefaults.,
    indicator: @Composable (tabPositions: List<TabPosition>) -> Unit = @Composable { tabPositions ->
        if (selectedTabIndex < tabPositions.size) {
            val width by animateDpAsState(targetValue = tabPositions[selectedTabIndex].width)
            TabRowDefaults.Indicator(
                Modifier.tabIndicatorOffset(tabPositions[selectedTabIndex]),
                height = width
            )
        }
    },
    divider: @Composable () -> Unit = @Composable {
        HorizontalDivider()
    },
    tabs: @Composable () -> Unit
): Unit {
}