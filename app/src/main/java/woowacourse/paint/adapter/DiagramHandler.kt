package woowacourse.paint.adapter

import woowacourse.paint.paintcanvas.Diagram

interface DiagramHandler {
    fun selectDiagram(selectedDiagram: Diagram)
}
