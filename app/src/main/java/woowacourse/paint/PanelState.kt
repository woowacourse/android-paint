package woowacourse.paint

data class PanelState(
    val type: PanelType,
) {
    val blushColorVisible: Boolean
        get() = type == PanelType.BRUSH_COLOR

    val brushStrokeVisible: Boolean
        get() = type == PanelType.BRUSH_STROKE

    val toolVisible: Boolean
        get() = type == PanelType.TOOL
}