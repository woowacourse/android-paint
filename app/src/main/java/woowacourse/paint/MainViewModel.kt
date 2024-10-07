package woowacourse.paint

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import woowacourse.paint.model.ColorPalette
import woowacourse.paint.model.ShapeType

class MainViewModel : ViewModel() {
    private val _selectedColor = MutableLiveData(ColorPalette.RED)
    val selectedColor: LiveData<ColorPalette> = _selectedColor

    private val _selectedShape = MutableLiveData(ShapeType.PEN)
    val selectedShape: LiveData<ShapeType> = _selectedShape

    fun onColorSelected(color: ColorPalette) {
        _selectedColor.value = color
    }

    fun onPenSelected() {
        _selectedShape.value = ShapeType.PEN
    }

    fun onEraserSelected() {
        _selectedShape.value = ShapeType.ERASER
    }

    fun onCircleSelected() {
        _selectedShape.value = ShapeType.CIRCLE
    }

    fun onSquareSelected() {
        _selectedShape.value = ShapeType.SQUARE
    }
}
