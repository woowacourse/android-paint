package woowacourse.paint.customView

import android.graphics.Color
import android.graphics.Paint
import android.graphics.PorterDuff
import android.graphics.PorterDuffXfermode
import androidx.annotation.ColorInt
import woowacourse.paint.customView.content.BrushType

class PaintInfo {
    @ColorInt
    var currentColor: Int = DEFAULT_COLOR

    var minStrokeWidth: Float = DEFAULT_MIN_STROKE_WIDTH
        set(value) {
            require(value in DEFAULT_MIN_STROKE_WIDTH..maxStrokeWidth) { "[ERROR] 펜의 최소 두께는 최대 두께 보다 작고 0이상이여야 합니다" }
            field = value
            if (currentStrokeWidth < value) currentStrokeWidth = value
            updateDefaultStrokeWidth()
        }

    var maxStrokeWidth: Float = DEFAULT_MAX_STROKE_WIDTH
        set(value) {
            require(value > minStrokeWidth) { "[ERROR] 펜의 최대 두께는 최소 두께 보다 커야 합니다" }
            field = value
            if (currentStrokeWidth > value) currentStrokeWidth = value
            updateDefaultStrokeWidth()
        }
    private var defaultStrokeWidth: Float = (minStrokeWidth + maxStrokeWidth) / 2

    var currentStrokeWidth: Float = defaultStrokeWidth
        set(value) {
            require(value in minStrokeWidth..maxStrokeWidth) { "[ERROR] 펜 두께는 ${minStrokeWidth}이상 ${maxStrokeWidth}이하의 범위만 가능합니다" }
            field = value
        }

    private fun updateDefaultStrokeWidth() {
        defaultStrokeWidth = (minStrokeWidth + maxStrokeWidth) / 2
    }

    fun getPaint(contentType: BrushType): Paint {
        return when (contentType) {
            BrushType.Stroke -> createStrokePaint()
            BrushType.Rectangle -> createRectanglePaint()
            BrushType.Eraser -> createEraserPaint()
        }
    }

    private fun createStrokePaint() = Paint().apply {
        isAntiAlias = true
        style = Paint.Style.STROKE
        strokeCap = Paint.Cap.ROUND
        strokeJoin = Paint.Join.ROUND
        color = currentColor
        this.strokeWidth = currentStrokeWidth
    }

    private fun createRectanglePaint() = Paint().apply {
        style = Paint.Style.FILL_AND_STROKE
        color = currentColor
    }

    private fun createEraserPaint() = Paint().apply {
        isAntiAlias = true
        style = Paint.Style.STROKE
        strokeCap = Paint.Cap.ROUND
        strokeJoin = Paint.Join.ROUND
        xfermode = PorterDuffXfermode(PorterDuff.Mode.CLEAR)
        this.strokeWidth = currentStrokeWidth
    }

    companion object {
        const val DEFAULT_MIN_STROKE_WIDTH = 0f
        const val DEFAULT_MAX_STROKE_WIDTH = 100f

        @ColorInt
        const val DEFAULT_COLOR = Color.RED
    }
}
