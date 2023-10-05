package woowacourse.paint

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import woowacourse.paint.model.BrushType
import woowacourse.paint.model.ColorPalette

class MainViewModel : ViewModel() {

    private val _color = MutableLiveData<ColorPalette>()
    val color: LiveData<ColorPalette> = _color

    private val _width = MutableLiveData<Float>()
    val width: LiveData<Float> = _width

    private val _brushType = MutableLiveData(BrushType.PEN)
    val brushType: LiveData<BrushType> = _brushType

    private val _isErasing = MutableLiveData<Boolean>()
    val isErasing: LiveData<Boolean> = _isErasing

    private val _colorSelectionEvent = MutableLiveData<Boolean>()
    val colorSelectionEvent: LiveData<Boolean> = _colorSelectionEvent

    private val _widthSelectionEvent = MutableLiveData<Boolean>()
    val widthSelectionEvent: LiveData<Boolean> = _widthSelectionEvent

    private val _brushSelectionEvent = MutableLiveData<Boolean>()
    val brushSelectionEvent: LiveData<Boolean> = _brushSelectionEvent

    fun changeColor(color: ColorPalette) {
        _color.value = color
    }

    fun changeWidth(width: Float) {
        _width.value = width
    }

    fun changeBrush(brushType: BrushType) {
        _brushType.value = brushType
    }

    fun erase() {
        _isErasing.value = true
    }

    fun openColorSelection() {
        _colorSelectionEvent.value = true
    }

    fun openWidthSelection() {
        _widthSelectionEvent.value = true
    }

    fun openBrushSelection() {
        _brushSelectionEvent.value = true
    }
}
