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
    var onContentsChangeListener: OnContentsChangeListener? = null
    private val contents = ContentContainer()
    val drawnPaths: List<Content>
        get() = contents.getDrawnContents()

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

    fun changeDrawnPaths(paths: List<Content>) {
        contents.changeDrawnContents(paths)
        invalidate()
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        contents.drawOnCanvas(canvas)
    }

    interface OnContentsChangeListener {
        fun onContentsChange(contents: List<Content>)
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(event: MotionEvent): Boolean {
        contents.updateContent(event)
        invalidate()
        if (event.action == MotionEvent.ACTION_UP) {
            onContentsChangeListener?.onContentsChange(drawnPaths)
        }
        return true
    }

    companion object {
        @JvmStatic
        @BindingAdapter("paint_board_drawn_contents")
        fun PaintBoard.setDrawnContents(contents: List<Content>) {
            if (drawnPaths == contents) return
            changeDrawnPaths(contents)
        }

        @JvmStatic
        @BindingAdapter("paint_board_drawn_contentsAttrChanged")
        fun PaintBoard.setDrawnContentsInverseBindingListener(inverseBindingListener: InverseBindingListener) {
            onContentsChangeListener = object : OnContentsChangeListener {
                override fun onContentsChange(contents: List<Content>) {
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
            return view.drawnPaths
        }
    }
}
