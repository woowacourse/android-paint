package woowacourse.paint

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import woowacourse.paint.model.Color
import woowacourse.paint.model.ColorPalette

class MainViewModel : ViewModel() {
    val colorPalette = ColorPalette()

    private val _selectedColor = MutableLiveData(colorPalette.colors[0])
    val selectedColor: LiveData<Color> = _selectedColor

    fun onColorSelected(color: Color) {
        _selectedColor.value = color
    }
}
