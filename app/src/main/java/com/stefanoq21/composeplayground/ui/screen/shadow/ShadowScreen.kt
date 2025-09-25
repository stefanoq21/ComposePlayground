package com.stefanoq21.composeplayground.ui.screen.shadow

import androidx.compose.animation.animateColor
import androidx.compose.animation.core.Ease
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.material3.MaterialShapes
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.toShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.dropShadow
import androidx.compose.ui.draw.innerShadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.shadow.Shadow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.stefanoq21.composeplayground.ui.theme.ComposePlaygroundTheme

//https://developer.android.com/develop/ui/compose/graphics/draw/shadows
@OptIn(ExperimentalMaterial3ExpressiveApi::class)
@Composable
fun ShadowScreen(

) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .verticalScroll(rememberScrollState())
            .padding(16.dp)
    ) {
        Box(
            modifier = Modifier
                .padding(16.dp)
                .dropShadow(
                    shape = RoundedCornerShape(20.dp), shadow = Shadow(
                        radius = 6.dp,
                        spread = 2.dp,
                        color = Color.Black.copy(alpha = 0.3f),
                        offset = DpOffset(2.dp, 2.dp)
                    )
                )
                .background(
                    color = Color.White, shape = RoundedCornerShape(20.dp)
                )
        ) {
            Text(
                modifier = Modifier
                    .align(Alignment.Center)
                    .padding(24.dp), text = "dropShadow"
            )


        }
        Box(
            modifier = Modifier
                .padding(16.dp)
                .dropShadow(
                    shape = MaterialShapes.Cookie12Sided.toShape(), shadow = Shadow(
                        radius = 6.dp,
                        spread = 2.dp,
                        color = Color.Black.copy(alpha = 0.3f),
                        offset = DpOffset(2.dp, 2.dp)
                    )
                )
                .background(
                    color = Color.White,
                    shape = MaterialShapes.Cookie12Sided.toShape(),
                )
        ) {
            Text(
                modifier = Modifier
                    .align(Alignment.Center)
                    .padding(vertical = 40.dp, horizontal = 24.dp), text = "dropShadow2"
            )
        }

        Box(
            modifier = Modifier
                .padding(16.dp)
                .background(
                    Color.White, shape = RoundedCornerShape(20.dp)
                )
                .innerShadow(
                    shape = RoundedCornerShape(20.dp), shadow = Shadow(
                        radius = 6.dp,
                        spread = 2.dp,
                        color = Color.Black.copy(alpha = 0.3f),
                        offset = DpOffset(2.dp, 2.dp)
                    )
                )
        ) {
            Text(
                modifier = Modifier
                    .align(Alignment.Center)
                    .padding(24.dp), text = "innerShadow"
            )
        }



        Box(
            modifier = Modifier
                .padding(16.dp)
                .dropShadow(
                    shape = RoundedCornerShape(20.dp), shadow = Shadow(
                        radius = 6.dp,
                        spread = 2.dp,
                        color = Color.Black.copy(alpha = 0.3f),
                        offset = DpOffset(2.dp, 2.dp)
                    )
                )
                .background(
                    Color.White, shape = RoundedCornerShape(20.dp)
                )
                .innerShadow(
                    shape = RoundedCornerShape(20.dp), shadow = Shadow(
                        radius = 6.dp,
                        spread = 2.dp,
                        color = Color.Black.copy(alpha = 0.3f),
                        offset = DpOffset(2.dp, 2.dp)
                    )
                )
        ) {
            Text(
                modifier = Modifier
                    .align(Alignment.Center)
                    .padding(24.dp), text = "dropInnerShadow"
            )
        }


        AnimatedColoredShadows()

        Spacer(modifier = Modifier.height(50.dp))


        val colors = listOf(Color.Green, Color.Blue, Color.Yellow, Color.Green)
        Box(
            modifier = Modifier
                .width(240.dp)
                .height(200.dp)
                .dropShadow(
                    shape = RoundedCornerShape(70.dp), shadow = Shadow(
                        radius = 10.dp, spread = 12.dp, brush = Brush.sweepGradient(
                            colors
                        ), offset = DpOffset(x = 0.dp, y = 0.dp), alpha = 0.5f
                    )
                )
                .clip(RoundedCornerShape(70.dp))
                .background(Color(0xEDFFFFFF)),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "Gradient", color = Color.Black, style = MaterialTheme.typography.bodyLarge
            )
        }

        Spacer(modifier = Modifier.height(80.dp))

        Box(
            Modifier
                .fillMaxWidth()
                .height(300.dp)
        ) {
            NeumorphicRaisedButton()
        }

        Spacer(modifier = Modifier.height(20.dp))

        Box(
            Modifier
                .fillMaxWidth()
                .height(300.dp)
        ) {
            NeoBrutalShadows()
        }

 Spacer(modifier = Modifier.height(10.dp))

        Box(
            Modifier
                .fillMaxWidth()
                .height(300.dp)
        ) {
            RealisticShadows()
        }


    }

}

