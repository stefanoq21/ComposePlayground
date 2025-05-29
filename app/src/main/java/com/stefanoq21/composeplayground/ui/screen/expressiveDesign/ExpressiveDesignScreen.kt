package com.stefanoq21.composeplayground.ui.screen.expressiveDesign

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.automirrored.filled.Label
import androidx.compose.material.icons.automirrored.filled.Message
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Archive
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Coffee
import androidx.compose.material.icons.filled.Contacts
import androidx.compose.material.icons.filled.Download
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.People
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Restaurant
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Snooze
import androidx.compose.material.icons.filled.Work
import androidx.compose.material.icons.outlined.Coffee
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Restaurant
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material.icons.outlined.Work
import androidx.compose.material3.AppBarColumn
import androidx.compose.material3.BottomAppBarDefaults
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ButtonGroup
import androidx.compose.material3.ContainedLoadingIndicator
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.FlexibleBottomAppBar
import androidx.compose.material3.FloatingActionButtonMenu
import androidx.compose.material3.FloatingActionButtonMenuItem
import androidx.compose.material3.FloatingToolbarDefaults
import androidx.compose.material3.FloatingToolbarDefaults.ScreenOffset
import androidx.compose.material3.FloatingToolbarDefaults.floatingToolbarVerticalNestedScroll
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.HorizontalFloatingToolbar
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LoadingIndicator
import androidx.compose.material3.MaterialShapes
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SplitButtonDefaults
import androidx.compose.material3.SplitButtonLayout
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.ToggleFloatingActionButton
import androidx.compose.material3.ToggleFloatingActionButtonDefaults.animateIcon
import androidx.compose.material3.VerticalFloatingToolbar
import androidx.compose.material3.animateFloatingActionButton
import androidx.compose.material3.toShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.semantics.CustomAccessibilityAction
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.customActions
import androidx.compose.ui.semantics.isTraversalGroup
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.stateDescription
import androidx.compose.ui.semantics.traversalIndex
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.datasource.LoremIpsum
import androidx.compose.ui.unit.dp
import com.stefanoq21.composeplayground.ui.theme.ComposePlaygroundTheme

@OptIn(ExperimentalMaterial3ExpressiveApi::class)
@Composable
fun ExpressiveDesignScreen(

) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .verticalScroll(rememberScrollState())
            .padding(16.dp)
    ) {

        LoadingIndicators()

        SplitButtons()

        ButtonGroups()

    }
    MyFloatingActionButtonMenu()

    //toolbars
    /*

 ExpandableVerticalFloatingToolbarSample()
OverflowingHorizontalFloatingToolbarSample()
HorizontalFloatingToolbarAsScaffoldFabSample()
ExitAlwaysBottomAppBarFixedVibrant()
     */
}

@OptIn(ExperimentalMaterial3ExpressiveApi::class)
@Preview(showBackground = true)
@Composable
fun LoadingIndicators() {
    Column(
        Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally

    ) {

        LoadingIndicator()

        ContainedLoadingIndicator(Modifier.padding(12.dp))


        ContainedLoadingIndicator(
            containerShape = MaterialShapes.Pill.toShape()
        )

        LoadingIndicator(
            color = Color.Blue,
            polygons = listOf(
                //MaterialShapes.SoftBurst,
                // MaterialShapes.Cookie9Sided,
                // MaterialShapes.Pentagon,
                MaterialShapes.Pill,
                //MaterialShapes.Sunny,
                // MaterialShapes.Cookie4Sided,
                MaterialShapes.Oval
            )
        )
    }

}


