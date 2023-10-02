package woowacourse.paint.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import woowacourse.paint.model.BrushSize
import woowacourse.paint.model.PaintColor

class MainViewModel : ViewModel() {
    private val _brushSize = MutableLiveData<BrushSize>()
    val brushSize: LiveData<BrushSize>
        get() = _brushSize

    private val _brushColor = MutableLiveData<PaintColor>()
    val brushColor: LiveData<PaintColor>
        get() = _brushColor

    init {
        _brushSize.value = BrushSize(BrushSize.DEFAULT_SIZE)
        _brushColor.value = PaintColor.getDefaultColor()
    }

    fun setBrushSize(size: Float) {
        _brushSize.value = BrushSize(size)
    }

    fun setBrushColor(color: PaintColor) {
        _brushColor.value = color
    }
}
