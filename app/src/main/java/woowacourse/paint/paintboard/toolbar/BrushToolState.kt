package woowacourse.paint.paintboard.toolbar

data class BrushToolState(
    val lineSelected: Boolean = false,
    val eraserSelected: Boolean = false,
    val rectSelected: Boolean = false,
    val ovalSelected: Boolean = false,
    val lineDoubleSelected: Boolean = false,
    val eraserDoubleSelected: Boolean = false,
    val rectDoubleSelected: Boolean = false,
    val ovalDoubleSelected: Boolean = false,
) {

    fun select(tool: Tools): BrushToolState {
        when (tool) {
            Tools.LINE -> {
                if (lineSelected) return this.copy(lineDoubleSelected = true)
                return BrushToolState(lineSelected = true)
            }

            Tools.ERASER -> {
                if (eraserSelected) return this.copy(eraserDoubleSelected = true)
                return BrushToolState(eraserSelected = true)
            }

            Tools.RECT -> {
                if (rectSelected) return this.copy(rectDoubleSelected = true)
                return BrushToolState(rectSelected = true)
            }

            Tools.OVAL -> {
                if (ovalSelected) return this.copy(ovalDoubleSelected = true)
                return BrushToolState(ovalSelected = true)
            }
        }
    }

    fun isDoubleChecked(tool: Tools): Boolean {
        return when (tool) {
            Tools.LINE -> lineDoubleSelected
            Tools.ERASER -> eraserDoubleSelected
            Tools.RECT -> rectDoubleSelected
            Tools.OVAL -> ovalDoubleSelected
        }
    }
}
