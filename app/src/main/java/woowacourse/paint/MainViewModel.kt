package woowacourse.paint

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {
    private val _changingState = MutableLiveData<ChangingState>(ChangingState.Nothing)
    val changingState: LiveData<ChangingState>
        get() = _changingState

    private val _colors =
        MutableLiveData(CustomColor.getAllColors().map { ColorUiModel(it, false) })
    val colors: LiveData<List<ColorUiModel>>
        get() = _colors

    fun setChangeColor() {
        if (_changingState.value == ChangingState.ColorChanging) {
            _changingState.value = ChangingState.Nothing
            return
        }
        _changingState.value = ChangingState.ColorChanging
    }

    fun setChangeThickness() {
        if (_changingState.value == ChangingState.WidthChanging) {
            _changingState.value = ChangingState.Nothing
            return
        }
        _changingState.value = ChangingState.WidthChanging
    }

    fun pickColor(color: ColorUiModel) {
        _colors.value?.let { colors ->
            val newPalette = colors.toMutableList()
            newPalette.replaceAll {
                if (it.color == color.color) {
                    it.copy(isPicked = true)
                } else {
                    it.copy(isPicked = false)
                }
            }
            _colors.value = newPalette
        }
    }
}
