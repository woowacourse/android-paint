package woowacourse.paint.adapter

import woowacourse.paint.Diagram

interface DiagramHandler {
    fun selectDiagram(selectedDiagram: Diagram)
}
