package woowacourse.paint.presentation.ui.uiState

import woowacourse.paint.data.model.Brush
import woowacourse.paint.data.model.Color
import woowacourse.paint.data.model.SettingMode

data class MainUiState(
    val settingMode: SettingMode,
    val color: Color,
    val brush: Brush,
) {
    companion object {
        val Initial: MainUiState = MainUiState(SettingMode.NONE, Color.RED, Brush.PEN)
    }
}
