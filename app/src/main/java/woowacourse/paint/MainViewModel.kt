package woowacourse.paint

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import woowacourse.paint.model.BrushBox
import woowacourse.paint.model.ColorBox
import woowacourse.paint.model.PaintBrush
import woowacourse.paint.model.PaintColor
import woowacourse.paint.model.Painting
import woowacourse.paint.model.PaintingHistory

class MainViewModel : ViewModel() {
    private val _colors = MutableLiveData(paintColors)
    val colors: LiveData<List<ColorBox>>
        get() = _colors

    private val _paintBrush = MutableLiveData(paintBrushes)
    val paintBrush: LiveData<List<BrushBox>>
        get() = _paintBrush

    private val _paintingHistory = MutableLiveData(PaintingHistory())
    val paintingHistory: LiveData<PaintingHistory>
        get() = _paintingHistory

    fun setColorsSelected(colorBox: ColorBox) {
        _colors.value = _colors.value?.map {
            it.copy(isSelected = it == colorBox)
        }
    }

    fun setBrushesSelected(brush: BrushBox) {
        _paintBrush.value = _paintBrush.value?.map {
            it.copy(isSelected = it == brush)
        }
    }

    fun addHistory(painting: Painting) {
        _paintingHistory.value?.addHistory(painting)
    }

    fun undoHistory() {
        _paintingHistory.value?.undo()
    }

    fun redoHistory() {
        _paintingHistory.value?.redo()
    }

    fun clearHistory() {
        _paintingHistory.value?.clear()
    }

    companion object {
        private val paintColors = PaintColor.getColorBoxes(R.color.red)
        private val paintBrushes = PaintBrush.getPaintBrushes(PaintBrush.PEN)
    }
}
