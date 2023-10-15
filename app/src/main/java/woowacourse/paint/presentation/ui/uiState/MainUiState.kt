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
        private const val INIT_THICKNESS: Float = 0f

        val Initial: MainUiState = MainUiState(
            settingMode = SettingMode.NONE,
            color = Color.RED,
            thickness = INIT_THICKNESS,
            brush = Brush.PEN,
        )
    }
}
