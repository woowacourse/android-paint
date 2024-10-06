package woowacourse.paint.view

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import woowacourse.paint.model.DrawingTool
import woowacourse.paint.model.DrawingToolType
import woowacourse.paint.model.ColorItem
import woowacourse.paint.view.listener.DrawingToolChangeListener
import woowacourse.paint.view.listener.DrawingToolControllerVisibilityChangeListener
import woowacourse.paint.view.listener.ColorControllerVisibilityChangeListener
import woowacourse.paint.view.listener.ColorSelectionChangeListener
import woowacourse.paint.view.listener.WidthControllerVisibilityChangeListener

class MainViewModel :
    ViewModel(),
    ColorSelectionChangeListener,
    ColorControllerVisibilityChangeListener,
    WidthControllerVisibilityChangeListener,
    DrawingToolControllerVisibilityChangeListener,
    DrawingToolChangeListener {
    private val _colorControllerVisible: MutableLiveData<Boolean> = MutableLiveData(true)
    val colorControllerVisible: LiveData<Boolean> = _colorControllerVisible

    private val _widthControllerVisible: MutableLiveData<Boolean> = MutableLiveData(true)
    val widthControllerVisible: LiveData<Boolean> = _widthControllerVisible

    private val _drawingToolControllerVisible: MutableLiveData<Boolean> = MutableLiveData(true)
    val drawingToolControllerVisible: LiveData<Boolean> = _drawingToolControllerVisible

    val colors: List<ColorItem> = ColorItem.entries
    val drawingTools: List<DrawingTool> = DrawingTool.values

    private val _selectedColor: MutableLiveData<ColorItem> = MutableLiveData(colors[0])
    val selectedColor: LiveData<ColorItem> = _selectedColor

    private val _selectedTool: MutableLiveData<DrawingToolType> = MutableLiveData(drawingTools[0].type)
    val selectedTool: LiveData<DrawingToolType> = _selectedTool

    val selectedWidth = MutableLiveData(DEFAULT_WIDTH)

    override fun onColorSelected(color: Int) {
        _selectedColor.value = colors.find { it.color == color }
    }

    override fun toggleColorControllerVisibility() {
        val currValue =
            colorControllerVisible.value ?: error(EXCEPTION_INVALID_COLOR_CONTROLLER_VISIBILITY)
        _colorControllerVisible.value = !currValue
    }

    override fun toggleWidthControllerVisibility() {
        val currValue =
            widthControllerVisible.value ?: error(EXCEPTION_INVALID_WIDTH_CONTROLLER_VISIBILITY)
        _widthControllerVisible.value = !currValue
    }

    override fun toggleDrawingToolControllerVisibility() {
        val currValue =
            drawingToolControllerVisible.value ?: error(EXCEPTION_INVALID_BRUSH_CONTROLLER_VISIBILITY)
        _drawingToolControllerVisible.value = !currValue
    }

    override fun onDrawingToolChanged(drawingTool: DrawingTool) {
        _selectedTool.value = drawingTool.type
    }

    companion object {
        private const val DEFAULT_WIDTH = 50f
        private const val EXCEPTION_INVALID_COLOR_CONTROLLER_VISIBILITY =
            "color controller visibility not valid"
        private const val EXCEPTION_INVALID_WIDTH_CONTROLLER_VISIBILITY =
            "width controller visibility not valid"
        private const val EXCEPTION_INVALID_BRUSH_CONTROLLER_VISIBILITY =
            "brush controller visibility not valid"
    }
}
