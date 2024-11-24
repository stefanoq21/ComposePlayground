package com.stefanoq21.composeplayground.ui.screen.graphicsLayer

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.ColorMatrix
import androidx.compose.ui.graphics.TransformOrigin
import androidx.compose.ui.graphics.asAndroidBitmap
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.layer.drawLayer
import androidx.compose.ui.graphics.rememberGraphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.content.FileProvider
import com.stefanoq21.composeplayground.R
import com.stefanoq21.composeplayground.ui.theme.ComposePlaygroundTheme
import kotlinx.coroutines.launch
import java.io.File
import java.io.FileOutputStream

/** https://developer.android.com/develop/ui/compose/graphics/draw/modifiers#graphics-modifiers    */


@Composable
fun GraphicsLayerScreen(

) {
    val graphicsLayer = rememberGraphicsLayer()
    val coroutineScope = rememberCoroutineScope()
    val context = LocalContext.current


    Column(
        modifier = Modifier
            .fillMaxWidth()
            .verticalScroll(rememberScrollState())
            .padding(16.dp)
    ) {

        var scaleX by remember {
            mutableFloatStateOf(1f)
        }
        TransformationSlider(
            label = "scaleX", value = scaleX, onValueChange = {
                scaleX = it
            }, valueRange = 0f..2f
        )

        var scaleY by remember {
            mutableFloatStateOf(1f)
        }
        TransformationSlider(
            label = "scaleY", value = scaleY, onValueChange = {
                scaleY = it
            }, valueRange = 0f..2f
        )

        var translateX by remember {
            mutableFloatStateOf(0f)
        }
        TransformationSlider(
            label = "translateX",
            value = translateX,
            onValueChange = {
                translateX = it
            },
        )

        var translateY by remember {
            mutableFloatStateOf(0f)
        }
        TransformationSlider(
            label = "translateY",
            value = translateY,
            onValueChange = {
                translateY = it
            },
        )

        var transformOrigin by remember {
            mutableFloatStateOf(0f)
        }
        TransformationSlider(
            label = "transformOrigin",
            value = transformOrigin,
            onValueChange = {
                transformOrigin = it
            },
        )


        var rotationX by remember {
            mutableFloatStateOf(0f)
        }
        TransformationSlider(
            label = "rotationX", value = rotationX, onValueChange = {
                rotationX = it
            }, valueRange = 0f..360f
        )

        var rotationY by remember {
            mutableFloatStateOf(0f)
        }
        TransformationSlider(
            label = "rotationY", value = rotationY, onValueChange = {
                rotationY = it
            }, valueRange = 0f..360f
        )

        var rotationZ by remember {
            mutableFloatStateOf(0f)
        }
        TransformationSlider(
            label = "rotationZ", value = rotationZ, onValueChange = {
                rotationZ = it
            }, valueRange = 0f..360f
        )
        var alpha by remember {
            mutableFloatStateOf(1f)
        }
        TransformationSlider(
            label = "alpha", value = alpha,
            onValueChange = {
                alpha = it
            },
        )

        var clip by remember {
            mutableStateOf(false)
        }
        Row(modifier = Modifier.fillMaxWidth()) {
            Button(onClick = {
                clip = !clip
            }) {
                Text("Clip")
            }

            Button(onClick = {
                coroutineScope.launch {
                    val bitmap = graphicsLayer
                        .toImageBitmap()
                        .asAndroidBitmap()
                    shareBitmap(bitmap, context)
                }
            }) {
                Text("Save")
            }
        }

        Box(modifier = Modifier
            .fillMaxWidth()
            .border(2.dp, Color.Black)
            .drawWithContent {
                graphicsLayer.record {
                    this@drawWithContent.drawContent()
                }
                drawLayer(graphicsLayer)
            }
            .clickable {
                coroutineScope.launch {
                    val bitmap = graphicsLayer
                        .toImageBitmap()
                        .asAndroidBitmap()
                    shareBitmap(bitmap, context)
                }
            }

        ) {
            Surface {
                Image(
                    painter = painterResource(id = R.drawable.test_background_1),
                    contentDescription = "",
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(300.dp)
                        .graphicsLayer {
                            this.scaleX = scaleX
                            this.scaleY = scaleY
                            this.translationX = (100 * translateX).dp.toPx()
                            this.translationY = (100 * translateY).dp.toPx()
                            this.transformOrigin = TransformOrigin(transformOrigin, transformOrigin)
                            this.rotationX = rotationX
                            this.rotationY = rotationY
                            this.rotationZ = rotationZ
                            this.alpha = alpha
                            this.clip = clip
                            this.ambientShadowColor = Color.Black
                            this.spotShadowColor = Color.Black
                            this.shape = CircleShape
                        },
                    contentScale = ContentScale.Fit
                )

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

        }


        Spacer(Modifier.height(16.dp))

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
            val pagerState = rememberPagerState(initialPage = 0, pageCount = { images.size })
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
            val pagerState = rememberPagerState(initialPage = 0, pageCount = { images.size })
            HorizontalPager(
                modifier = Modifier.colorFilter(ColorFilter.colorMatrix(ColorMatrix().apply {
                    setToSaturation(0f)
                })), state = pagerState
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


    }

}

@Composable
fun TransformationSlider(
    label: String,
    value: Float,
    onValueChange: (Float) -> Unit,
    valueRange: ClosedFloatingPointRange<Float> = 0f..1f
) {

    Column {
        Text(text = label)
        Slider(
            value = value, onValueChange = onValueChange, valueRange = valueRange
        )
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

fun shareBitmap(bitmap: Bitmap, context: Context) {
    val imageFile = File(context.cacheDir, "shared_image.jpg")
    val fos = FileOutputStream(imageFile)
    bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos)
    fos.close()

    val uri = FileProvider.getUriForFile(
        context, "${context.applicationContext.packageName}.fileprovider", imageFile
    )

    val shareIntent = Intent(Intent.ACTION_SEND)
    shareIntent.type = "image/jpeg"
    shareIntent.putExtra(
        Intent.EXTRA_STREAM, uri
    )
    shareIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)

    context.startActivity(
        Intent.createChooser(
            shareIntent, "Share Image"
        )
    )
}

@Preview
@Composable
fun GraphicsLayerScreenPreview() {
    ComposePlaygroundTheme {
        Surface {
            GraphicsLayerScreen()
        }
    }
}

