package woowacourse.paint

enum class BrushType {
    PEN,
    RECTANGLE,
    CIRCLE,
    ERASER,
    ;

    companion object {
        val DEFAULT_BRUSH_TYPE = PEN
        lateinit var brushType: BrushType
            private set

        fun changeBrushType(newBrushType: BrushType) {
            brushType = newBrushType
        }
    }
}
