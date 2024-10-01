package woowacourse.paint

import androidx.annotation.ColorRes
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import woowacourse.paint.PaintBoard.Companion.DEFAULT_PAINT_COLOR_RES
import woowacourse.paint.PaintBoard.Companion.DEFAULT_STROKE_WIDTH

class PaintViewModel : ViewModel(), PaintActionHandler {
    private val _colorRes: MutableLiveData<Int> = MutableLiveData(DEFAULT_PAINT_COLOR_RES)
    val colorRes: LiveData<Int> get() = _colorRes

    private val _strokeWidth: MutableLiveData<Float> = MutableLiveData(DEFAULT_STROKE_WIDTH)
    val strokeWidth: LiveData<Float> get() = _strokeWidth

    override fun changeColorRes(@ColorRes colorRes: Int) {
        _colorRes.value = colorRes
    }

    fun changeStrokeWidth(strokeWidth: Float) {
        _strokeWidth.value = strokeWidth
    }
}
