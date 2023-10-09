package woowacourse.paint

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import woowacourse.paint.canvas.Brush
import woowacourse.paint.canvas.PaletteColor
import woowacourse.paint.model.ColorUiModel

class MainViewModel : ViewModel() {
    private val _paintChangingState =
        MutableLiveData(PaintChangingState.Nothing)
    val paintChangingState: LiveData<PaintChangingState>
        get() = _paintChangingState

    private val _colors =
        MutableLiveData(PaletteColor.getAllColors().map { ColorUiModel(it, it.ordinal == 0) })
    val colors: LiveData<List<ColorUiModel>>
        get() = _colors

    private var _selectedBrush = MutableLiveData(Brush.PEN)
    val selectedBrush: LiveData<Brush>
        get() = _selectedBrush

    val selectedColor: LiveData<PaletteColor>
        get() = Transformations.map(_colors) { colors ->
            colors.firstOrNull { it.isPicked }?.color ?: DEFAULT_SELECTED_COLOR
        }

    private val _width = MutableLiveData(DEFAULT_WIDTH)
    val width: LiveData<Float>
        get() = _width

    fun setSettingState(state: PaintChangingState) {
        if (_paintChangingState.value == state) {
            _paintChangingState.value = PaintChangingState.Nothing
            return
        }
        _paintChangingState.value = state
    }

    fun pickBrush(brush: Brush) {
        _selectedBrush.value = brush
    }

    fun pickColor(model: ColorUiModel) {
        val colors = _colors.value ?: return
        _colors.value = colors.map { it.copy(isPicked = it.color == model.color) }
    }

    fun pickWidth(selectedWidth: Float) {
        _width.value = selectedWidth
    }

    companion object {
        const val DEFAULT_WIDTH = 1F
        const val MIN_WIDTH = 1f
        const val MAX_WIDTH = 100f
        val DEFAULT_SELECTED_COLOR = PaletteColor.RED
    }
}
