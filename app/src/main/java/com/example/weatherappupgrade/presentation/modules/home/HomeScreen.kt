package com.example.weatherappupgrade.presentation.modules.home

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.rounded.CheckCircle
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.weatherappupgrade.R
import com.example.weatherappupgrade.presentation.componets.Tabs.CustomTab
import com.example.weatherappupgrade.presentation.navigation.Screen
import com.example.weatherappupgrade.presentation.ui.theme.Purple40
import com.example.weatherappupgrade.presentation.ui.theme.WeatherAppUpgradeTheme
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HomeScreen (navController: NavHostController) {
    Scaffold (modifier = Modifier.fillMaxSize()) {
        val items = remember {
            listOf("Man", "Woman", "Hieu")
        }

        var selectedIndex by remember {
            mutableStateOf(0)
        }


        WeatherAppUpgradeTheme {
            Box (
                modifier = Modifier.fillMaxSize()
            ) {
                Column(
                    modifier = Modifier.fillMaxSize().background(Purple40)
                ){
                    Column (
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(50.dp)
                            .wrapContentWidth(align = Alignment.CenterHorizontally)
                    ) {
                        Text("Customer some of components",  color = Color.White, modifier = Modifier.fillMaxSize() )
                    }
                    Spacer(modifier = Modifier.height(16.dp))

                    Surface (modifier = Modifier.fillMaxWidth().size(60.dp)){
                        Row (modifier = Modifier.run { fillMaxSize().align(alignment = Alignment.CenterHorizontally).align(alignment = Alignment.End)}){
                            Column (modifier = Modifier.size(width = 120.dp, height = 45.dp)) {
                                FilledButton(onClick = {
                                    navController.navigate(Screen.Weather.toString())
                                })
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
                        BottomSheetDemo()
                    }

                    Surface (modifier = Modifier.fillMaxWidth().height(100.dp).fillMaxWidth()) {
                        Box(modifier = Modifier.height(50.dp)){
                            CustomTab(
                                selectedIndex = selectedIndex,
                                items = items,
                                onSelectionChange = {
                                    selectedIndex = it
                                }
                            )
                        }
                    }

                    Surface (modifier = Modifier.fillMaxWidth().height(50.dp).fillMaxWidth()) {
                       Column {
                           Icon(
                               imageVector = ImageVector.vectorResource(id = R.drawable.active_user_circle),
                               contentDescription = "Active user",
                               tint = Color.Red
                           )
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
