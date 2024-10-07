package woowacourse.paint.model

data class DrawingTool(
    val name: String,
    val type: DrawingToolType,
) {
    companion object {
        val values: List<DrawingTool> =
            listOf(
                DrawingTool("펜", DrawingToolType.PEN),
                DrawingTool("사각형", DrawingToolType.RECTANGULAR),
                DrawingTool("원", DrawingToolType.CIRCLE),
                DrawingTool("지우개", DrawingToolType.ERASER),
                DrawingTool("초기화", DrawingToolType.RESET),
            )
    }
}
