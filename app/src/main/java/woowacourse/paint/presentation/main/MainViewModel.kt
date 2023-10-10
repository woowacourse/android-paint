package woowacourse.paint.presentation.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.now.domain.Brush
import com.now.domain.BrushWidth
import woowacourse.paint.mapper.toBrushColor
import woowacourse.paint.presentation.uimodel.BrushColorUiModel
import woowacourse.paint.presentation.uimodel.BrushToolView

class MainViewModel : ViewModel() {
    private val _brush = MutableLiveData(Brush.fromDefault())
    val brush: LiveData<Brush> = _brush

    fun changeBrushColor(color: BrushToolView) {
        if (color is BrushColorUiModel) {
            _brush.value = _brush.value?.changeColor(color.toBrushColor())
        }
    }

    fun changeBrushWidth(width: Float) {
        _brush.value = _brush.value?.changeWidth(BrushWidth(width))
    }
}
