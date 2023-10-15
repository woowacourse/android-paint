package woowacourse.paint.presentation.ui.uiState

import woowacourse.paint.presentation.ui.model.Brush
import woowacourse.paint.presentation.ui.model.Color
import woowacourse.paint.presentation.ui.model.SettingMode

data class MainUiState(
    val settingMode: SettingMode,
    val color: Color,
    val thickness: Float,
    val brush: Brush,
) {
    companion object {
        val Initial: MainUiState = MainUiState(SettingMode.NONE, Color.RED, 0f, Brush.PEN)
    }
}
