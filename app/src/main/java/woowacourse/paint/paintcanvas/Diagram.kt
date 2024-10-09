package woowacourse.paint.paintcanvas

import woowacourse.paint.paintcanvas.shape.EraserShape
import woowacourse.paint.paintcanvas.shape.OvalShape
import woowacourse.paint.paintcanvas.shape.PathShape
import woowacourse.paint.paintcanvas.shape.RectShape
import woowacourse.paint.paintcanvas.shape.Shape

enum class Diagram(val actionName: String) {
    PEN("펜"),
    RECT("직사각형"),
    OVAL("원"),
    ERASER("지우개"),
    ;

    fun toShape(): Shape {
        return when (this) {
            PEN -> PathShape()
            RECT -> RectShape()
            OVAL -> OvalShape()
            ERASER -> EraserShape()
        }
    }
}
