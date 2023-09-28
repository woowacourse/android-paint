package woowacourse.paint

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {
    private val _colors = MutableLiveData<List<ColorBox>>()
    val colors: LiveData<List<ColorBox>>
        get() = _colors

    private val _history = MutableLiveData<DrawingHistory>()
    val history: LiveData<DrawingHistory>
        get() = _history

    init {
        setColors()
    }

    private fun setColors() {
        _colors.value = paintColors
    }

    fun setColorsSelected(colorBox: ColorBox) {
        _colors.value = _colors.value?.map {
            it.copy(isSelected = it == colorBox)
        }
    }

    companion object {
        private val paintColors = listOf(
            ColorBox(R.color.red, true),
            ColorBox(R.color.orange, false),
            ColorBox(R.color.yellow, false),
            ColorBox(R.color.green, false),
            ColorBox(R.color.blue, false),
        )
    }
}
