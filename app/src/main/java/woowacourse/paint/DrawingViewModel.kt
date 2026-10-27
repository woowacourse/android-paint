package woowacourse.paint

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import woowacourse.paint.model.BrushColor
import woowacourse.paint.model.DrawingPoint
import woowacourse.paint.model.DrawingState
import woowacourse.paint.model.Stroke

class DrawingViewModel : ViewModel() {
    private val mutableState = MutableStateFlow(DrawingState())
    val state = mutableState.asStateFlow()

    fun selectColor(color: BrushColor) {
        mutableState.update { current -> current.copy(brush = current.brush.copy(color = color)) }
    }

    fun changeBrushWidth(widthDp: Float) {
        mutableState.update { current -> current.copy(brush = current.brush.copy(widthDp = widthDp)) }
    }

    fun startStroke(point: DrawingPoint) {
        mutableState.update { current ->
            current.copy(
                activeStroke =
                    Stroke(
                        points = listOf(point),
                        color = current.brush.color,
                        widthDp = current.brush.widthDp,
                    ),
            )
        }
    }

    fun addStrokePoints(points: List<DrawingPoint>) {
        if (points.isEmpty()) return

        mutableState.update { current ->
            val activeStroke = current.activeStroke ?: return@update current
            current.copy(activeStroke = activeStroke.copy(points = activeStroke.points + points))
        }
    }

    fun finishStroke() {
        mutableState.update { current ->
            val activeStroke = current.activeStroke ?: return@update current
            current.copy(
                strokes = current.strokes + activeStroke,
                activeStroke = null,
            )
        }
    }
}
