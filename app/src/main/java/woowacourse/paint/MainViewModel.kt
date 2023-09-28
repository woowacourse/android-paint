package woowacourse.paint

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {

    private val _brushColor: MutableLiveData<Int> = MutableLiveData(R.color.red)
    val brushColor: LiveData<Int> get() = _brushColor

    private val _brushThickness: MutableLiveData<Float> = MutableLiveData(DEFAULT_BRUSH_THICKNESS)
    val brushThickness: LiveData<Float> get() = _brushThickness

    private val _strokes: MutableLiveData<List<Stroke>> = MutableLiveData()
    val strokes: LiveData<List<Stroke>> get() = _strokes

    private val _brushColorBoxes: MutableLiveData<List<BrushColorBox>> = MutableLiveData()
    val brushColorBoxes: LiveData<List<BrushColorBox>> get() = _brushColorBoxes

    fun updateBrushColor(colorRes: Int) {
        _brushColor.value = colorRes
    }

    fun updateStrokes(newStrokes: List<Stroke>) {
        _strokes.value = newStrokes
    }

    fun updateBrushThickness(value: Float) {
        _brushThickness.value = value
    }

    companion object {
        private const val DEFAULT_BRUSH_THICKNESS = 30f
    }
}
