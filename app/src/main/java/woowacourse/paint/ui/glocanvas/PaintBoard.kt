package woowacourse.paint.ui.glocanvas

import android.annotation.SuppressLint
import android.content.Context
import android.content.res.Configuration
import android.graphics.Canvas
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup.LayoutParams
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

    init {
        setLayerType(LAYER_TYPE_HARDWARE, null)
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        resizePaintBoard()
    }

    private fun resizePaintBoard() {
        if (resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            val portraitWidth = resources.displayMetrics.widthPixels
            val portraitHeight = resources.displayMetrics.heightPixels
            layoutParams = LayoutParams(portraitHeight, portraitWidth)
            x += (portraitWidth - portraitHeight) / 2
        }
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
        if (event.action == MotionEvent.ACTION_DOWN) {
            val paint = palette.getPaint()
            val drawing = palette.drawingTool.createDrawing(paint, this::invalidate)
            drawings.addLast(drawing)
            savedDrawings.clear()
        }
        return drawings.getLastDrawing().onTouchEvent(event)
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
}
