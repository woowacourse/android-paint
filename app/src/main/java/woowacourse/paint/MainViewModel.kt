package woowacourse.paint

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import woowacourse.paint.model.ColorBox
import woowacourse.paint.model.PaintColor

class MainViewModel : ViewModel() {
    private val _colors = MutableLiveData<List<ColorBox>>()
    val colors: LiveData<List<ColorBox>>
        get() = _colors

    init {
        setColors()
    }

    private fun setColors() {
        _colors.value = PaintColor.getColorBoxes(R.color.red)
    }

    fun setColorsSelected(colorBox: ColorBox) {
        _colors.value = _colors.value?.map {
            it.copy(isSelected = it == colorBox)
        }
    }
}