@OptIn(ExperimentalMaterial3ExpressiveApi::class)
@Composable
@Preview(showBackground = true)
fun SplitButtons() {
    var checked by remember { mutableStateOf(false) }

    SplitButtonLayout(
        modifier = Modifier.fillMaxWidth(),
        leadingButton = {
            SplitButtonDefaults.OutlinedLeadingButton(
                onClick = { },
            ) {
                Icon(
                    Icons.Filled.Edit,
                    modifier = Modifier.size(SplitButtonDefaults.LeadingIconSize),
                    contentDescription = null,
                )
                Spacer(Modifier.size(ButtonDefaults.IconSpacing))
                Text("My Button")
            }
        }, trailingButton = {
            SplitButtonDefaults.ElevatedTrailingButton(
                checked = checked,
                onCheckedChange = { checked = it },
                modifier = Modifier.semantics {
                    stateDescription = if (checked) "Expanded" else "Collapsed"
                    contentDescription = "Toggle Button"
                },
            ) {
                val rotation: Float by animateFloatAsState(
                    targetValue = if (checked) 180f else 0f, label = "Trailing Icon Rotation"
                )
                Icon(
                    Icons.Filled.KeyboardArrowDown,
                    modifier = Modifier
                        .size(SplitButtonDefaults.TrailingIconSize)
                        .graphicsLayer {
                            this.rotationZ = rotation
                        },
                    contentDescription = null
                )
            }
        })


    var checked2 by remember { mutableStateOf(false) }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .wrapContentSize()
    ) {
        SplitButtonLayout(leadingButton = {
            SplitButtonDefaults.LeadingButton(
                onClick = { /* Do Nothing */ },
            ) {
                Icon(
                    Icons.Filled.Edit,
                    modifier = Modifier.size(SplitButtonDefaults.LeadingIconSize),
                    contentDescription = null,
                )
                Spacer(Modifier.size(ButtonDefaults.IconSpacing))
                Text("My Button")
            }
        }, trailingButton = {
            SplitButtonDefaults.TrailingButton(
                checked = checked2,
                onCheckedChange = { checked2 = it },
                modifier = Modifier.semantics {
                    stateDescription = if (checked2) "Expanded" else "Collapsed"
                    contentDescription = "Toggle Button"
                },
            ) {
                val rotation: Float by animateFloatAsState(
                    targetValue = if (checked2) 180f else 0f, label = "Trailing Icon Rotation"
                )
                Icon(
                    Icons.Filled.KeyboardArrowDown,
                    modifier = Modifier
                        .size(SplitButtonDefaults.TrailingIconSize)
                        .graphicsLayer {
                            this.rotationZ = rotation
                        },
                    contentDescription = null
                )
            }
        })

        DropdownMenu(expanded = checked2, onDismissRequest = { checked2 = false }) {
            DropdownMenuItem(
                text = { Text("Edit") },
                onClick = {},
                leadingIcon = { Icon(Icons.Outlined.Edit, contentDescription = null) })
            DropdownMenuItem(
                text = { Text("Settings") },
                onClick = {},
                leadingIcon = { Icon(Icons.Outlined.Settings, contentDescription = null) })
            HorizontalDivider()
            DropdownMenuItem(
                text = { Text("Send Feedback") },
                onClick = {},
                leadingIcon = { Icon(Icons.Outlined.Email, contentDescription = null) },
                trailingIcon = { Text("F11", textAlign = TextAlign.Center) })
        }
    }


}


