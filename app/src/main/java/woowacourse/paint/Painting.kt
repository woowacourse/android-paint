package woowacourse.paint

import android.graphics.Canvas

class Painting(private val records: MutableList<PaintingRecord> = mutableListOf()) {

    fun drawOn(canvas: Canvas) {
        records.forEach {
            canvas.drawPath(it.path, it.brush)
        }
    }

    fun record(paintingRecord: PaintingRecord) {
        records.add(paintingRecord)
    }
}
