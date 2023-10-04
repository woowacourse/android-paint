package woowacourse.paint.view

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import woowacourse.paint.domain.BrushColor
import woowacourse.paint.domain.BrushWidth
import woowacourse.paint.domain.Lines
import woowacourse.paint.view.model.Inks
import woowacourse.paint.view.model.mapper.LineMapper.toDomain
import woowacourse.paint.view.model.mapper.LinesMapper.toModel
import woowacourse.paint.view.model.pen.EllipsePen
import woowacourse.paint.view.model.pen.Ink
import woowacourse.paint.view.model.pen.LinePen
import woowacourse.paint.view.model.pen.Pen
import woowacourse.paint.view.model.pen.RectPen

class PaintViewModel : ViewModel() {
    private var color: Int = BrushColor.paintColors[0]
    private var strokeWidth: Float = BrushWidth.range.start

    private val _lines: MutableLiveData<Lines> = MutableLiveData(Lines())
    val lines: LiveData<Inks>
        get() = Transformations.map(_lines) { it.toModel() }

    private val _pen: MutableLiveData<Pen> = MutableLiveData(
        EllipsePen(onAddInk = ::addPen).apply {
            setColor(color)
        }
    )
    val pen: LiveData<Pen>
        get() = _pen

    fun updateColor(color: Int) {
        _pen.value.also {
            it?.setColor(color)
        }
    }

    fun updateStrokeWidth(strokeWidth: Float) {
        _pen.value.also {
            it?.setStrokeWidth(strokeWidth)
        }
    }

    fun changeToLinePen() {
        _pen.value = LinePen(onAddInk = ::addPen).apply {
            setColor(color)
            setStrokeWidth(strokeWidth)
        }
    }

    fun changeToRectPen() {
        _pen.value = RectPen(onAddInk = ::addPen).apply {
            setColor(color)
        }
    }

    fun changeToEllipsePen() {
        _pen.value = EllipsePen(onAddInk = ::addPen).apply {
            setColor(color)
        }
    }

    private fun addPen(ink: Ink) {
        _lines.value = _lines.value?.add(ink.toDomain())
    }
}
