package com.example.weatherappupgrade.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.ActivityResultLauncher
import androidx.compose.animation.core.animateDp
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.*
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.rounded.CheckCircle
import androidx.compose.material3.*
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.weatherappupgrade.presentation.ui.theme.Purple40
import com.example.weatherappupgrade.presentation.ui.theme.WeatherAppUpgradeTheme
import kotlinx.coroutines.launch
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.zIndex

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
                            BottomSheetDemo()
                        }

                        Surface (modifier = Modifier.fillMaxWidth().size(80.dp)) {
                            TabRowExample()
                        }

                        Surface (modifier = Modifier.fillMaxWidth().size(80.dp)) {
                            CustomTabRowCgsi()
                        }

                        Surface (modifier = Modifier.fillMaxWidth().size(200.dp).background(Color.Gray)) {
                            CustomScrollableTabRow()
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
fun CustomTabRow(
    selectedTabIndex: Int,
    modifier: Modifier = Modifier,
    containerColor: Color = TabRowDefaults.containerColor,
    contentColor: Color = TabRowDefaults.contentColor,
    indicator: @Composable (tabPositions: List<TabPosition>) -> Unit = @Composable { tabPositions ->
        if (selectedTabIndex < tabPositions.size) {
            TabRowDefaults.Indicator(
                Modifier.tabIndicatorOffset(tabPositions[selectedTabIndex])
            )
        }
    },
    divider: @Composable () -> Unit = @Composable {
        Divider()
    },
    tabs: @Composable () -> Unit
) {
    Column {
        // Content of your screen above the TabRow
        // ...

        // TabRow with customization options
        TabRow(
            selectedTabIndex = selectedTabIndex,
            modifier = modifier,
            containerColor = containerColor,
            contentColor = contentColor,
            indicator = indicator,
            divider = divider
        ) {
            // Call the provided @Composable function for tabs
            tabs()
        }

        // Content of your screen below the TabRow
        // ...
    }
}
@Composable
fun TabRowExample (){
    var selectedTabIndex by remember { mutableStateOf(0) }

    Column {
        CustomTabRow(
            selectedTabIndex = selectedTabIndex,
            modifier = Modifier.fillMaxWidth(),
            containerColor = Color.Gray,
            contentColor = Color.White
            ) {
                Tab(
                    selected = selectedTabIndex == 0,
                    onClick = { selectedTabIndex = 0 }
                ) {
                    Text("Tab1", modifier = Modifier.padding(8.dp))
                }

                Tab(
                    selected = selectedTabIndex == 1,
                    onClick = { selectedTabIndex = 1 }
                ) {
                    Text("Tab2", modifier = Modifier.padding(8.dp))
                }
        }
    }
}

@Composable
fun CustomTab(
    isSelected: Boolean,
    onClick: () -> Unit,
    content: @Composable () -> Unit
) {
    Box(
        modifier = Modifier
            .background(Color.Transparent)
            .padding(8.dp)
            .clickable { onClick() }
    ) {
//        Box(modifier = Modifier
//            .height(50.dp)
//            .width(140.dp)
//            .background(if (isSelected) Color.White else Color.Gray, ShapeDefaults.Large)
//            .clickable { onClick() }
//        )
//        {
            content()
//        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CustomScrollableTabRow() {
    var selectedTabIndex by remember { mutableStateOf(0) }
    val tabBackgroundOffset by animateDpAsState(
        targetValue = ((selectedTabIndex + 1) * 150).dp, label = selectedTabIndex.toString() // Adjust the value based on your design
    )
    val pages = listOf("kotlin","java")

    val indicator = @Composable { tabPositions: List<TabPosition> ->
        CustomIndicator(tabPositions, selectedTabIndex)
    }

    Column {
        // Background that translates between tabs
        Box(
            modifier = Modifier
                .background(Color.Cyan)
                .width(150.dp)
                .height(48.dp)
                .graphicsLayer(translationX = tabBackgroundOffset.value)
        )

        // Custom tabs
        ScrollableTabRow(
            modifier = Modifier.height(50.dp),
            selectedTabIndex =  selectedTabIndex,
            indicator = indicator
        ) {
            pages.forEachIndexed { index, s ->
                CustomTab(
                    isSelected = selectedTabIndex == index,
                    onClick = { selectedTabIndex = index }
                ) {
                    // Custom content for each tab
                    Box(
                        modifier = Modifier
                            .padding(8.dp)
                    ) {
                        Text("Tab $index", color = Color.Black)
                    }
                }
            }
        }

        HorizontalPager(
            modifier = Modifier.fillMaxSize(),
            pageCount = pages.size
        ) {

            // Content based on selectedTabIndex
            when (selectedTabIndex) {
                0 -> {
                    // Content for Tab 0
                    Text("Content for Tab 0")
                }

                1 -> {
                    // Content for Tab 1
                    Text("Content for Tab 1")
                }
            }
        }

        // Content below the TabRow
        // ...
    }
}

@Composable
fun CustomIndicator(tabPositions: List<TabPosition>, selectedTabIndex: Int){
    val transition = updateTransition(selectedTabIndex, label = "Transition")
    val indicatorStart by transition.animateDp(
        transitionSpec = {
            if (initialState < targetState) {
                spring(dampingRatio = 1f, stiffness = 50f)
            }else {
                spring(dampingRatio = 1f, stiffness = 1000f)
            }
        },
        label = ""
    ){
        tabPositions[it].left
    }

    val indicatorEnd by transition.animateDp(
        transitionSpec = {
            if (initialState < targetState) {
                spring(dampingRatio = 1f, stiffness = 1000f)
            } else {
                spring(dampingRatio = 1f, stiffness = 50f)
            }
        }, label = ""
    ) {
        tabPositions[it].right
    }

    Box(
        Modifier
            .offset(x = indicatorStart)
            .wrapContentSize(align = Alignment.BottomStart)
            .width(indicatorEnd - indicatorStart)
            .padding(2.dp)
            .fillMaxSize()
            .background(color = Color(0xFFFF7455), RoundedCornerShape(50))
            .border(BorderStroke(2.dp, Color(0xFFC13D25)), RoundedCornerShape(50))
            .zIndex(1f)
    )
}

@Composable
fun CustomTabRowCgsi() {
    var selectedTabIndex by remember { mutableStateOf(0) }

    Column {
        CustomTabRow(
            selectedTabIndex = selectedTabIndex,
            modifier = Modifier.fillMaxWidth(),
            containerColor = Color.Gray,
            contentColor = Color.White
        ) {
            CustomTab(
                isSelected = selectedTabIndex == 0,
                onClick = { selectedTabIndex = 0 },
            ) {
                Text("Tab1", modifier = Modifier.padding(8.dp))
            }

            CustomTab(
                isSelected = selectedTabIndex == 1,
                onClick = { selectedTabIndex = 1 },
            ) {
                Text("Tab2", modifier = Modifier.padding(8.dp))
            }
        }
    }
}
