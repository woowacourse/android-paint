package woowacourse.paint

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import woowacourse.paint.event.Event
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

    private val _colorSelectionEvent = MutableLiveData<Event<Boolean>>()
    val colorSelectionEvent: LiveData<Event<Boolean>> = _colorSelectionEvent

    private val _widthSelectionEvent = MutableLiveData<Event<Boolean>>()
    val widthSelectionEvent: LiveData<Event<Boolean>> = _widthSelectionEvent

    private val _brushSelectionEvent = MutableLiveData<Event<Boolean>>()
    val brushSelectionEvent: LiveData<Event<Boolean>> = _brushSelectionEvent

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
        _colorSelectionEvent.value = Event(true)
    }

    fun openWidthSelection() {
        _widthSelectionEvent.value = Event(true)
    }

    fun openBrushSelection() {
        _brushSelectionEvent.value = Event(true)
    }
}
