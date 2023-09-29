package woowacourse.paint.presentation.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import woowacourse.paint.domain.model.BrushColor
import woowacourse.paint.domain.model.BrushCondition
import woowacourse.paint.domain.model.BrushWidth
import woowacourse.paint.presentation.ui.model.BrushColorModel
import woowacourse.paint.presentation.ui.model.BrushModel
import woowacourse.paint.presentation.ui.model.toLineColor
import woowacourse.paint.presentation.ui.model.toPresentation

class MainViewModel : ViewModel() {

    private val _brushCondition = MutableStateFlow(INITIAL_LINE_CONDITION)

    private val _brush = MutableStateFlow(INITIAL_LINE_CONDITION.toPresentation())
    val brush: StateFlow<BrushModel> = _brush.asStateFlow()

    init {
        viewModelScope.launch {
            _brushCondition.collect { lineCondition ->
                _brush.value = lineCondition.toPresentation()
            }
        }
    }

    fun changeLineColor(color: BrushColorModel) {
        _brushCondition.value = _brushCondition.value.changeColor(color.toLineColor())
    }

    fun changeLineWidth(width: Float) {
        _brushCondition.value = _brushCondition.value.changeWidth(BrushWidth(width))
    }

    companion object {
        private val INITIAL_LINE_CONDITION = BrushCondition(BrushColor.RED, BrushWidth())
    }
}
