package woowacourse.paint.view

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import woowacourse.paint.domain.BrushColor
import woowacourse.paint.domain.BrushWidth
import woowacourse.paint.domain.Line
import woowacourse.paint.domain.Lines
import woowacourse.paint.view.model.RichPaths
import woowacourse.paint.view.model.mapper.LineMapper
import woowacourse.paint.view.model.mapper.LinesMapper.toModel
import woowacourse.paint.view.model.pen.EllipsePen
import woowacourse.paint.view.model.pen.LinePen
import woowacourse.paint.view.model.pen.Pen

class PaintViewModel : ViewModel() {
    private var color: Int = BrushColor.paintColors[0]
    private var strokeWidth: Float = BrushWidth.range.start

    private val _lines: MutableLiveData<Lines> = MutableLiveData(Lines())
    val lines: LiveData<RichPaths>
        get() = Transformations.map(_lines) { it.toModel() }

    private val _pen: MutableLiveData<Pen> = MutableLiveData(
        LinePen { path, paint ->
            addLine(LineMapper.toLine(path, paint))
        }.apply {
            setColor(color)
            setStrokeWidth(strokeWidth)
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
        _pen.value = LinePen { path, paint ->
            addLine(LineMapper.toLine(path, paint))
        }.apply {
            setColor(color)
            setStrokeWidth(strokeWidth)
        }
    }

    fun changeToEllipsePen() {
        _pen.value = EllipsePen().apply {
            setColor(color)
            setStrokeWidth(strokeWidth)
        }
    }

    private fun addLine(line: Line) {
        _lines.value = _lines.value?.add(line)
    }
}
