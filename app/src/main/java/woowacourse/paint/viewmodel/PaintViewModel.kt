package woowacourse.paint.viewmodel

import androidx.annotation.ColorRes
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import woowacourse.paint.PaintBoard.Companion.DEFAULT_PAINT_COLOR_RES
import woowacourse.paint.PaintBoard.Companion.DEFAULT_STROKE_WIDTH
import woowacourse.paint.action.PaintActionHandler
import woowacourse.paint.action.PaintBoardAction
import woowacourse.paint.model.DrawingMode
import woowacourse.paint.util.Event

class PaintViewModel : ViewModel(), PaintActionHandler {
    private val _drawingMode: MutableLiveData<DrawingMode> = MutableLiveData(DrawingMode.PEN)
    val drawingMode: LiveData<DrawingMode> get() = _drawingMode

    private val _colorRes: MutableLiveData<Int> = MutableLiveData(DEFAULT_PAINT_COLOR_RES)
    val colorRes: LiveData<Int> get() = _colorRes

    private val _strokeWidth: MutableLiveData<Float> = MutableLiveData(DEFAULT_STROKE_WIDTH)
    val strokeWidth: LiveData<Float> get() = _strokeWidth

    private val _boardAction: MutableLiveData<Event<PaintBoardAction>> = MutableLiveData()
    val boardAction: LiveData<Event<PaintBoardAction>> get() = _boardAction

    override fun changeDrawingMode(drawingMode: DrawingMode) {
        _drawingMode.value = drawingMode
    }

    override fun clearDrawings() {
        _boardAction.value = Event(PaintBoardAction.ClearDrawings)
    }

    override fun changeColorRes(
        @ColorRes colorRes: Int,
    ) {
        _colorRes.value = colorRes
    }

    fun changeStrokeWidth(strokeWidth: Float) {
        _strokeWidth.value = strokeWidth
    }
}
