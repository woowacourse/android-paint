package woowacourse.paint.model

import android.graphics.Paint
import androidx.annotation.DrawableRes
import woowacourse.paint.R
import woowacourse.paint.model.brush.Brush
import woowacourse.paint.model.brush.line.EraserBrush
import woowacourse.paint.model.brush.line.PenBrush
import woowacourse.paint.model.brush.shape.CircleBrush
import woowacourse.paint.model.brush.shape.RectangleBrush

enum class BrushType(@DrawableRes val icon: Int, val builder: (Paint) -> Brush) {
    PEN(R.drawable.pen, ::PenBrush),
    RECTANGLE(R.drawable.rectangle, ::RectangleBrush),
    FILLED_RECTANGLE(R.drawable.filled_rectangle, ::RectangleBrush),
    CIRCLE(R.drawable.circle, ::CircleBrush),
    FILLED_CIRCLE(R.drawable.filled_circle, ::CircleBrush),
    ERASER_LINE(R.drawable.eraser_line, ::EraserBrush),
}
