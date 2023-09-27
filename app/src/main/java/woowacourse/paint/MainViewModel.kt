package woowacourse.paint

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import woowacourse.model.BoardColor

class MainViewModel : ViewModel() {
    var selectedPenStroke: Float = 50f
        private set

    var selectedPenColor: BoardColor = BoardColor.values().first()
        private set

    private val _colors: MutableLiveData<List<BoardColor>> =
        MutableLiveData(BoardColor.values().toList())
    val colors: LiveData<List<BoardColor>>
        get() = _colors

    private val _appliedColor: MutableLiveData<BoardColor> = MutableLiveData(selectedPenColor)
    val appliedColor: LiveData<BoardColor>
        get() = _appliedColor

    private val _appliedStroke: MutableLiveData<Float> = MutableLiveData(selectedPenStroke)
    val appliedStroke: LiveData<Float>
        get() = _appliedStroke

    val onChangeSelectedStroke = { value: Float ->
        Log.d("mendel!", "!! $value")
        selectedPenStroke = value
    }

    fun onAppliedColorChange() {
        _appliedColor.value = selectedPenColor
    }

    fun onAppliedStrokeChange() {
        _appliedStroke.value = selectedPenStroke
    }
}
