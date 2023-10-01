package woowacourse.paint

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import woowacourse.paint.canvas.CustomColor
import woowacourse.paint.model.ColorUiModel

class MainViewModel : ViewModel() {
    private val _paintChangingState = MutableLiveData<PaintChangingState>(PaintChangingState.Nothing)
    val paintChangingState: LiveData<PaintChangingState>
        get() = _paintChangingState

    private val _colors =
        MutableLiveData(
            CustomColor.getAllColors()
                .map { if (it.ordinal == 0) ColorUiModel(it, true) else ColorUiModel(it, false) },
        )
    val colors: LiveData<List<ColorUiModel>>
        get() = _colors

    fun showColorSetting() {
        if (_paintChangingState.value == PaintChangingState.ColorChanging) {
            _paintChangingState.value = PaintChangingState.Nothing
            return
        }
        _paintChangingState.value = PaintChangingState.ColorChanging
    }

    fun showWidthSetting() {
        if (_paintChangingState.value == PaintChangingState.WidthChanging) {
            _paintChangingState.value = PaintChangingState.Nothing
            return
        }
        _paintChangingState.value = PaintChangingState.WidthChanging
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
