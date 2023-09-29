package woowacourse.paint.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import woowacourse.paint.model.BoardColorItem
import woowacourse.paint.model.PaintColor

class MainViewModel : ViewModel() {
    val minStrokeWidth: Float = MIN_STROKE_WIDTH
    val maxStrokeWidth: Float = MAX_STROKE_WIDTH
    var selectedStroke: Float = (minStrokeWidth + maxStrokeWidth) / 2
        private set

    var selectedColor: PaintColor = PaintColor.values().first()
        private set

    private val _appliedColor: MutableLiveData<PaintColor> = MutableLiveData(selectedColor)
    val appliedColor: LiveData<PaintColor>
        get() = _appliedColor

    private val _appliedStroke: MutableLiveData<Float> = MutableLiveData(selectedStroke)
    val appliedStroke: LiveData<Float>
        get() = _appliedStroke

    private val _colors: MutableLiveData<List<BoardColorItem>> =
        MutableLiveData(getBoardColorItems(selectedColor))
    val colors: LiveData<List<BoardColorItem>>
        get() = _colors

    private fun getBoardColorItems(selectedColor: PaintColor): List<BoardColorItem> =
        PaintColor.values().map {
            if (it == selectedColor) return@map BoardColorItem(it, true)
            BoardColorItem(it, false)
        }

    fun onChangeSelectedColor(boardColorItem: BoardColorItem) {
        selectedColor = boardColorItem.color
    }

    val onSelectedStrokeChange = { value: Float ->
        selectedStroke = value
    }

    fun onAppliedColorChange() {
        _appliedColor.value = selectedColor
        _colors.value = getBoardColorItems(selectedColor)
    }

    fun onAppliedStrokeChange() {
        _appliedStroke.value = selectedStroke
    }

    companion object {
        private const val MIN_STROKE_WIDTH = 0f
        private const val MAX_STROKE_WIDTH = 100F
    }
}
