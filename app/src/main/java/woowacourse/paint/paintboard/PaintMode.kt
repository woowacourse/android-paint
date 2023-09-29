package woowacourse.paint.paintboard

sealed interface PaintMode {
    object Eraser : PaintMode
    object Pen : PaintMode
}
