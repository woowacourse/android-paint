package woowacourse.paint

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {
    val colorPalette = ColorPalette()

    private val _selectedColor = MutableLiveData(colorPalette.colors[0])
    val selectedColor: LiveData<Color> = _selectedColor

    private val _strokeWidth = MutableLiveData(30)
    val strokeWidth: LiveData<Int> = _strokeWidth

    fun onColorSelected(color: Color) {
        _selectedColor.value = color
    }
}
