package woowacourse.paint

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import woowacourse.paint.model.Tool
import woowacourse.paint.model.Tool.CIRCLE_PEN
import woowacourse.paint.model.Tool.LINE_ERASER
import woowacourse.paint.model.Tool.NORMAL_PEN
import woowacourse.paint.model.Tool.PATH_ERASER
import woowacourse.paint.model.Tool.RECTANGLE_PEN
import woowacourse.paint.paintBoard.Line
import woowacourse.paint.paintBoard.tools.CirclePen
import woowacourse.paint.paintBoard.tools.LineEraser
import woowacourse.paint.paintBoard.tools.NormalPen
import woowacourse.paint.paintBoard.tools.Painter
import woowacourse.paint.paintBoard.tools.PathEraser
import woowacourse.paint.paintBoard.tools.RectanglePen

class MainViewModel : ViewModel() {

    private var color = R.color.black
    private var width = DEFAULT_PEN_SIZE
    private var tool: Tool = NORMAL_PEN
    private var paintingBackup: MutableList<List<Line>> = mutableListOf()

    private val _painting: MutableLiveData<List<Line>> = MutableLiveData(listOf())
    val painting: LiveData<List<Line>> get() = _painting

    private val _painter: MutableLiveData<Painter> = MutableLiveData(setNormalPen())
    val painter: LiveData<Painter> get() = _painter

    fun updateToolState(tool: Tool) {
        this.tool = tool
        renewTools()
    }

    fun updateWidth(width: Float) {
        this.width = width
        renewTools()
    }

    fun updateColor(color: Int) {
        this.color = color
        renewTools()
    }

    fun resetPaintings() {
        if (painting.value.isNullOrEmpty()) return

        paintingBackup.clear()
        paintingBackup.add(painting.value ?: emptyList())
        _painting.value = emptyList()
    }

    fun redoPaintings() {
        if (paintingBackup.isEmpty()) return

        _painting.value = (painting.value ?: emptyList()) + paintingBackup.last()
        paintingBackup.removeLast()
    }

    fun undoPaintings() {
        if (painting.value.isNullOrEmpty() or (paintingBackup.size == BACKUP_MAX_SIZE)) return

        val latestLine = painting.value!!.last()

        paintingBackup.add(listOf(latestLine))
        _painting.value = painting.value?.minus(latestLine)
    }

    private fun setNormalPen(): NormalPen = NormalPen(::renewTools, ::saveLine).apply {
        setColor(color)
        setWidth(width)
    }

    private fun setRectanglePen(): RectanglePen = RectanglePen(::renewTools, ::saveLine).apply {
        setColor(color)
        setWidth(width)
    }

    private fun setCirclePen(): CirclePen = CirclePen(::renewTools, ::saveLine).apply {
        setColor(color)
        setWidth(width)
    }

    private fun setPathEraser(): PathEraser =
        PathEraser(::removeLines, (painting.value ?: emptyList()).toMutableList())

    private fun setLineEraser(): LineEraser = LineEraser(::saveLine).apply { setWidth(width) }

    private fun saveLine(line: Line) {
        _painting.value = painting.value?.plus(line)
    }

    private fun removeLines(line: Line) {
        _painting.value = painting.value?.minus(line)
    }

    private fun renewTools() {
        _painter.value = when (tool) {
            NORMAL_PEN -> setNormalPen()
            CIRCLE_PEN -> setCirclePen()
            RECTANGLE_PEN -> setRectanglePen()
            PATH_ERASER -> setPathEraser()
            LINE_ERASER -> setLineEraser()
        }
    }

    companion object {
        private const val DEFAULT_PEN_SIZE = 0f
        private const val BACKUP_MAX_SIZE = 3

        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                MainViewModel()
            }
        }
    }
}