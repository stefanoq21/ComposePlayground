package com.stefanoq21.composeplayground.ui.screen.expressiveDesign

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.stefanoq21.composeplayground.ui.theme.ComposePlaygroundTheme


@Composable
fun ExpressiveDesignScreen(

) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .verticalScroll(rememberScrollState())
            .padding(16.dp)
    ) {


    }

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

