package woowacourse.paint

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import woowacourse.paint.canvas.CustomColor
import woowacourse.paint.model.ColorUiModel

class MainViewModel : ViewModel() {
    private val _Paint_changingState = MutableLiveData<PaintChangingState>(PaintChangingState.Nothing)
    val paintChangingState: LiveData<PaintChangingState>
        get() = _Paint_changingState

    private val _colors =
        MutableLiveData(
            CustomColor.getAllColors()
                .map { if (it.ordinal == 0) ColorUiModel(it, true) else ColorUiModel(it, false) },
        )
    val colors: LiveData<List<ColorUiModel>>
        get() = _colors

    fun showColorSetting() {
        if (_Paint_changingState.value == PaintChangingState.ColorChanging) {
            _Paint_changingState.value = PaintChangingState.Nothing
            return
        }
        _Paint_changingState.value = PaintChangingState.ColorChanging
    }

    fun showWidthSetting() {
        if (_Paint_changingState.value == PaintChangingState.WidthChanging) {
            _Paint_changingState.value = PaintChangingState.Nothing
            return
        }
        _Paint_changingState.value = PaintChangingState.WidthChanging
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
