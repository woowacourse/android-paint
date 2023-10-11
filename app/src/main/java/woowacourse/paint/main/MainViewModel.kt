package woowacourse.paint.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import woowacourse.paint.model.BrushSize
import woowacourse.paint.model.DrawMode
import woowacourse.paint.model.PaintColor

class MainViewModel : ViewModel() {
    private val _brushSize = MutableLiveData<BrushSize>()
    val brushSize: LiveData<BrushSize>
        get() = _brushSize

    private val _brushColor = MutableLiveData<PaintColor>()
    val brushColor: LiveData<PaintColor>
        get() = _brushColor

    private val _drawMode = MutableLiveData<DrawMode>()
    val drawMode: LiveData<DrawMode>
        get() = _drawMode

    init {
        _brushSize.value = BrushSize(BrushSize.DEFAULT_SIZE)
        _brushColor.value = PaintColor.DEFAULT_COLOR
        _drawMode.value = DrawMode.DEFAULT_MODE
    }

    fun setBrushSize(size: Float) {
        _brushSize.value = BrushSize(size)
    }

    fun setBrushColor(color: PaintColor) {
        _brushColor.value = color
    }

    fun setDrawMode(mode: DrawMode) {
        _drawMode.value = mode
    }
}
