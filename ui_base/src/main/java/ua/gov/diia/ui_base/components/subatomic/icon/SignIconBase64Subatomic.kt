package ua.gov.diia.ui_base.components.subatomic.icon

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Color
import android.util.Base64
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.graphics.set
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlin.math.abs


@Composable
fun SingIconBase64Subatomic(
    modifier: Modifier,
    base64Image: String,
    contentDescription: String = "icon",
    contentScale: ContentScale = ContentScale.Fit,
    signImage: ImageBitmap?
) {
    val image = remember {
        mutableStateOf(
            signImage ?: Bitmap.createBitmap(100, 100, Bitmap.Config.ARGB_8888).apply {
                eraseColor(Color.TRANSPARENT)
            }.asImageBitmap()
        )
    }
    LaunchedEffect(key1 = Unit) {
        coroutineScope {
            if (signImage == null){
                launch(Dispatchers.IO) {
                    val byteArray = Base64.decode(base64Image, Base64.DEFAULT)
                    BitmapFactory.decodeByteArray(byteArray, 0, byteArray.size)
                        ?.replaceWhiteWithTransparent()?.let {
                            image.value = it.asImageBitmap()
                        }
                }
            }

        }
    }
    Image(
        modifier = modifier,
        bitmap = image.value,
        contentScale = contentScale,
        contentDescription = contentDescription
    )
}

@Preview
@Composable
fun SingIconBase64SubatomicPreview_ValidBase64() {
    val base64String =
        "iVBORw0KGgoAAAANSUhEUgAAADMAAAAzCAYAAAA6oTAqAAAAEXRFWHRTb2Z0d2FyZQBwbmdjcnVzaEB1SfMAAABQSURBVGje7dSxCQBACARB+2/ab8BEeQNhFi6WSYzYLYudDQYGBgYGBgYGBgYGBgYGBgZmcvDqYGBgmhivGQYGBgYGBgYGBgYGBgYGBgbmQw+P/eMrC5UTVAAAAABJRU5ErkJggg=="

    IconBase64Subatomic(
        modifier = Modifier.size(16.dp),
        base64Image = base64String
    )
}

@Preview
@Composable
fun SingIconBase64Subatomic_InvalidBase64() {
    val base64String =
        "iVnjkndfjjsd"

    IconBase64Subatomic(
        modifier = Modifier.size(16.dp),
        base64Image = base64String
    )
}

fun Bitmap.replaceWhiteWithTransparent(): Bitmap {
    val bitmap = copy(Bitmap.Config.ARGB_8888, true)
    val width = bitmap.width
    val height = bitmap.height
    var leftMostPixelIndex = Integer.MAX_VALUE

    fun pixelIsBlack(color: Int): Boolean {
        val blackThreshold = 128

        val br = Color.red(Color.BLACK)
        val bg = Color.green(Color.BLACK)
        val bb = Color.blue(Color.BLACK)

        val r = Color.red(color)
        val g = Color.green(color)
        val b = Color.blue(color)


        if ((abs(br - r) < blackThreshold) && (abs(bg - g) < blackThreshold) && (abs(bb - b) < blackThreshold)) {
            return true
        }
        return false
    }

    (0 until width).forEach { x ->
        (0 until height).forEach { y ->
            val currentPixel = bitmap.getPixel(x, y)
            if (!pixelIsBlack(currentPixel)) {
                bitmap[x, y] = Color.TRANSPARENT
            } else {
                if (leftMostPixelIndex > x) {
                    leftMostPixelIndex = x
                }
            }
        }
    }
    return Bitmap.createBitmap(
        bitmap,
        leftMostPixelIndex,
        0,
        if (width - leftMostPixelIndex <= 0) 1 else width - leftMostPixelIndex,
        height
    ).apply {
        setHasAlpha(true)
    }
}