package woowacourse.paint.model.pen

import android.graphics.Paint

class BallpointPen : Pen() {
    override val style: Paint.Style = Paint.Style.STROKE
    override val cap: Paint.Cap = Paint.Cap.ROUND
    override val join: Paint.Join = Paint.Join.ROUND
}
