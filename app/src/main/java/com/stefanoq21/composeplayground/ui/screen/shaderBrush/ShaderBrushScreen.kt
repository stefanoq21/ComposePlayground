package com.stefanoq21.composeplayground.ui.screen.shaderBrush

import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.LinearGradientShader
import androidx.compose.ui.graphics.Shader
import androidx.compose.ui.graphics.ShaderBrush
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.stefanoq21.composeplayground.ui.theme.ComposePlaygroundTheme

/** https://medium.com/androiddevelopers/animating-brush-text-coloring-in-compose-%EF%B8%8F-26ae99d9b402   */

@Composable
fun ShaderBrushScreen(

) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .verticalScroll(rememberScrollState())
            .padding(16.dp)
    ) {

        val baseColor = Color.Black
        val shadeColor = baseColor.copy(alpha = 0.3f)
        val infiniteTransition = rememberInfiniteTransition()
        val offset by infiniteTransition.animateFloat(
            initialValue = 0f,
            targetValue = 1f,
            animationSpec = infiniteRepeatable(
                animation = tween(2000),
                repeatMode = RepeatMode.Restart
            )
        )

        val brush = remember(offset) {
            object : ShaderBrush() {
                override fun createShader(size: Size): Shader {
                    val widthOffset = size.width * 2 * offset
                    return LinearGradientShader(
                        from = Offset(-size.width + widthOffset, 0f),
                        to = Offset(widthOffset, 0f),
                        colors = listOf(
                            baseColor,
                            shadeColor,
                            shadeColor,
                            baseColor
                        ),
                    )
                }
            }
        }
        Text(
            text = "Loading...",
            style = MaterialTheme.typography.displayLarge.copy(
                brush = brush,
            ),

            )


    }
}


@Preview
@Composable
fun ShaderBrushScreenPreview() {
    ComposePlaygroundTheme {
        Surface {
            ShaderBrushScreen(

            )
        }


    }

}

