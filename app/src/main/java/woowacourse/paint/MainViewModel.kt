package woowacourse.paint

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import woowacourse.paint.canvas.PaletteColor
import woowacourse.paint.model.ColorUiModel

class MainViewModel : ViewModel() {
    private val _paintChangingState =
        MutableLiveData<PaintChangingState>(PaintChangingState.Nothing)
    val paintChangingState: LiveData<PaintChangingState>
        get() = _paintChangingState

    private val _colors =
        MutableLiveData(
            PaletteColor.getAllColors()
                .map { ColorUiModel(it, it.ordinal == 0) },
        )
    val colors: LiveData<List<ColorUiModel>>
        get() = _colors

    val selectedColor: LiveData<PaletteColor>
        get() = Transformations.map(_colors) { colors ->
            colors.firstOrNull { it.isPicked }?.color ?: PaletteColor.RED
        }

    private val _width = MutableLiveData(0f)
    val width: LiveData<Float>
        get() = _width

    fun setColorSettingState() {
        if (_paintChangingState.value == PaintChangingState.ColorChanging) {
            _paintChangingState.value = PaintChangingState.Nothing
            return
        }
        _paintChangingState.value = PaintChangingState.ColorChanging
    }

    fun setWidthSettingState() {
        if (_paintChangingState.value == PaintChangingState.WidthChanging) {
            _paintChangingState.value = PaintChangingState.Nothing
            return
        }
        _paintChangingState.value = PaintChangingState.WidthChanging
    }

    fun pickColor(model: ColorUiModel) {
        val colors = _colors.value ?: return
        _colors.value = colors.map { it.copy(isPicked = it.color == model.color) }
    }

    fun pickWidth(selectedWidth: Float) {
        _width.value = selectedWidth
    }
}
