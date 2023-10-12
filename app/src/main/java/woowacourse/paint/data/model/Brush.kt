package woowacourse.paint.data.model

import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path

enum class Brush {
    PEN {
        override fun onActionMove(
            graphicPrimitives: MutableList<GraphicPrimitive>,
            basePointX: Float,
            basePointY: Float,
            pointX: Float,
            pointY: Float,
            paint: Paint,
        ) {
            graphicPrimitives.last().path.lineTo(pointX, pointY)
        }

        override fun getInitializedPaint(paint: Paint): Paint =
            Paint(paint).apply {
                style = Paint.Style.STROKE
                strokeCap = Paint.Cap.ROUND
                strokeJoin = Paint.Join.ROUND
            }
    },
    RECTANGLE {
        override fun onActionMove(
            graphicPrimitives: MutableList<GraphicPrimitive>,
            basePointX: Float,
            basePointY: Float,
            pointX: Float,
            pointY: Float,
            paint: Paint,
        ) {
            graphicPrimitives.removeLastOrNull()
            val path =
                Path().apply { addRect(basePointX, basePointY, pointX, pointY, Path.Direction.CW) }
            val graphicPrimitive = GraphicPrimitive(path, getInitializedPaint(paint))
            graphicPrimitives.add(graphicPrimitive)
        }

        override fun getInitializedPaint(paint: Paint): Paint =
            Paint(paint).apply { style = Paint.Style.FILL }
    },
    CIRCLE {
        override fun onActionMove(
            graphicPrimitives: MutableList<GraphicPrimitive>,
            basePointX: Float,
            basePointY: Float,
            pointX: Float,
            pointY: Float,
            paint: Paint,
        ) {
            graphicPrimitives.removeLastOrNull()
            val path =
                Path().apply { addOval(basePointX, basePointY, pointX, pointY, Path.Direction.CW) }
            val graphicPrimitive = GraphicPrimitive(path, getInitializedPaint(paint))
            graphicPrimitives.add(graphicPrimitive)
        }

        override fun getInitializedPaint(paint: Paint): Paint =
            Paint(paint).apply { style = Paint.Style.FILL }
    },
    ERASER {
        override fun onActionMove(
            graphicPrimitives: MutableList<GraphicPrimitive>,
            basePointX: Float,
            basePointY: Float,
            pointX: Float,
            pointY: Float,
            paint: Paint,
        ) {
            graphicPrimitives.last().path.lineTo(pointX, pointY)
        }

        override fun getInitializedPaint(paint: Paint): Paint =
            Paint(paint).apply {
                color = Color.WHITE
                style = Paint.Style.STROKE
                strokeCap = Paint.Cap.ROUND
                strokeJoin = Paint.Join.ROUND
            }
    },
    ;

    fun onActionDown(
        graphicPrimitives: MutableList<GraphicPrimitive>,
        pointX: Float,
        pointY: Float,
        paint: Paint,
    ) {
        val path = Path().apply { moveTo(pointX, pointY) }
        val graphicPrimitive = GraphicPrimitive(path, getInitializedPaint(paint))
        graphicPrimitives.add(graphicPrimitive)
    }

    abstract fun onActionMove(
        graphicPrimitives: MutableList<GraphicPrimitive>,
        basePointX: Float,
        basePointY: Float,
        pointX: Float,
        pointY: Float,
        paint: Paint,
    )

    protected abstract fun getInitializedPaint(paint: Paint): Paint
}