@Composable
fun AnimatedColoredShadows() {

    Box(Modifier.fillMaxSize()) {
        val interactionSource = remember { MutableInteractionSource() }
        val isPressed by interactionSource.collectIsPressedAsState()

        // Create transition with pressed state
        val transition = updateTransition(
            targetState = isPressed, label = "button_press_transition"
        )

        fun <T> buttonPressAnimation() = tween<T>(
            durationMillis = 400, easing = Ease
        )

        // Animate all properties using the transition
        val shadowAlpha by transition.animateFloat(
            label = "shadow_alpha", transitionSpec = { buttonPressAnimation() }) { pressed ->
            if (pressed) 0f else 1f
        }
        // ...

        val blueDropShadow by transition.animateColor(
            label = "shadow_color", transitionSpec = { buttonPressAnimation() }) { pressed ->
            if (pressed) Color.Transparent else Color.Green.copy(alpha = (0.5f))
        }

        // ...

        Box(
            Modifier
                .clickable(
                    interactionSource, indication = null
                ) {
                    // ** ...... **//
                }
                .width(300.dp)
                .height(200.dp)
                .align(Alignment.Center)
                .dropShadow(
                    shape = RoundedCornerShape(70.dp), shadow = Shadow(
                        radius = 10.dp,
                        spread = 0.dp,
                        color = blueDropShadow,
                        offset = DpOffset(x = 0.dp, -(2).dp),
                        alpha = shadowAlpha
                    )
                )
                .dropShadow(
                    shape = RoundedCornerShape(70.dp), shadow = Shadow(
                        radius = 10.dp,
                        spread = 0.dp,
                        color = Color.Green.copy(alpha = (0.5f)),
                        offset = DpOffset(x = 2.dp, 6.dp),
                        alpha = shadowAlpha
                    )
                )
                // note that the background needs to be defined before defining the inner shadow
                .background(
                    color = Color(0xFFFFFFFF), shape = RoundedCornerShape(70.dp)
                )
                .innerShadow(
                    shape = RoundedCornerShape(70.dp), shadow = Shadow(
                        radius = 8.dp,
                        spread = 4.dp,
                        color = Color.Green.copy(alpha = (0.5f)),
                        offset = DpOffset(x = 4.dp, 0.dp)
                    )
                )
                .innerShadow(
                    shape = RoundedCornerShape(70.dp), shadow = Shadow(
                        radius = 20.dp,
                        spread = 4.dp,
                        color = Color.Green.copy(alpha = (0.5f)),
                        offset = DpOffset(x = 4.dp, 0.dp),
                    )
                )

        ) {
            Text(
                modifier = Modifier
                    .align(Alignment.Center)
                    .padding(24.dp),
                text = "Animated Shadows",
            )
        }
    }

}

@Composable
fun NeumorphicRaisedButton(
    shape: RoundedCornerShape = RoundedCornerShape(30.dp)
) {
    val bgColor = Color(0xFFe0e0e0)
    val lightShadow = Color(0xFFFFFFFF)
    val darkShadow = Color(0xFFb1b1b1)
    val upperOffset = -10.dp
    val lowerOffset = 10.dp
    val radius = 15.dp
    val spread = 0.dp
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(bgColor)
            .wrapContentSize(Alignment.Center)
            .size(240.dp)
            .dropShadow(
                shape,
                shadow = Shadow(
                    radius = radius,
                    color = lightShadow,
                    spread = spread,
                    offset = DpOffset(upperOffset, upperOffset)
                ),
            )
            .dropShadow(
                shape,
                shadow = Shadow(
                    radius = radius,
                    color = darkShadow,
                    spread = spread,
                    offset = DpOffset(lowerOffset, lowerOffset)
                ),

                )
            .background(bgColor, shape)
    )
}

