package com.example.weatherappupgrade.presentation.componets

import androidx.compose.animation.core.animateDp
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.*
import androidx.compose.foundation.interaction.InteractionSource
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.TabPosition
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CustomTab() {
    var selectedTabIndex by remember { mutableStateOf(0) }
    val configuration = LocalConfiguration.current

    val screenWidth = configuration.screenWidthDp.dp

    val pages = listOf("kotlin", "java")

    val indicator = @Composable { tabPositions: List<TabPosition> ->
        CustomIndicator(tabPositions, selectedTabIndex)
    }

    Column (
        modifier = Modifier
        .fillMaxWidth()
    ) {
        // Custom tabs
        ScrollableTabRow(
            modifier = Modifier
                .height(50.dp)
                .fillMaxWidth()
               ,
            selectedTabIndex =  selectedTabIndex,
            indicator = indicator
        ) {
            pages.forEachIndexed { index, s ->
                Box(
                    modifier = Modifier
                    .zIndex(6f)
                    .height(50.dp)
                    .width(screenWidth.div(2))
                    .background(Color.Transparent)
                    .clickableWithoutRipple(
                        interactionSource = remember { MutableInteractionSource() },
                        onClick = { selectedTabIndex = index}
                    )
                ){
                    Text("Tab $index", color = Color.Black)
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
    }
}

@Composable
fun CustomIndicator(tabPositions: List<TabPosition>, selectedTabIndex: Int){
    val configuration = LocalConfiguration.current

    val screenWidth = configuration.screenWidthDp.dp

    val transition = updateTransition(selectedTabIndex, label = "")
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
            .wrapContentSize(align = Alignment.Center)
            .width(indicatorEnd - indicatorStart)
            .fillMaxSize()
            .background(color = Color.Gray)
            .zIndex(1f)
    )
}

fun Modifier.clickableWithoutRipple(
    interactionSource: MutableInteractionSource,
    onClick: () -> Unit
) = composed (
    factory = {
        this.then(
            Modifier.clickable(
                interactionSource = interactionSource,
                indication = null,
                onClick = { onClick() }
            )
        )
    }
)