@OptIn(ExperimentalMaterial3ExpressiveApi::class)
@Preview(showBackground = true)
@Composable
fun MyFloatingActionButtonMenu() {
    Box(Modifier.fillMaxSize()) {

        val items = listOf(
            Icons.AutoMirrored.Filled.Message to "Reply",
            Icons.Filled.People to "Reply all",
            Icons.Filled.Contacts to "Forward",
            Icons.Filled.Snooze to "Snooze",
            Icons.Filled.Archive to "Archive",
            Icons.AutoMirrored.Filled.Label to "Label",
        )

        var fabMenuExpanded by rememberSaveable { mutableStateOf(false) }


        FloatingActionButtonMenu(
            modifier = Modifier.align(Alignment.BottomEnd), expanded = fabMenuExpanded, button = {
                ToggleFloatingActionButton(
                    modifier = Modifier
                        .semantics {
                            traversalIndex = -1f
                            stateDescription = if (fabMenuExpanded) "Expanded" else "Collapsed"
                            contentDescription = "Toggle menu"
                        }
                        .animateFloatingActionButton(
                            visible = true, alignment = Alignment.BottomEnd
                        ),
                    checked = fabMenuExpanded,
                    onCheckedChange = { fabMenuExpanded = !fabMenuExpanded }) {
                    val imageVector by remember {
                        derivedStateOf {
                            if (checkedProgress > 0.5f) Icons.Filled.Close else Icons.Filled.Add
                        }
                    }
                    Icon(
                        painter = rememberVectorPainter(imageVector),
                        contentDescription = null,
                        modifier = Modifier.animateIcon({ checkedProgress })
                    )
                }
            }) {
            items.forEachIndexed { i, item ->
                FloatingActionButtonMenuItem(
                    modifier = Modifier.semantics {
                        isTraversalGroup = true
                        if (i == items.size - 1) {
                            customActions = listOf(
                                CustomAccessibilityAction(
                                    label = "Close menu", action = {
                                        fabMenuExpanded = false
                                        true
                                    })
                            )
                        }
                    },
                    onClick = { fabMenuExpanded = false },
                    icon = { Icon(item.first, contentDescription = null) },
                    text = { Text(text = item.second) },
                )
            }
        }
    }
}


@OptIn(ExperimentalMaterial3ExpressiveApi::class)
@Preview(showBackground = true)
@Composable
fun ButtonGroups() {
    MultiSelectConnectedButtonGroupSample()
    SingleSelectConnectedButtonGroupSample()


    val numButtons = 10
    ButtonGroup(
        overflowIndicator = { menuState ->
            IconButton(
                onClick = {
                    if (menuState.isExpanded) {
                        menuState.dismiss()
                    } else {
                        menuState.show()
                    }
                }
            ) {
                Icon(
                    imageVector = Icons.Filled.MoreVert,
                    contentDescription = null
                )
            }
        }
    ) {
        for (i in 0 until numButtons) {
            clickableItem(onClick = {}, label = "$i")
        }
    }


}


@OptIn(ExperimentalMaterial3ExpressiveApi::class)
@Preview(showBackground = true)
@Composable
fun MultiSelectConnectedButtonGroupSample() {
    //androidx.compose.material3.samples.MultiSelectConnectedButtonGroupSample

    val options = listOf("Work", "Restaurant", "Coffee")
    val unCheckedIcons =
        listOf(Icons.Outlined.Work, Icons.Outlined.Restaurant, Icons.Outlined.Coffee)
    val checkedIcons = listOf(Icons.Filled.Work, Icons.Filled.Restaurant, Icons.Filled.Coffee)
    val checked = remember { mutableStateListOf(false, false, false) }

    ButtonGroup(
        modifier = Modifier.padding(horizontal = 8.dp),
        overflowIndicator = {}
    ) {
        options.forEachIndexed { index, label ->
            toggleableItem(
                checked = checked[index],
                onCheckedChange = { checked[index] = it },
                label = label,
                icon = {
                    Icon(
                        if (checked[index]) checkedIcons[index] else unCheckedIcons[index],
                        contentDescription = null
                    )
                }
            )
        }
    }


}


@OptIn(ExperimentalMaterial3ExpressiveApi::class)
@Preview(showBackground = true)
@Composable
fun SingleSelectConnectedButtonGroupSample() {
    //androidx.compose.material3.samples.SingleSelectConnectedButtonGroupSample

    val options = listOf("Work", "Restaurant", "Coffee")
    val unCheckedIcons =
        listOf(Icons.Outlined.Work, Icons.Outlined.Restaurant, Icons.Outlined.Coffee)
    val checkedIcons = listOf(Icons.Filled.Work, Icons.Filled.Restaurant, Icons.Filled.Coffee)
    var selectedIndex by remember { mutableIntStateOf(0) }

    ButtonGroup(
        modifier = Modifier.padding(horizontal = 8.dp),
        overflowIndicator = {}
    ) {
        options.forEachIndexed { index, label ->
            toggleableItem(
                checked = selectedIndex == index,
                onCheckedChange = { selectedIndex = index },
                label = label,
                icon = {
                    Icon(
                        if (selectedIndex == index) checkedIcons[index] else unCheckedIcons[index],
                        contentDescription = null
                    )
                }
            )
        }
    }
}


