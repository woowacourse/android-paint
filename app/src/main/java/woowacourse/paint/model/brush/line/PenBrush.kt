package woowacourse.paint.model.brush.line

import android.graphics.Paint
import android.graphics.Path

class PenBrush(override var paint: Paint) : LineBrush {

    override var path: Path = Path()
}
