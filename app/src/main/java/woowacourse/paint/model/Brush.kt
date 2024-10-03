package woowacourse.paint.model

data class Brush(
    val name: String,
    val type: BrushType,
) {
    companion object {
        val values: List<Brush> =
            listOf(
                Brush("펜", BrushType.PEN),
                Brush("사각형", BrushType.RECTANGULAR),
                Brush("원", BrushType.CIRCLE),
                Brush("지우개", BrushType.ERASER),
                Brush("초기화", BrushType.RESET),
            )
    }
}
