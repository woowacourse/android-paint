package woowacourse.paint.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import woowacourse.paint.R
import woowacourse.paint.model.BrushSize
import woowacourse.paint.model.DrawMode
import woowacourse.paint.model.PaintColor

class MainViewModel : ViewModel() {
    private val _brushSize = MutableLiveData<BrushSize>()
    val brushSize: LiveData<BrushSize>
        get() = _brushSize

    private val _brushColors = MutableLiveData<List<PaintColor>>()
    val brushColors: LiveData<List<PaintColor>>
        get() = _brushColors

    private val _drawMode = MutableLiveData<DrawMode>()
    val drawMode: LiveData<DrawMode>
        get() = _drawMode

    init {
        _brushSize.value = BrushSize(BrushSize.DEFAULT_SIZE)
        _brushColors.value = PaintColor.colors
        _drawMode.value = DrawMode.DEFAULT_MODE
    }

    fun setBrushSize(size: Float) {
        _brushSize.value = BrushSize(size)
    }

    fun setBrushColor(index: Int) {
        val color = PaintColor.colors[index]
        _brushColors.value = _brushColors.value?.map {
            PaintColor(it.colorRes, it.colorRes == color.colorRes)
        }
    }

    fun setDrawMode(mode: DrawMode) {
        _drawMode.value = mode
    }
}
