package woowacourse.paint.view

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import woowacourse.paint.model.ColorItem
import woowacourse.paint.view.listener.ColorControllerVisibilityChangeListener
import woowacourse.paint.view.listener.ColorSelectionChangeListener
import woowacourse.paint.view.listener.WidthControllerVisibilityChangeListener

class MainViewModel :
    ViewModel(),
    ColorSelectionChangeListener,
    ColorControllerVisibilityChangeListener,
    WidthControllerVisibilityChangeListener {
    private val _colorControllerVisible: MutableLiveData<Boolean> = MutableLiveData(true)
    val colorControllerVisible: LiveData<Boolean> = _colorControllerVisible

    private val _widthControllerVisible: MutableLiveData<Boolean> = MutableLiveData(true)
    val widthControllerVisible: LiveData<Boolean> = _widthControllerVisible

    val colors: List<ColorItem> = ColorItem.entries

    private val _selectedColor: MutableLiveData<ColorItem> = MutableLiveData(colors[0])
    val selectedColor: LiveData<ColorItem> = _selectedColor

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

    companion object {
        private const val DEFAULT_WIDTH = 50f
        private const val EXCEPTION_INVALID_COLOR_CONTROLLER_VISIBILITY =
            "color controller visibility not valid"
        private const val EXCEPTION_INVALID_WIDTH_CONTROLLER_VISIBILITY =
            "width controller visibility not valid"
    }
}
