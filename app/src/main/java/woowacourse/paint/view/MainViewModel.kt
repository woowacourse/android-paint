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
//        listOf(
//            ColorItem(0xCD2701),
//            ColorItem(0xFF7C2A),
//            ColorItem(0xFFFF00),
//            ColorItem(0x0D9A00),
//            ColorItem(0x2601A8),
//        )

    private val _selectedColor: MutableLiveData<ColorItem> = MutableLiveData(colors[0])
    val selectedColor: LiveData<ColorItem> = _selectedColor

    val selectedWidth = MutableLiveData(50f)

    override fun onColorSelected(color: Int) {
        _selectedColor.value = colors.find { it.color == color }
    }

    override fun toggleColorControllerVisibility() {
        val currValue = colorControllerVisible.value ?: error("visibility not valid")
        _colorControllerVisible.value = !currValue
    }

    override fun toggleWidthControllerVisibility() {
        val currValue = widthControllerVisible.value ?: error("width not valid")
        _widthControllerVisible.value = !currValue
    }
}
