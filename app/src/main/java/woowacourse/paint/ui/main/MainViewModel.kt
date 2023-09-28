package woowacourse.paint.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import woowacourse.paint.model.PaintColor

class MainViewModel : ViewModel() {
    val minStrokeWidth: Float = MIN_STROKE_WIDTH
    val maxStrokeWidth: Float = MAX_STROKE_WIDTH
    var selectedPenStroke: Float = (minStrokeWidth + maxStrokeWidth) / 2
        private set

    var selectedPenColor: PaintColor = PaintColor.values().first()
        private set

    private val _colors: MutableLiveData<List<PaintColor>> =
        MutableLiveData(PaintColor.values().toList())
    val colors: LiveData<List<PaintColor>>
        get() = _colors

    private val _appliedColor: MutableLiveData<PaintColor> = MutableLiveData(selectedPenColor)
    val appliedColor: LiveData<PaintColor>
        get() = _appliedColor

    private val _appliedStroke: MutableLiveData<Float> = MutableLiveData(selectedPenStroke)
    val appliedStroke: LiveData<Float>
        get() = _appliedStroke

    fun onChangeSelectedColor(color: PaintColor) {
        selectedPenColor = color
    }

    val onSelectedStrokeChange = { value: Float ->
        selectedPenStroke = value
    }

    fun onAppliedColorChange() {
        _appliedColor.value = selectedPenColor
    }

    fun onAppliedStrokeChange() {
        _appliedStroke.value = selectedPenStroke
    }

    companion object {
        private const val MIN_STROKE_WIDTH = 0f
        private const val MAX_STROKE_WIDTH = 100F
    }
}
