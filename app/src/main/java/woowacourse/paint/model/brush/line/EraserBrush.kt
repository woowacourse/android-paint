package woowacourse.paint.model.brush.line

import android.graphics.Paint
import android.graphics.Path
import android.graphics.PorterDuff
import android.graphics.PorterDuffXfermode

class EraserBrush(override var paint: Paint) : LineBrush {

    override var path: Path = Path()

    override fun setPaintingOption(paint: Paint) {
        this.paint.apply {
            xfermode = PorterDuffXfermode(PorterDuff.Mode.CLEAR)
            this.color = paint.color
            this.strokeWidth = paint.strokeWidth
        }
    }
}
