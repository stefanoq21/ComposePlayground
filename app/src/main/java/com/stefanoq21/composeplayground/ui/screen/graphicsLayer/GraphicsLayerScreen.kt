package com.stefanoq21.composeplayground.ui.screen.graphicsLayer

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
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
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.ColorMatrix
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asAndroidBitmap
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
        Box(modifier = Modifier
            .drawWithContent {
                graphicsLayer.record {
                    this@drawWithContent.drawContent()
                }
                drawLayer(graphicsLayer)
            }
            .clickable {
                coroutineScope.launch {
                    val bitmap = graphicsLayer.toImageBitmap().asAndroidBitmap()
                    shareBitmap(bitmap, context)
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
        context,
        "${context.applicationContext.packageName}.fileprovider",
        imageFile
    )

    val shareIntent = Intent(Intent.ACTION_SEND)
    shareIntent.type = "image/jpeg"
    shareIntent.putExtra(
        Intent.EXTRA_STREAM,
        uri
    )
    shareIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)

    context.startActivity(
        Intent.createChooser(
            shareIntent,
            "Share Image"
        )
    )
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

