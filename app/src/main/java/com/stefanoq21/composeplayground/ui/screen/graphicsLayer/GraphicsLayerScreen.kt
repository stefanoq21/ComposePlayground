package com.stefanoq21.composeplayground.ui.screen.graphicsLayer

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.ColorMatrix
import androidx.compose.ui.graphics.layer.drawLayer
import androidx.compose.ui.graphics.rememberGraphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.stefanoq21.composeplayground.R
import com.stefanoq21.composeplayground.ui.theme.ComposePlaygroundTheme
import kotlinx.coroutines.launch
/** https://developer.android.com/develop/ui/compose/graphics/draw/modifiers    */

@Composable
fun GraphicsLayerScreen(

) {
    val graphicsLayer = rememberGraphicsLayer()
    val coroutineScope = rememberCoroutineScope()

    Column(
        modifier = Modifier
            .fillMaxWidth().verticalScroll(rememberScrollState())
            .padding(16.dp)
    ) {
        Text(
            modifier = Modifier.padding(vertical = 16.dp),
            text = "Graphics Layer",
            style = MaterialTheme.typography.titleLarge
        )

        Box(modifier = Modifier
            .drawWithContent {
                graphicsLayer.record {
                    this@drawWithContent.drawContent()
                }
                drawLayer(graphicsLayer)
            }
            .clickable {
                coroutineScope.launch {
                    val bitmap = graphicsLayer.toImageBitmap()

                }
            }

        ) {
            Text(
                modifier = Modifier,
                text = "Graphics Layer",
                style = MaterialTheme.typography.titleLarge
            )
        }


        val images = remember {
            mutableStateListOf(
                R.drawable.test_background_0,
                R.drawable.test_background_1,
                R.drawable.test_background_2,
                R.drawable.test_background_3
            )
        }



        Box(
            modifier = Modifier.fillMaxWidth()

        ) {
            val pagerState = rememberPagerState(
                initialPage = 0,
                pageCount = { images.size }
            )
            HorizontalPager(
                state = pagerState
            ) { index ->
                Image(
                    modifier = Modifier.fillMaxWidth(),
                    painter = painterResource(id = images[index]),
                    contentDescription = null,
                    contentScale = ContentScale.Crop
                )
            }

            Text(
                modifier = Modifier
                    .align(Alignment.Center)
                    .blendMode(BlendMode.Difference),
                text = "Graphics Layer",
                color = Color.White,
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.displayLarge
            )
        }
        Box(
            modifier = Modifier.fillMaxWidth()

        ) {
            val pagerState = rememberPagerState(
                initialPage = 0,
                pageCount = { images.size }
            )
            HorizontalPager(
                modifier = Modifier
                    .colorFilter(ColorFilter.colorMatrix(ColorMatrix().apply {
                        setToSaturation(0f)
                    })),
                state = pagerState
            ) { index ->
                Image(
                    modifier = Modifier.fillMaxWidth(),
                    painter = painterResource(id = images[index]),
                    contentDescription = null,
                    contentScale = ContentScale.Crop
                )
            }

            Text(
                modifier = Modifier
                    .align(Alignment.Center)
                    .blendMode(BlendMode.Difference),
                text = "Graphics Layer",
                color = Color.White,
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.displayLarge
            )
        }

        var pointerOffset by remember {
            mutableStateOf(Offset(0f, 0f))
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .pointerInput("dragging") {
                    detectDragGestures { change, dragAmount ->
                        pointerOffset += dragAmount
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
                modifier = Modifier.fillMaxWidth(),
                painter = painterResource(id = images[0]),
                contentDescription = null,
                contentScale = ContentScale.Crop
            )
        }
    }

}

private fun Modifier.blendMode(blendMode: BlendMode): Modifier {
    return this.drawWithCache {
        val graphicsLayer = obtainGraphicsLayer()
        graphicsLayer.apply {
            record {
                drawContent()
            }
            this.blendMode = blendMode
        }
        onDrawWithContent {
            drawLayer(graphicsLayer)
        }
    }
}


private fun Modifier.colorFilter(colorFilter: ColorFilter): Modifier {
    return this.drawWithCache {
        val graphicsLayer = obtainGraphicsLayer()
        graphicsLayer.apply {
            record {
                drawContent()
            }
            this.colorFilter = colorFilter
        }
        onDrawWithContent {
            drawLayer(graphicsLayer)
        }
    }
}


@Preview
@Composable
fun GraphicsLayerScreenPreview() {
    ComposePlaygroundTheme {
        Surface {
            GraphicsLayerScreen(

            )
        }


    }

}

