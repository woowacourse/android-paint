package woowacourse.paint.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import woowacourse.paint.R
import woowacourse.paint.customview.BrushColor
import woowacourse.paint.customview.Stroke
import woowacourse.paint.main.model.BrushColorBox

class MainViewModel : ViewModel() {

    private val _brushColor: MutableLiveData<Int> = MutableLiveData(R.color.red)
    val brushColor: LiveData<Int> get() = _brushColor

    private val _brushThickness: MutableLiveData<Float> = MutableLiveData(DEFAULT_BRUSH_THICKNESS)
    val brushThickness: LiveData<Float> get() = _brushThickness

    private val _strokes: MutableLiveData<List<Stroke>> = MutableLiveData()
    val strokes: LiveData<List<Stroke>> get() = _strokes

    private val _brushColorBoxes: MutableLiveData<List<BrushColorBox>> = MutableLiveData(
        BrushColor.getColorBoxes(
            _brushColor.value
                ?: throw NullPointerException(THE_BRUSH_COLOR_HAS_NOT_BEEN_SELECTED_YET),
        ),
    )
    val brushColorBoxes: LiveData<List<BrushColorBox>> get() = _brushColorBoxes

    fun updateBrushColor(colorRes: Int) {
        _brushColor.value = colorRes
    }

    fun updateBrushThickness(value: Float) {
        _brushThickness.value = value
    }

    fun updateStrokes(newStrokes: List<Stroke>) {
        _strokes.value = newStrokes
    }

    fun updateBrushColorBoxes(newBrushColorBoxes: List<BrushColorBox>) {
        _brushColorBoxes.value = newBrushColorBoxes
    }

    companion object {
        private const val DEFAULT_BRUSH_THICKNESS = 30f
        private const val THE_BRUSH_COLOR_HAS_NOT_BEEN_SELECTED_YET = "브러시의 색이 지정되지 않았습니다."
    }
}
