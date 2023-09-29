package woowacourse.paint

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {

    private val _color = MutableLiveData<ColorPalette>()
    val color: LiveData<ColorPalette> = _color

    fun changeColor(color: ColorPalette) {
        _color.value = color
    }
}
