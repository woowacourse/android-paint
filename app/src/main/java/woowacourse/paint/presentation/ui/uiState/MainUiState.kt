package woowacourse.paint.presentation.ui.uiState

import woowacourse.paint.data.model.SettingMode

data class MainUiState(
    val settingMode: SettingMode,
) {
    companion object {
        val Initial: MainUiState = MainUiState(SettingMode.NONE)
    }
}
