package woowacourse.paint

data class Stroke(
    val path: android.graphics.Path,
    val color: Int,
    val width: Float,
)
