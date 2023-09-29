package woowacourse.paint

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import woowacourse.paint.canvas.CustomColor
import woowacourse.paint.model.ColorUiModel

class MainViewModel : ViewModel() {
    private val _changingState = MutableLiveData<ChangingState>(ChangingState.Nothing)
    val changingState: LiveData<ChangingState>
        get() = _changingState

    private val _colors =
        MutableLiveData(
            CustomColor.getAllColors()
                .map { if (it.ordinal == 0) ColorUiModel(it, true) else ColorUiModel(it, false) },
        )
    val colors: LiveData<List<ColorUiModel>>
        get() = _colors

    fun showColorSetting() {
        if (_changingState.value == ChangingState.ColorChanging) {
            _changingState.value = ChangingState.Nothing
            return
        }
        _changingState.value = ChangingState.ColorChanging
    }

    fun showWidthSetting() {
        if (_changingState.value == ChangingState.WidthChanging) {
            _changingState.value = ChangingState.Nothing
            return
        }
        _changingState.value = ChangingState.WidthChanging
    }

    fun pickColor(model: ColorUiModel) {
        _colors.value?.let { colors ->
            val newColors = colors.toMutableList()
            newColors.replaceAll {
                if (it.color == model.color) {
                    it.copy(isPicked = true)
                } else {
                    it.copy(isPicked = false)
                }
            }
            _colors.value = newColors
        }
    }
}
