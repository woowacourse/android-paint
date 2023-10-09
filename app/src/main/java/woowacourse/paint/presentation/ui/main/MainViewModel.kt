package woowacourse.paint.presentation.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import woowacourse.paint.domain.model.Brush
import woowacourse.paint.domain.model.BrushColor
import woowacourse.paint.domain.model.BrushType
import woowacourse.paint.domain.model.BrushWidth
import woowacourse.paint.presentation.ui.model.BrushColorModel
import woowacourse.paint.presentation.ui.model.BrushModel
import woowacourse.paint.presentation.ui.model.toBrushColor
import woowacourse.paint.presentation.ui.model.toPresentation

class MainViewModel : ViewModel() {

    private val _brush = MutableStateFlow(INITIAL_BRUSH)
    val brush: StateFlow<BrushModel> = _brush
        .map { it.toPresentation() }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.Eagerly,
            initialValue = _brush.value.toPresentation(),
        )

    fun changeBrushColor(color: BrushColorModel) {
        _brush.value = _brush.value.changeColor(color.toBrushColor())
    }

    fun changeBrushWidth(width: Float) {
        _brush.value = _brush.value.changeWidth(BrushWidth(width))
    }

    fun changeBrushType(type: BrushType) {
        _brush.value = _brush.value.changeType(type)
    }

    companion object {
        private val INITIAL_BRUSH = Brush(BrushColor.RED, BrushWidth(30f), BrushType.PEN)
    }
}
