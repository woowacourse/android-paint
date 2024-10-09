package woowacourse.paint.adapter

import woowacourse.paint.PaintColor
import woowacourse.paint.paintcanvas.Diagram

interface PaintCanvasHandler {
    fun selectColor(selectedColor: PaintColor)

    fun selectDiagram(selectedDiagram: Diagram)

    fun undo()

    fun redo()
}
