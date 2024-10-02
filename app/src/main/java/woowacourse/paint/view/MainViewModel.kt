package woowacourse.paint.view

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import woowacourse.paint.model.Brush
import woowacourse.paint.model.ColorItem
import woowacourse.paint.view.listener.BrushChangeListener
import woowacourse.paint.view.listener.BrushControllerVisibilityChangeListener
import woowacourse.paint.view.listener.ColorControllerVisibilityChangeListener
import woowacourse.paint.view.listener.ColorSelectionChangeListener
import woowacourse.paint.view.listener.WidthControllerVisibilityChangeListener

class MainViewModel :
    ViewModel(),
    ColorSelectionChangeListener,
    ColorControllerVisibilityChangeListener,
    WidthControllerVisibilityChangeListener,
    BrushControllerVisibilityChangeListener,
    BrushChangeListener {
    private val _colorControllerVisible: MutableLiveData<Boolean> = MutableLiveData(true)
    val colorControllerVisible: LiveData<Boolean> = _colorControllerVisible

    private val _widthControllerVisible: MutableLiveData<Boolean> = MutableLiveData(true)
    val widthControllerVisible: LiveData<Boolean> = _widthControllerVisible

    private val _brushControllerVisible: MutableLiveData<Boolean> = MutableLiveData(true)
    val brushControllerVisible: LiveData<Boolean> = _brushControllerVisible

    val colors: List<ColorItem> = ColorItem.entries
    val brushes: List<Brush> = Brush.values

    private val _selectedColor: MutableLiveData<ColorItem> = MutableLiveData(colors[0])
    val selectedColor: LiveData<ColorItem> = _selectedColor

    private val _selectedBrush: MutableLiveData<Brush> = MutableLiveData(brushes[0])
    val selectedBrush: LiveData<Brush> = _selectedBrush

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

    override fun toggleBrushControllerVisibility() {
        val currValue =
            brushControllerVisible.value ?: error(EXCEPTION_INVALID_BRUSH_CONTROLLER_VISIBILITY)
        _brushControllerVisible.value = !currValue
    }

    override fun onBrushChanged(brush: Brush) {
        _selectedBrush.value = brush
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
