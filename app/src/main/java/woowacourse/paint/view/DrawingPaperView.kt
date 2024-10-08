package woowacourse.paint.view

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.os.Parcel
import android.os.Parcelable
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import androidx.core.os.ParcelCompat
import kotlinx.parcelize.Parcelize
import woowacourse.paint.utils.PaintWrapper
import woowacourse.paint.utils.dp
import woowacourse.paint.view.shape.BrushShape
import woowacourse.paint.view.shape.Circle
import woowacourse.paint.view.shape.Eraser
import woowacourse.paint.view.shape.Pencil
import woowacourse.paint.view.shape.Rectangle

class DrawingPaperView
    @JvmOverloads
    constructor(
        context: Context,
        attrs: AttributeSet? = null,
        defStyle: Int = 0,
    ) : View(context, attrs, defStyle) {
        private var drawingState =
            DrawingState(
                brushType = BrushType.PEN,
                panelType = PanelType.BRUSH_COLOR,
                color = DEFAULT_COLOR,
                strokeWidth = DEFAULT_STROKE_WIDTH,
                shapes = emptyList(),
                redoShapes = emptyList(),
            )

        val panelType: PanelType
            get() = drawingState.panelType

        init {
            setLayerType(LAYER_TYPE_HARDWARE, null)
        }

        private fun createPaint(
            color: Int,
            strokeWidth: Float,
        ): Paint {
            return Paint().apply {
                this.color = color
                this.strokeWidth = strokeWidth
                isAntiAlias = true
                style = Paint.Style.STROKE
                strokeJoin = Paint.Join.ROUND
            }
        }

        override fun onDraw(canvas: Canvas) {
            super.onDraw(canvas)
            for (shape in drawingState.shapes) {
                shape.draw(canvas)
            }
        }

        @SuppressLint("ClickableViewAccessibility")
        override fun onTouchEvent(event: MotionEvent): Boolean {
            val x = event.x
            val y = event.y
            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    val newShape = createShape(x, y)
                    drawingState =
                        drawingState.copy(
                            shapes = drawingState.shapes + newShape,
                            redoShapes = emptyList(),
                        )
                }

                MotionEvent.ACTION_MOVE -> {
                    drawingState.shapes.lastOrNull()?.let {
                        it.updatePosition(x, y)
                        invalidate()
                    }
                }

                MotionEvent.ACTION_UP -> {}
            }
            return true
        }

        fun undo() {
            if (drawingState.shapes.isNotEmpty()) {
                val lastShape = drawingState.shapes.last()
                drawingState =
                    drawingState.copy(
                        shapes = drawingState.shapes.dropLast(1),
                        redoShapes = drawingState.redoShapes + lastShape,
                    )
                invalidate()
            }
        }

        fun redo() {
            if (drawingState.redoShapes.isNotEmpty()) {
                val lastRedoShape = drawingState.redoShapes.last()
                drawingState =
                    drawingState.copy(
                        shapes = drawingState.shapes + lastRedoShape,
                        redoShapes = drawingState.redoShapes.dropLast(1),
                    )
                invalidate()
            }
        }

        fun clearAll() {
            drawingState =
                drawingState.copy(
                    shapes = emptyList(),
                    redoShapes = emptyList(),
                )
            invalidate()
        }

        fun changeBrushType(brushType: BrushType) {
            drawingState = drawingState.copy(brushType = brushType)
        }

        fun changeColor(color: Int) {
            drawingState = drawingState.copy(color = color)
        }

        fun changeStrokeWidth(strokeWidth: Float) {
            drawingState = drawingState.copy(strokeWidth = strokeWidth)
        }

        fun changePanelType(panelType: PanelType) {
            drawingState = drawingState.copy(panelType = panelType)
        }

        private fun createShape(
            x: Float,
            y: Float,
        ): BrushShape {
            val paint = PaintWrapper.from(createPaint(drawingState.color, drawingState.strokeWidth))
            return when (drawingState.brushType) {
                BrushType.PEN -> Pencil(x, y, paint, drawingState.strokeWidth)
                BrushType.RECTANGLE -> Rectangle(x, y, paint, drawingState.strokeWidth)
                BrushType.CIRCLE -> Circle(x, y, paint, drawingState.strokeWidth)
                BrushType.ERASER -> Eraser(x, y, paint, drawingState.strokeWidth)
            }
        }

        override fun onSaveInstanceState(): Parcelable {
            return SavedState(super.onSaveInstanceState()).apply {
                drawingState = this@DrawingPaperView.drawingState
            }
        }

        override fun onRestoreInstanceState(state: Parcelable?) {
            if (state is SavedState) {
                super.onRestoreInstanceState(state.superState)
                drawingState = state.drawingState ?: return
                invalidate()
            } else {
                super.onRestoreInstanceState(state)
            }
        }

        private class SavedState : BaseSavedState {
            var drawingState: DrawingState? = null

            constructor(superState: Parcelable?) : super(superState)

            constructor(parcel: Parcel) : super(parcel) {
                drawingState =
                    ParcelCompat.readParcelable(
                        parcel,
                        DrawingState::class.java.classLoader,
                        DrawingState::class.java,
                    )
            }

            override fun writeToParcel(
                parcel: Parcel,
                flags: Int,
            ) {
                super.writeToParcel(parcel, flags)
                parcel.writeParcelable(drawingState, flags)
            }

            companion object {
                @JvmField
                val CREATOR: Parcelable.Creator<SavedState> =
                    object : Parcelable.Creator<SavedState> {
                        override fun createFromParcel(parcel: Parcel): SavedState {
                            return SavedState(parcel)
                        }

                        override fun newArray(size: Int): Array<SavedState?> {
                            return arrayOfNulls(size)
                        }
                    }
            }
        }

        companion object {
            private const val DEFAULT_COLOR = Color.BLACK
            private val DEFAULT_STROKE_WIDTH = 5f.dp
        }
    }

@Parcelize
data class DrawingState(
    val brushType: BrushType,
    val panelType: PanelType,
    val color: Int,
    val strokeWidth: Float,
    val shapes: List<BrushShape>,
    val redoShapes: List<BrushShape>,
) : Parcelable
