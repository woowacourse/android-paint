package woowacourse.paint.customview

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.findViewTreeViewModelStoreOwner
import woowacourse.paint.MainViewModel
import woowacourse.paint.model.PaintBrush
import woowacourse.paint.model.Painting

class PaintingCanvas @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
) : View(context, attrs) {

    private val viewModel: MainViewModel by lazy {
        ViewModelProvider(findViewTreeViewModelStoreOwner()!!)[MainViewModel::class.java]
    }
    private var previousX = 0f
    private var previousY = 0f

    init {
        isFocusable = true
        isFocusableInTouchMode = true
        setLayerType(LAYER_TYPE_HARDWARE, null)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        viewModel.paletteHistory.forEach {
            it.draw(canvas)
        }
        viewModel.painting.draw(canvas)
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(event: MotionEvent): Boolean {
        val pointX = event.x
        val pointY = event.y
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                viewModel.updatePaintingElement {
                    it.movePath(pointX, pointY)
                }
                previousX = pointX
                previousY = pointY
            }

            MotionEvent.ACTION_MOVE -> {
                viewModel.painting.initPath(previousX, previousY, pointX, pointY)
            }

            MotionEvent.ACTION_UP -> {
                viewModel.paletteHistory.addHistory(viewModel.painting)
            }

            else -> super.onTouchEvent(event)
        }
        invalidate()
        return true
    }

    fun setStroke(value: Float) {
        viewModel.updatePaintingElement {
            it.setStroke(value)
        }
    }

    fun setColor(color: Int) {
        viewModel.updatePaintingElement {
            it.setColor(context.getColor(color))
        }
    }

    fun setBrush(brush: PaintBrush) {
        viewModel.updatePaintingElement {
            it.setPaintBrush(brush.brushTool)
        }
    }

    fun undoCanvas() {
        updateCanvasState {
            viewModel.paletteHistory.undo()
        }
    }

    fun redoCanvas() {
        updateCanvasState {
            viewModel.paletteHistory.redo()
        }
    }

    fun resetCanvas() {
        updateCanvasState {
            viewModel.paletteHistory.clear()
        }
    }

    private inline fun updateCanvasState(crossinline action: (Painting) -> Unit) {
        viewModel.updatePaintingElement {
            action(it)
            it.getNewPainting()
        }
        invalidate()
    }

    companion object {
        const val DEFAULT_STROKE_WIDTH = 50.0f
    }
}
