package woowacourse.paint.model

enum class BrushColor(val argb: Long, val label: String) {
    RED(0xFFE53935, "빨강"),
    ORANGE(0xFFFB8C00, "주황"),
    YELLOW(0xFFFFC107, "노랑"),
    GREEN(0xFF43A047, "초록"),
    BLUE(0xFF1E88E5, "파랑"),
}

data class BrushSettings(
    val color: BrushColor = BrushColor.RED,
    val widthDp: Float = 6f,
)

data class DrawingPoint(val x: Float, val y: Float) {
    companion object {
        fun fromCanvasPosition(
            x: Float,
            y: Float,
            width: Float,
            height: Float,
        ): DrawingPoint {
            require(width > 0f && height > 0f)

            return DrawingPoint(
                x = (x / width).coerceIn(0f, 1f),
                y = (y / height).coerceIn(0f, 1f),
            )
        }
    }
}

data class Stroke(
    val points: List<DrawingPoint>,
    val color: BrushColor,
    val widthDp: Float,
)

data class DrawingState(
    val strokes: List<Stroke> = emptyList(),
    val activeStroke: Stroke? = null,
    val brush: BrushSettings = BrushSettings(),
)
