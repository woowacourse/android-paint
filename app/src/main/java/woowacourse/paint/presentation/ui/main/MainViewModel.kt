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
import woowacourse.paint.domain.model.BrushWidth
import woowacourse.paint.presentation.ui.model.BrushColorModel
import woowacourse.paint.presentation.ui.model.BrushModel
import woowacourse.paint.presentation.ui.model.toBrushColor
import woowacourse.paint.presentation.ui.model.toPresentation

class MainViewModel : ViewModel() {

    private val _brushCondition = MutableStateFlow(INITIAL_Brush_CONDITION)
    val brush: StateFlow<BrushModel> = _brushCondition
        .map { it.toPresentation() }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.Eagerly,
            initialValue = _brushCondition.value.toPresentation(),
        )

    fun changeLineColor(color: BrushColorModel) {
        _brushCondition.value = _brushCondition.value.changeColor(color.toBrushColor())
    }

    fun changeLineWidth(width: Float) {
        _brushCondition.value = _brushCondition.value.changeWidth(BrushWidth(width))
    }

    companion object {
        private val INITIAL_Brush_CONDITION = Brush(BrushColor.RED, BrushWidth(30f))
    }
}
