package woowacourse.paint

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import woowacourse.paint.view.BrushType

class DrawingPaperViewModel : ViewModel() {
    private val _panelState: MutableStateFlow<PanelState> =
        MutableStateFlow(PanelState(PanelType.BRUSH_COLOR))
    val panelState: StateFlow<PanelState> = _panelState.asStateFlow()

    fun changePanelState(panelType: PanelType) {
        _panelState.value = _panelState.value.copy(type = panelType)
    }

    fun changeBrushType(brushType: BrushType) {
        _panelState.value = _panelState.value.copy(brushType = brushType)
    }
}
