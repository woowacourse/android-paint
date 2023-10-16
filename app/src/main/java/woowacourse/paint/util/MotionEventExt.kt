package woowacourse.paint.util

import android.view.MotionEvent
import woowacourse.paint.entity.Point

val MotionEvent.point: Point
    get() {
        return Point(x, y)
    }
