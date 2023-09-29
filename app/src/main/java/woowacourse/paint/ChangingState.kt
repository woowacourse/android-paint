package woowacourse.paint

sealed class ChangingState {
    object Nothing : ChangingState()
    object ColorChanging : ChangingState()
    object ThicknessChanging : ChangingState()
}