@Composable
fun NeoBrutalShadows() {
    val dropShadowColor = Color(0xFF007AFF)
    val borderColor = Color(0xFFFF2D55)
    Box(Modifier.fillMaxSize()) {
        Box(
            Modifier
                .width(300.dp)
                .height(200.dp)
                .align(Alignment.Center)
                .dropShadow(
                    shape = RoundedCornerShape(0.dp),
                    shadow = Shadow(
                        radius = 0.dp,
                        spread = 0.dp,
                        color = dropShadowColor,
                        offset = DpOffset(x = 8.dp, 8.dp)
                    )
                )
                .border(
                    8.dp, borderColor
                )
                .background(
                    color = Color.White,
                    shape = RoundedCornerShape(0.dp)
                )
        ) {
            Text(
                "Neobrutal Shadows",
                modifier = Modifier.align(Alignment.Center),
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}


@Composable
fun RealisticShadows() {
    Box(Modifier.fillMaxSize()) {
        val dropShadowColor1 = Color(0xB3000000)
        val dropShadowColor2 = Color(0x66000000)

        val innerShadowColor1 = Color(0xCC000000)
        val innerShadowColor2 = Color(0xFF050505)
        val innerShadowColor3 = Color(0x40FFFFFF)
        val innerShadowColor4 = Color(0x1A050505)
        Box(
            Modifier
                .width(300.dp)
                .height(200.dp)
                .align(Alignment.Center)
                .dropShadow(
                    shape = RoundedCornerShape(100.dp),
                    shadow = Shadow(
                        radius = 40.dp,
                        spread = 0.dp,
                        color = dropShadowColor1,
                        offset = DpOffset(x = 2.dp, 8.dp)
                    )
                )
                .dropShadow(
                    shape = RoundedCornerShape(100.dp),
                    shadow = Shadow(
                        radius = 4.dp,
                        spread = 0.dp,
                        color = dropShadowColor2,
                        offset = DpOffset(x = 0.dp, 4.dp)
                    )
                )
                // note that the background needs to be defined before defining the inner shadow
                .background(
                    color = Color.Black,
                    shape = RoundedCornerShape(100.dp)
                )
// //
                .innerShadow(
                    shape = RoundedCornerShape(100.dp),
                    shadow = Shadow(
                        radius = 12.dp,
                        spread = 3.dp,
                        color = innerShadowColor1,
                        offset = DpOffset(x = 6.dp, 6.dp)
                    )
                )
                .innerShadow(
                    shape = RoundedCornerShape(100.dp),
                    shadow = Shadow(
                        radius = 4.dp,
                        spread = 1.dp,
                        color = Color.White,
                        offset = DpOffset(x = 5.dp, 5.dp)
                    )
                )
                .innerShadow(
                    shape = RoundedCornerShape(100.dp),
                    shadow = Shadow(
                        radius = 12.dp,
                        spread = 5.dp,
                        color = innerShadowColor2,
                        offset = DpOffset(x = (-3).dp, (-12).dp)
                    )
                )
                .innerShadow(
                    shape = RoundedCornerShape(100.dp),
                    shadow = Shadow(
                        radius = 3.dp,
                        spread = 10.dp,
                        color = innerShadowColor3,
                        offset = DpOffset(x = 0.dp, 0.dp)
                    )
                )
                .innerShadow(
                    shape = RoundedCornerShape(100.dp),
                    shadow = Shadow(
                        radius = 3.dp,
                        spread = 9.dp,
                        color = innerShadowColor4,
                        offset = DpOffset(x = 1.dp, 1.dp)
                    )
                )

        ) {
            Text(
                "Realistic Shadows",
                modifier = Modifier.align(Alignment.Center),
                fontSize = 24.sp,
                color = Color.White
            )
        }
    }
}

@Preview(heightDp = 2000 )
@Composable
fun ShadowScreenPreview() {
    ComposePlaygroundTheme {
        Surface {
            ShadowScreen()
        }
    }
}

