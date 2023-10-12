package woowacourse.paint.customView

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import androidx.annotation.ColorInt
import androidx.databinding.BindingAdapter
import androidx.databinding.InverseBindingAdapter
import androidx.databinding.InverseBindingListener
import woowacourse.paint.R
import woowacourse.paint.customView.container.ContentContainer
import woowacourse.paint.customView.content.BrushType
import woowacourse.paint.customView.content.Content
import woowacourse.paint.util.getEnum

class PaintBoard @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
) : View(context, attrs) {
    var onDrawnContentsChangeListener: OnDrawnContentsChangeListener? = null
    var onRedoAbleContentsChangeListener: OnRedoAbleContentsChangeListener? = null
    private val contents = ContentContainer()
    val drawnContents: List<Content>
        get() = contents.getDrawnContents()

    val redoAbleContents: List<Content>
        get() = contents.getRedoAbleContents()

    var currentColor: Int
        set(@ColorInt value) {
            contents.paintInfo.currentColor = value
        }
        get() = contents.paintInfo.currentColor

    var minStrokeWidth: Float
        set(value) {
            contents.paintInfo.minStrokeWidth = value
        }
        get() = contents.paintInfo.minStrokeWidth

    var maxStrokeWidth: Float
        set(value) {
            contents.paintInfo.maxStrokeWidth = value
        }
        get() = contents.paintInfo.maxStrokeWidth

    var currentStrokeWidth: Float
        set(value) {
            contents.paintInfo.currentStrokeWidth = value
        }
        get() = contents.paintInfo.currentStrokeWidth

    var brushType: BrushType
        set(value) {
            contents.brushType = value
        }
        get() = contents.brushType

    init {
        if (attrs != null) initAttrs(attrs)
        setLayerType(LAYER_TYPE_HARDWARE, null)
    }

    private fun initAttrs(attrs: AttributeSet) {
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.PaintBoard)
        this.minStrokeWidth = typedArray.getFloat(
            R.styleable.PaintBoard_minStrokeWidth,
            contents.paintInfo.minStrokeWidth,
        )
        this.maxStrokeWidth = typedArray.getFloat(
            R.styleable.PaintBoard_maxStrokeWidth,
            contents.paintInfo.maxStrokeWidth,
        )
        this.currentColor = typedArray.getColor(
            R.styleable.PaintBoard_currentColor,
            contents.paintInfo.currentColor,
        )
        this.currentStrokeWidth = typedArray.getFloat(
            R.styleable.PaintBoard_currentStrokeWidth,
            contents.paintInfo.currentStrokeWidth,
        )
        this.brushType = typedArray.getEnum(R.styleable.PaintBoard_brushType, this.brushType)
        typedArray.recycle()
    }

    fun changeDrawnContents(contents: List<Content>) {
        this.contents.changeDrawnContents(contents)
        invalidate()
        onDrawnContentsChange()
    }

    fun changeRedoAbleContents(contents: List<Content>) {
        this.contents.changeRedoAbleContents(contents)
        onRedoAbleContentsChange()
    }

    fun undo() {
        if (contents.undo()) {
            invalidate()
            onDrawnContentsChange()
            onRedoAbleContentsChange()
        }
    }

    fun redo() {
        if (contents.redo()) {
            invalidate()
            onDrawnContentsChange()
            onRedoAbleContentsChange()
        }
    }

    fun clear() {
        if (contents.clear()) {
            invalidate()
            onDrawnContentsChange()
            onRedoAbleContentsChange()
        }
    }

    private fun onDrawnContentsChange() {
        onDrawnContentsChangeListener?.onDrawnContentsChange(drawnContents)
    }

    private fun onRedoAbleContentsChange() {
        onRedoAbleContentsChangeListener?.onRedoAbleContentsChange(redoAbleContents)
    }

    interface OnDrawnContentsChangeListener {
        fun onDrawnContentsChange(contents: List<Content>)
    }

    interface OnRedoAbleContentsChangeListener {
        fun onRedoAbleContentsChange(contents: List<Content>)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        contents.drawOnCanvas(canvas)
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(event: MotionEvent): Boolean {
        contents.updateContent(event)
        invalidate()
        if (event.action == MotionEvent.ACTION_UP) {
            onDrawnContentsChange()
        }
        return true
    }

    companion object {
        @JvmStatic
        @BindingAdapter("paint_board_drawn_contents")
        fun PaintBoard.setDrawnContents(contents: List<Content>) {
            if (drawnContents == contents) return
            changeDrawnContents(contents)
        }

        @JvmStatic
        @BindingAdapter("paint_board_drawn_contentsAttrChanged")
        fun PaintBoard.setDrawnContentsInverseBindingListener(inverseBindingListener: InverseBindingListener) {
            onDrawnContentsChangeListener = object : OnDrawnContentsChangeListener {
                override fun onDrawnContentsChange(contents: List<Content>) {
                    inverseBindingListener.onChange()
                }
            }
        }

        @InverseBindingAdapter(
            attribute = "paint_board_drawn_contents",
            event = "paint_board_drawn_contentsAttrChanged",
        )
        @JvmStatic
        fun getContent(view: PaintBoard): List<Content> {
            return view.drawnContents
        }

        @JvmStatic
        @BindingAdapter("paint_board_redo_contents")
        fun PaintBoard.setRedoAbleContents(contents: List<Content>) {
            if (redoAbleContents == contents) return
            changeRedoAbleContents(contents)
        }

        @JvmStatic
        @BindingAdapter("paint_board_redo_contentsAttrChanged")
        fun PaintBoard.setRedoAbleContentsInverseBindingListener(inverseBindingListener: InverseBindingListener) {
            onRedoAbleContentsChangeListener = object : OnRedoAbleContentsChangeListener {
                override fun onRedoAbleContentsChange(contents: List<Content>) {
                    inverseBindingListener.onChange()
                }
            }
        }

        @InverseBindingAdapter(
            attribute = "paint_board_redo_contents",
            event = "paint_board_redo_contentsAttrChanged",
        )
        @JvmStatic
        fun getRedoAbleContent(view: PaintBoard): List<Content> {
            return view.redoAbleContents
        }
    }
}
