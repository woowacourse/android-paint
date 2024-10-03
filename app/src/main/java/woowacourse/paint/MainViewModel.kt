package woowacourse.paint

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import woowacourse.paint.model.ColorPalette

class MainViewModel : ViewModel() {
    private val _selectedColor = MutableLiveData(ColorPalette.RED)
    val selectedColor: LiveData<ColorPalette> = _selectedColor

    fun onColorSelected(color: ColorPalette) {
        _selectedColor.value = color
    }
}
