package woowacourse.paint.presentation.ui.uiState

import woowacourse.paint.data.model.Color
import woowacourse.paint.data.model.SettingMode

data class MainUiState(
    val settingMode: SettingMode,
    val color: Color,
) {
    companion object {
        val Initial: MainUiState = MainUiState(SettingMode.NONE, Color.RED)
    }
}
