package com.stefanoq21.composeplayground.ui.screen.home

import androidx.activity.ComponentActivity
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Apps
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.stefanoq21.composeplayground.ui.navigation.NavigationEvent
import com.stefanoq21.composeplayground.ui.navigation.NavigationViewModel
import com.stefanoq21.composeplayground.ui.navigation.Screen
import com.stefanoq21.composeplayground.ui.theme.ComposePlaygroundTheme
import org.koin.androidx.compose.koinViewModel


@Composable
fun HomeInitScreen(
    navigationViewModel: NavigationViewModel = koinViewModel(viewModelStoreOwner = LocalContext.current as ComponentActivity),
) {

    HomeScreen(
        onNavigationEvent = navigationViewModel::onEvent
    )


}


@Composable
fun HomeScreen(
    onNavigationEvent: (NavigationEvent) -> Unit,
) {
    val screens by remember {
        mutableStateOf(Screen.getSubScreens())
    }

    Column(modifier = Modifier.fillMaxSize()) {
        Text(
            modifier = Modifier.padding(16.dp),
            text = "Compose Playground",
            style = MaterialTheme.typography.titleLarge
        )
        LazyColumn(
            modifier = Modifier
                .fillMaxSize(),
        ) {
            item {
                HorizontalDivider()
            }
            items(items = screens) {
                ListItem(
                    modifier = Modifier.clickable {
                        onNavigationEvent(NavigationEvent.OnNavigateToScreen(it))
                    },
                    headlineContent = { Text(text = it.javaClass.simpleName) },
                    leadingContent = {
                        Icon(
                            imageVector = Icons.Outlined.Apps,
                            contentDescription = null,
                        )
                    },
                )
                HorizontalDivider()
            }
        }


    }


}

@Preview
@Composable
fun HomeScreenPreview() {
    ComposePlaygroundTheme {
        Surface {
            HomeScreen(
                onNavigationEvent = {},
            )
        }


    }

}

