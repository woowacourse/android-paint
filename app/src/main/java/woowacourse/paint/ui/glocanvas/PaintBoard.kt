package woowacourse.paint.ui.glocanvas

import android.annotation.SuppressLint
import android.content.Context
import android.content.res.Configuration
import android.graphics.Canvas
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import dagger.hilt.android.AndroidEntryPoint
import woowacourse.paint.di.DrawingsQualifier
import woowacourse.paint.di.SavedDrawingsQualifier
import woowacourse.paint.ui.glocanvas.drawing.Drawings
import javax.inject.Inject

@AndroidEntryPoint
class PaintBoard(context: Context, attrs: AttributeSet) : View(context, attrs) {
    @Inject
    @DrawingsQualifier
    lateinit var drawings: Drawings

    @Inject
    @SavedDrawingsQualifier
    lateinit var savedDrawings: Drawings
    private lateinit var palette: Palette

    private val screenWidth by lazy { resources.displayMetrics.widthPixels }
    private val screenHeight by lazy { resources.displayMetrics.heightPixels }
    private val orientation by lazy { resources.configuration.orientation }

    private val scrollEventHandler: TwoPointerScrollEventHandler by lazy {
        val actionBarHeight = ACTION_BAR_DP * resources.displayMetrics.density / 2
        val paintBoardStartY = y
        val paintBoardEndY = -screenWidth.toFloat() + screenHeight.toFloat() - actionBarHeight
        TwoPointerScrollEventHandler(paintBoardStartY, paintBoardEndY) {
            y = it
        }
    }

    init {
        setLayerType(LAYER_TYPE_HARDWARE, null)
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        resizePaintBoard()
    }

    private fun resizePaintBoard() {
        if (orientation == Configuration.ORIENTATION_PORTRAIT) {
            layoutParams = layoutParams.apply {
                width = screenWidth
                height = screenHeight
            }
        } else {
            layoutParams = layoutParams.apply {
                width = screenHeight
                height = screenWidth
            }
        }
        requestLayout()
    }

    fun setupPalette(palette: Palette) {
        this.palette = palette
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        drawings.items.forEach {
            it.onDraw(canvas)
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(event: MotionEvent): Boolean {
        if (event.actionMasked == MotionEvent.ACTION_POINTER_DOWN) {
            if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
                drawings.removeLast()
                return scrollEventHandler.onTouchEvent(y, event)
            }
        }

        if (!scrollEventHandler.isScrolling) {
            if (event.action == MotionEvent.ACTION_DOWN) {
                val paint = palette.getPaint()
                val drawing = palette.drawingTool.createDrawing(paint, this::invalidate)
                drawings.addLast(drawing)
                savedDrawings.clear()
            }
            return drawings.getLastDrawing().onTouchEvent(event)
        }
        return scrollEventHandler.onTouchEvent(y, event)
    }

    fun goToPreviousDrawing() {
        val drawing = drawings.removeLast()
        drawing?.let { savedDrawings.addFirst(drawing) }
        invalidate()
    }

    fun goToNextDrawing() {
        val drawing = savedDrawings.removeFirst()
        drawing?.let { drawings.addLast(drawing) }
        invalidate()
    }

    fun setNewCanvas() {
        drawings.clear()
        savedDrawings.clear()
        invalidate()
    }

    companion object {
        private const val ACTION_BAR_DP = 56
    }
}
