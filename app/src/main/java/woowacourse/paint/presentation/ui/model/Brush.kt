package woowacourse.paint.presentation.ui.model

import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path

enum class Brush {
    PEN {
        override fun onActionMove(
            graphicPrimitives: List<GraphicPrimitive>,
            basePointX: Float,
            basePointY: Float,
            pointX: Float,
            pointY: Float,
            paint: Paint,
        ): List<GraphicPrimitive> {
            val result = graphicPrimitives.take(graphicPrimitives.size - 1)
            return result + graphicPrimitives.last().apply { path.lineTo(pointX, pointY) }
        }

        override fun getBrushPaint(paint: Paint): Paint =
            Paint(paint).apply {
                style = Paint.Style.STROKE
                strokeCap = Paint.Cap.ROUND
                strokeJoin = Paint.Join.ROUND
            }
    },
    RECTANGLE {
        override fun onActionMove(
            graphicPrimitives: List<GraphicPrimitive>,
            basePointX: Float,
            basePointY: Float,
            pointX: Float,
            pointY: Float,
            paint: Paint,
        ): List<GraphicPrimitive> {
            val result = graphicPrimitives.take(graphicPrimitives.size - 1)
            val path =
                Path().apply { addRect(basePointX, basePointY, pointX, pointY, Path.Direction.CW) }
            val graphicPrimitive = GraphicPrimitive(path, getBrushPaint(paint))
            return result + graphicPrimitive
        }

        override fun getBrushPaint(paint: Paint): Paint =
            Paint(paint).apply { style = Paint.Style.FILL }
    },
    CIRCLE {
        override fun onActionMove(
            graphicPrimitives: List<GraphicPrimitive>,
            basePointX: Float,
            basePointY: Float,
            pointX: Float,
            pointY: Float,
            paint: Paint,
        ): List<GraphicPrimitive> {
            val result = graphicPrimitives.take(graphicPrimitives.size - 1)
            val path =
                Path().apply { addOval(basePointX, basePointY, pointX, pointY, Path.Direction.CW) }
            val graphicPrimitive = GraphicPrimitive(path, getBrushPaint(paint))
            return result + graphicPrimitive
        }

        override fun getBrushPaint(paint: Paint): Paint =
            Paint(paint).apply { style = Paint.Style.FILL }
    },
    ERASER {
        override fun onActionMove(
            graphicPrimitives: List<GraphicPrimitive>,
            basePointX: Float,
            basePointY: Float,
            pointX: Float,
            pointY: Float,
            paint: Paint,
        ): List<GraphicPrimitive> {
            val result = graphicPrimitives.take(graphicPrimitives.size - 1)
            return result + graphicPrimitives.last().apply { path.lineTo(pointX, pointY) }
        }

        override fun getBrushPaint(paint: Paint): Paint =
            Paint(paint).apply {
                color = Color.WHITE
                style = Paint.Style.STROKE
                strokeCap = Paint.Cap.ROUND
                strokeJoin = Paint.Join.ROUND
            }
    },
    ;

    fun onActionDown(
        graphicPrimitives: List<GraphicPrimitive>,
        pointX: Float,
        pointY: Float,
        paint: Paint,
    ): List<GraphicPrimitive> {
        val path = Path().apply { moveTo(pointX, pointY) }
        val graphicPrimitive = GraphicPrimitive(path, getBrushPaint(paint))
        return graphicPrimitives + graphicPrimitive
    }

    abstract fun onActionMove(
        graphicPrimitives: List<GraphicPrimitive>,
        basePointX: Float,
        basePointY: Float,
        pointX: Float,
        pointY: Float,
        paint: Paint,
    ): List<GraphicPrimitive>

    protected abstract fun getBrushPaint(paint: Paint): Paint
}
