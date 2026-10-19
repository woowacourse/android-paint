package woowacourse.paint

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.awaitEachGesture
import androidx.compose.foundation.gestures.awaitFirstDown
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Slider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            var strokeWidth: Float by rememberSaveable { mutableFloatStateOf(1f) }
            var strokeColor: PicassoColor by rememberSaveable { mutableStateOf(PicassoColor.RED) }
            Column {
                PaintPanel(
                    strokeWidth = strokeWidth,
                    onStrokeWidthChange = { strokeWidth = it },
                    onStrokeColorChange = { strokeColor = it },
                    modifier = Modifier.fillMaxWidth(),
                )
                PaintCanvas(
                    strokeColor = strokeColor,
                    strokeWidth = strokeWidth,
                    modifier = Modifier.fillMaxSize()
                )
            }
        }
    }
}

@Composable
fun PaintCanvas(
    strokeColor: PicassoColor,
    strokeWidth: Float,
    modifier: Modifier = Modifier
) {
    var picasso: Picasso by rememberSaveable { mutableStateOf(Picasso(emptyList())) }
    var picassoPath: PicassoPath by rememberSaveable {
        mutableStateOf(
            PicassoPath(
                strokeColor,
                strokeWidth,
                emptyList()
            )
        )
    }
    Canvas(
        modifier =
            modifier.pointerInput(strokeColor, strokeWidth) {
                awaitEachGesture {
                    val down = awaitFirstDown()
                    picassoPath = PicassoPath(
                        strokeColor,
                        strokeWidth,
                        listOf(
                            PicassoOffset(
                                down.position.x / size.width,
                                down.position.y / size.height
                            )
                        )
                    )
                    do {
                        val event = awaitPointerEvent()
                        val change = event.changes.first()
                        picassoPath = picassoPath.add(
                            PicassoOffset(
                                change.position.x / size.width,
                                change.position.y / size.height
                            )
                        )
                        change.consume()
                    } while (event.changes.any { it.pressed })
                    picasso = picasso.add(picassoPath)
                    picassoPath = PicassoPath(strokeColor, strokeWidth, emptyList())
                }
            },
    ) {
        (picasso.plusElement(picassoPath)).forEach { stroke ->
            val points = stroke.map { Offset(it.x * size.width, it.y * size.height) }
            if (points.isEmpty()) return@forEach
            drawPath(
                smoothPath(points),
                stroke.color.toColor(),
                style = Stroke(width = stroke.width.dp.toPx()),
            )
        }
    }
}

@Composable
fun PaintPanel(
    strokeWidth: Float,
    onStrokeWidthChange: (Float) -> Unit,
    onStrokeColorChange: (PicassoColor) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        Row {
            PicassoColor.entries.forEach {
                Box(
                    modifier = Modifier
                        .size(40.dp)
                        .background(it.toColor())
                        .border(Dp.Hairline, Color.Black)
                        .clickable(onClick = { onStrokeColorChange(it) })
                )
            }
        }
        Slider(
            value = strokeWidth,
            onValueChange = onStrokeWidthChange,
            valueRange = 1f..50f,
        )
    }
}

fun smoothPath(points: List<Offset>): Path {
    val path = Path()
    if (points.isEmpty()) return path

    path.moveTo(points[0].x, points[0].y)
    for (i in 1 until points.size) {
        val prev = points[i - 1]
        val curr = points[i]
        path.quadraticTo(prev.x, prev.y, (prev.x + curr.x) / 2f, (prev.y + curr.y) / 2f)
    }
    return path
}