@OptIn(ExperimentalMaterial3ExpressiveApi::class)
@Preview
@Composable
fun ExpandableVerticalFloatingToolbarSample() {
    var expanded by rememberSaveable { mutableStateOf(true) }
    Scaffold(
        content = { innerPadding ->
            Box(Modifier.padding(innerPadding)) {
                LazyColumn(
                    // Apply a floatingToolbarVerticalNestedScroll Modifier toggle the expanded
                    // state of the HorizontalFloatingToolbar.
                    modifier =
                        Modifier.floatingToolbarVerticalNestedScroll(
                            expanded = expanded,
                            onExpand = { expanded = true },
                            onCollapse = { expanded = false },
                        ),
                    state = rememberLazyListState(),
                    contentPadding = innerPadding,
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    val list = (0..75).map { it.toString() }
                    items(count = list.size) {
                        Text(
                            text = list[it],
                            style = MaterialTheme.typography.bodyLarge,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 16.dp)
                        )
                    }
                }
                VerticalFloatingToolbar(
                    modifier = Modifier
                        .align(Alignment.CenterEnd)
                        .offset(x = -ScreenOffset),
                    expanded = expanded,
                    leadingContent = {
                        IconButton(
                            onClick = {}
                        ) {
                            Icon(
                                imageVector = Icons.Filled.MoreVert,
                                contentDescription = null
                            )
                        }
                    },
                    trailingContent = {
                        AppBarColumn(
                            overflowIndicator = { menuState ->
                                IconButton(
                                    onClick = {
                                        if (menuState.isExpanded) {
                                            menuState.dismiss()
                                        } else {
                                            menuState.show()
                                        }
                                    }
                                ) {
                                    Icon(
                                        imageVector = Icons.Filled.MoreVert,
                                        contentDescription = ""
                                    )
                                }
                            }
                        ) {
                            clickableItem(
                                onClick = {},
                                icon = {
                                    Icon(
                                        Icons.Filled.Download,
                                        contentDescription = ""
                                    )
                                },
                                label = "Download"
                            )

                            clickableItem(
                                onClick = {},
                                icon = {
                                    Icon(
                                        Icons.Filled.Settings,
                                        contentDescription = ""
                                    )
                                },
                                label = "Settings"
                            )

                        }
                    },
                    content = {
                        FilledIconButton(
                            modifier = Modifier.height(64.dp),
                            onClick = { }
                        ) {
                            Icon(Icons.Filled.Add, contentDescription = null)
                        }
                    },
                )


            }
        }
    )
}

@OptIn(ExperimentalMaterial3ExpressiveApi::class)
@Preview
@Composable
fun OverflowingHorizontalFloatingToolbarSample() {
    Scaffold(
        content = { innerPadding ->
            Box(Modifier.padding(innerPadding)) {
                LazyColumn(
                    state = rememberLazyListState(),
                    contentPadding = innerPadding,
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    val list = (0..75).map { it.toString() }
                    items(count = list.size) {
                        Text(
                            text = list[it],
                            style = MaterialTheme.typography.bodyLarge,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 16.dp)
                        )
                    }
                }
                HorizontalFloatingToolbar(
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .offset(y = -ScreenOffset),
                    expanded = true,
                    floatingActionButton = {
                        FloatingToolbarDefaults.VibrantFloatingActionButton(
                            onClick = { /* */ },
                        ) {
                            Icon(Icons.Filled.Add, contentDescription = "")
                        }
                    },
                    content = {
                        FilledIconButton(
                            modifier = Modifier.width(64.dp),
                            onClick = { }
                        ) {
                            Icon(Icons.Filled.Add, contentDescription = null)
                        }
                        IconButton(
                            onClick = {}
                        ) {
                            Icon(
                                imageVector = Icons.Filled.MoreVert,
                                contentDescription = null
                            )
                        }
                        IconButton(
                            onClick = {}
                        ) {
                            Icon(
                                imageVector = Icons.Filled.Settings,
                                contentDescription = null
                            )
                        }
                    }
                )
            }
        }
    )
}


