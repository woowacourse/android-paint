package woowacourse.paint

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import woowacourse.paint.model.Tool
import woowacourse.paint.model.Tool.NORMAL_PEN
import woowacourse.paint.paintBoard.Brush
import woowacourse.paint.paintBoard.Line
import woowacourse.paint.paintBoard.tools.CirclePen
import woowacourse.paint.paintBoard.tools.Eraser
import woowacourse.paint.paintBoard.tools.NormalPen
import woowacourse.paint.paintBoard.tools.RectanglePen

class MainViewModel : ViewModel() {
    private var color = R.color.black
    private var width = 0f
    private val _painting: MutableLiveData<List<Line>> = MutableLiveData(listOf(Line()))
    val painting: LiveData<List<Line>> get() = _painting

    private val _tool: MutableLiveData<Tool> = MutableLiveData(NORMAL_PEN)
    val tool: LiveData<Tool> get() = _tool

    fun updateToolState(tool: Tool) {
        _tool.value = tool
    }

    fun updateWidth(width: Float) {
        this.width = width
    }

    fun updateColor(color: Int) {
        this.color = color
    }

    fun setNormalPen(): NormalPen = NormalPen(
        ::saveLine,
        Line(brush = Brush().apply {
            changeBrush(color)
            changeBrush(width)
        })
    )

    fun setRectanglePen(): RectanglePen = RectanglePen(
        ::saveLine,
        Line(brush = Brush().apply {
            changeBrush(color)
        })
    )

    fun setCirclePen(): CirclePen = CirclePen(
        ::saveLine,
        Line(brush = Brush().apply {
            changeBrush(color)
        })
    )

    fun setEraser(): Eraser = Eraser(::saveLine, Line())

    private fun saveLine(line: Line) {
        _painting.value = _painting.value?.plus(line)
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                MainViewModel()
            }
        }
    }
}