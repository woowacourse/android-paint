package woowacourse.paint

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import woowacourse.paint.model.Tool
import woowacourse.paint.model.Tool.CIRCLE_PEN
import woowacourse.paint.model.Tool.ERASER
import woowacourse.paint.model.Tool.NORMAL_PEN
import woowacourse.paint.model.Tool.RECTANGLE_PEN
import woowacourse.paint.paintBoard.Brush
import woowacourse.paint.paintBoard.Line
import woowacourse.paint.paintBoard.tools.CirclePen
import woowacourse.paint.paintBoard.tools.Eraser
import woowacourse.paint.paintBoard.tools.NormalPen
import woowacourse.paint.paintBoard.tools.RectanglePen
import woowacourse.paint.paintBoard.tools.Tools

class MainViewModel : ViewModel() {

    private var color = R.color.black
    private var width = 0f
    private var tools: Tool = NORMAL_PEN

    private val _painting: MutableLiveData<List<Line>> = MutableLiveData(listOf())
    val painting: LiveData<List<Line>> get() = _painting

    private val _tool: MutableLiveData<Tools> = MutableLiveData(setNormalPen())
    val tool: LiveData<Tools> get() = _tool

    fun updateToolState(tool: Tool) {
        tools = tool
        renewTools()
    }

    fun updateWidth(width: Float) {
        this.width = width
    }

    fun updateColor(color: Int) {
        this.color = color
    }

    private fun setNormalPen(): NormalPen = NormalPen(
        ::renewTools,
        ::saveLine,
        Line(brush = Brush().apply {
            changeBrush(color)
            changeBrush(width)
        })
    )

    private fun setRectanglePen(): RectanglePen = RectanglePen(
        ::renewTools,
        ::saveLine,
        Line(brush = Brush().apply {
            changeBrush(color)
        })
    )

    private fun setCirclePen(): CirclePen = CirclePen(
        ::renewTools,
        ::saveLine,
        Line(brush = Brush().apply {
            changeBrush(color)
        })
    )

    private fun setEraser(): Eraser = Eraser(::removeLines, (painting.value ?: emptyList()).toMutableList())

    private fun saveLine(line: Line) {
        _painting.value = painting.value?.plus(line)
    }

    private fun removeLines(line: Line) {
        _painting.value = painting.value?.minus(line)
    }

    private fun renewTools() {
        _tool.value = when (tools) {
            NORMAL_PEN -> setNormalPen()
            CIRCLE_PEN -> setCirclePen()
            RECTANGLE_PEN -> setRectanglePen()
            ERASER -> setEraser()
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                MainViewModel()
            }
        }
    }
}