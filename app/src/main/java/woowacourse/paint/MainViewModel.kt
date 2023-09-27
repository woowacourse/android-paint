package woowacourse.paint

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import woowacourse.model.PaintColor

class MainViewModel : ViewModel() {
    var selectedPenStroke: Float = 50f
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
}
