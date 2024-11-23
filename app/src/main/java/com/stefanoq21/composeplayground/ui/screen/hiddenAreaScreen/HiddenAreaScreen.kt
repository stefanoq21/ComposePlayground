package com.stefanoq21.composeplayground.ui.screen.hiddenAreaScreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.stefanoq21.composeplayground.R
import com.stefanoq21.composeplayground.ui.theme.ComposePlaygroundTheme

/** https://developer.android.com/develop/ui/compose/graphics/draw/modifiers   */

@Composable
fun HiddenAreaScreen(

) {
    var pointerOffset by remember {
        mutableStateOf(Offset(0f, 0f))
    }
    Column(
        modifier = Modifier
            .fillMaxSize()

            .pointerInput("dragging") {
                detectDragGestures { change, dragAmount ->
                    pointerOffset += dragAmount
                    if (pointerOffset.x < 0)
                        pointerOffset = Offset(0f, pointerOffset.y)
                    if (pointerOffset.y < 0)
                        pointerOffset = Offset(pointerOffset.x, 0f)
                    if(pointerOffset.x> size.width)
                        pointerOffset = Offset(size.width.toFloat(), pointerOffset.y)
                    if(pointerOffset.y> size.height)
                        pointerOffset = Offset(pointerOffset.x, size.height.toFloat())


                }
            }
            .onSizeChanged {
                pointerOffset = Offset(it.width / 2f, it.height / 2f)
            }
            .drawWithContent {
                drawContent()
                // draws a fully black area with a small keyhole at pointerOffset that’ll show part of the UI.
                drawRect(
                    Brush.radialGradient(
                        listOf(Color.Transparent, Color.Black),
                        center = pointerOffset,
                        radius = 100.dp.toPx(),
                    )
                )
            }
    ) {
        Image(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            painter = painterResource(id = R.drawable.test_background_0),
            contentDescription = null,
            contentScale = ContentScale.Crop
        )
    }
}


@Preview
@Composable
fun GraphicsLayerScreenPreview() {
    ComposePlaygroundTheme {
        Surface {
            HiddenAreaScreen(

            )
        }


    }

}

