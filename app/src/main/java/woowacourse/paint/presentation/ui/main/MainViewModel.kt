package woowacourse.paint.presentation.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import woowacourse.paint.domain.model.LineColor
import woowacourse.paint.domain.model.LineCondition
import woowacourse.paint.domain.model.LineWidth
import woowacourse.paint.presentation.ui.model.LineColorModel
import woowacourse.paint.presentation.ui.model.LineModel
import woowacourse.paint.presentation.ui.model.toLineColor
import woowacourse.paint.presentation.ui.model.toPresentation

class MainViewModel : ViewModel() {

    private val _lineCondition = MutableStateFlow(INITIAL_LINE_CONDITION)

    private val _line = MutableStateFlow(INITIAL_LINE_CONDITION.toPresentation())
    val line: StateFlow<LineModel> = _line.asStateFlow()

    init {
        viewModelScope.launch {
            _lineCondition.collect { lineCondition ->
                _line.value = lineCondition.toPresentation()
            }
        }
    }

    fun changeLineColor(color: LineColorModel) {
        _lineCondition.value = _lineCondition.value.changeColor(color.toLineColor())
    }

    fun changeLineWidth(width: Float) {
        _lineCondition.value = _lineCondition.value.changeWidth(LineWidth(width))
    }

    companion object {
        private val INITIAL_LINE_CONDITION = LineCondition(LineColor.RED, LineWidth())
    }
}
