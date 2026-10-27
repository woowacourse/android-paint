package woowacourse.paint.ui

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.awaitEachGesture
import androidx.compose.foundation.gestures.awaitFirstDown
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import woowacourse.paint.model.BrushColor
import woowacourse.paint.model.DrawingPoint
import woowacourse.paint.model.DrawingState
import woowacourse.paint.model.Stroke
import androidx.compose.ui.graphics.drawscope.Stroke as DrawStroke

@Composable
@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Suppress("ktlint:standard:function-naming")
fun PaintScreen(
    state: DrawingState,
    onColorSelected: (BrushColor) -> Unit,
    onBrushWidthChanged: (Float) -> Unit,
    onStrokeStarted: (DrawingPoint) -> Unit,
    onStrokePointsAdded: (List<DrawingPoint>) -> Unit,
    onStrokeFinished: () -> Unit,
) {
    val latestOnStrokeStarted = rememberUpdatedState(onStrokeStarted)
    val latestOnStrokePointsAdded = rememberUpdatedState(onStrokePointsAdded)
    val latestOnStrokeFinished = rememberUpdatedState(onStrokeFinished)

    Scaffold(
        topBar = { TopAppBar(title = { Text("그림판") }) },
    ) { contentPadding ->
        Column(
            modifier =
                Modifier
                    .fillMaxSize()
                    .padding(contentPadding)
                    .padding(horizontal = 16.dp),
        ) {
            Text(text = "브러시 색상", style = MaterialTheme.typography.titleMedium)
            Row(
                modifier =
                    Modifier
                        .fillMaxWidth()
                        .padding(top = 8.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                BrushColor.entries.forEach { color ->
                    BrushColorOption(
                        color = color,
                        selected = state.brush.color == color,
                        onClick = { onColorSelected(color) },
                    )
                }
            }

            Text(
                modifier = Modifier.padding(top = 12.dp),
                text = "굵기: ${state.brush.widthDp.toInt()}dp",
                style = MaterialTheme.typography.titleMedium,
            )
            Slider(
                value = state.brush.widthDp,
                onValueChange = onBrushWidthChanged,
                valueRange = MIN_BRUSH_WIDTH_DP..MAX_BRUSH_WIDTH_DP,
                steps = BRUSH_WIDTH_STEPS,
            )

            Canvas(
                modifier =
                    Modifier
                        .fillMaxWidth()
                        .weight(1f)
                        .padding(vertical = 8.dp)
                        .clip(MaterialTheme.shapes.medium)
                        .background(MaterialTheme.colorScheme.surfaceVariant)
                        .border(1.dp, MaterialTheme.colorScheme.outline, MaterialTheme.shapes.medium)
                        .pointerInput(Unit) {
                            awaitEachGesture {
                                val down = awaitFirstDown()
                                latestOnStrokeStarted.value(
                                    down.position.toDrawingPoint(size.width, size.height),
                                )
                                down.consume()

                                try {
                                    var pressed = true
                                    while (pressed) {
                                        val change =
                                            awaitPointerEvent().changes
                                                .firstOrNull { it.id == down.id } ?: break
                                        val points =
                                            change.historical.map { historical ->
                                                historical.position.toDrawingPoint(size.width, size.height)
                                            } + change.position.toDrawingPoint(size.width, size.height)
                                        latestOnStrokePointsAdded.value(points)
                                        change.consume()
                                        pressed = change.pressed
                                    }
                                } finally {
                                    latestOnStrokeFinished.value()
                                }
                            }
                        },
            ) {
                (state.strokes + listOfNotNull(state.activeStroke)).forEach(::drawStroke)
            }
        }
    }
}

@Composable
@Suppress("ktlint:standard:function-naming")
private fun BrushColorOption(
    color: BrushColor,
    selected: Boolean,
    onClick: () -> Unit,
) {
    Column(
        modifier =
            Modifier.selectable(
                selected = selected,
                role = Role.RadioButton,
                onClick = onClick,
            ),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(4.dp),
    ) {
        Box(
            modifier =
                Modifier
                    .size(40.dp)
                    .clip(CircleShape)
                    .background(Color(color.argb))
                    .border(
                        width = if (selected) 3.dp else 1.dp,
                        color = if (selected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.outline,
                        shape = CircleShape,
                    ),
            contentAlignment = Alignment.Center,
        ) {
            if (selected) {
                Text(
                    text = "✓",
                    color = Color.Black,
                    fontSize = 20.sp,
                    textAlign = TextAlign.Center,
                )
            }
        }
        Text(text = color.label, style = MaterialTheme.typography.labelMedium)
    }
}

private fun Offset.toDrawingPoint(
    width: Int,
    height: Int,
): DrawingPoint = DrawingPoint.fromCanvasPosition(x, y, width.toFloat(), height.toFloat())

private fun DrawScope.drawStroke(stroke: Stroke) {
    val points =
        stroke.points.map { point ->
            Offset(point.x * size.width, point.y * size.height)
        }
    if (points.isEmpty()) return

    val color = Color(stroke.color.argb)
    val strokeWidthPx = stroke.widthDp.dp.toPx()
    if (points.size == 1) {
        drawCircle(color = color, radius = strokeWidthPx / 2, center = points.single())
        return
    }

    val path =
        Path().apply {
            moveTo(points.first().x, points.first().y)
            if (points.size == 2) {
                lineTo(points.last().x, points.last().y)
            } else {
                for (index in 1 until points.lastIndex) {
                    val current = points[index]
                    val next = points[index + 1]
                    val midpoint =
                        Offset(
                            x = (current.x + next.x) / 2,
                            y = (current.y + next.y) / 2,
                        )
                    quadraticBezierTo(current.x, current.y, midpoint.x, midpoint.y)
                }
                lineTo(points.last().x, points.last().y)
            }
        }
    drawPath(
        path = path,
        color = color,
        style =
            DrawStroke(
                width = strokeWidthPx,
                cap = StrokeCap.Round,
                join = StrokeJoin.Round,
            ),
    )
}

private const val MIN_BRUSH_WIDTH_DP = 2f
private const val MAX_BRUSH_WIDTH_DP = 32f
private const val BRUSH_WIDTH_STEPS = 14