@OptIn(ExperimentalMaterial3ExpressiveApi::class)
@Preview
@Composable
fun HorizontalFloatingToolbarAsScaffoldFabSample() {
    var expanded by rememberSaveable { mutableStateOf(true) }
    val vibrantColors = FloatingToolbarDefaults.vibrantFloatingToolbarColors()
    Scaffold(
        floatingActionButton = {
            HorizontalFloatingToolbar(
                expanded = expanded,
                floatingActionButton = {
                    FloatingToolbarDefaults.VibrantFloatingActionButton(
                        onClick = { expanded = !expanded }
                    ) {
                        Icon(Icons.Filled.Add, null)
                    }
                },
                colors = vibrantColors,
                content = {
                    IconButton(onClick = { }) {
                        Icon(Icons.Filled.Person, contentDescription = null)
                    }
                    IconButton(onClick = { }) {
                        Icon(Icons.Filled.Edit, contentDescription = null)
                    }
                    IconButton(onClick = { }) {
                        Icon(Icons.Filled.Favorite, contentDescription = null)
                    }
                    IconButton(onClick = { }) {
                        Icon(Icons.Filled.MoreVert, contentDescription = null)
                    }
                },
            )
        },
        floatingActionButtonPosition = FabPosition.End,
    ) { innerPadding ->
        Box(Modifier.padding(innerPadding)) {
            Column(
                Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
                    .then(
                        Modifier.floatingToolbarVerticalNestedScroll(
                            expanded = expanded,
                            onExpand = { expanded = true },
                            onCollapse = { expanded = false },
                        )
                    )
                    .verticalScroll(rememberScrollState())
            ) {
                Text(text = remember { LoremIpsum().values.first() })
            }
        }
    }
}


@OptIn(ExperimentalMaterial3ExpressiveApi::class, ExperimentalMaterial3Api::class)
@Preview
@Composable
fun ExitAlwaysBottomAppBarFixedVibrant() {
    val scrollBehavior = BottomAppBarDefaults.exitAlwaysScrollBehavior()
    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        bottomBar = {
            FlexibleBottomAppBar(
                horizontalArrangement = BottomAppBarDefaults.FlexibleFixedHorizontalArrangement,
                scrollBehavior = scrollBehavior,
                containerColor =
                    MaterialTheme.colorScheme.primaryContainer,
                content = {
                    IconButton(onClick = { }) {
                        Icon(
                            Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = null
                        )
                    }
                    IconButton(onClick = { }) {
                        Icon(
                            Icons.AutoMirrored.Filled.ArrowForward,
                            contentDescription = null
                        )
                    }
                    FilledIconButton(
                        modifier = Modifier.width(56.dp),
                        onClick = { }
                    ) {
                        Icon(Icons.Filled.Add, contentDescription = null)
                    }
                    IconButton(onClick = { }) {
                        Icon(Icons.Filled.Check, contentDescription = null)
                    }
                    IconButton(onClick = { }) {
                        Icon(Icons.Filled.Edit, contentDescription = null)
                    }
                }
            )
        },
        content = { innerPadding ->
            LazyColumn(
                contentPadding = innerPadding,
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                val list = (0..75).map { it.toString() }
                items(count = list.size) {
                    Text(
                        text = list[it],
                        style = MaterialTheme.typography.bodyLarge,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp)
                    )
                }
            }
        }
    )
}


@Preview
@Composable
fun ExpressiveDesignScreenPreview() {
    ComposePlaygroundTheme {
        Surface {
            ExpressiveDesignScreen()
        }
    }
}

