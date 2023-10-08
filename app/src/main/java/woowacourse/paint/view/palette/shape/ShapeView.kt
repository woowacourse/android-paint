package woowacourse.paint.view.palette.shape

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup
import android.view.ViewGroup.MarginLayoutParams

class ShapeView : View {
    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)

    private lateinit var paletteShape: PaletteShape
    private val framePaint = Paint().apply {
        isAntiAlias = true
        style = Paint.Style.FILL
        color = Color.LTGRAY
    }

    init {
        val marginLayoutParams = MarginLayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.MATCH_PARENT,
        )
        marginLayoutParams.setMargins(START_MARGIN, 0, 0, 0)
        layoutParams = marginLayoutParams
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        val measuredSize = resolveSizeAndState(MINIMUM_SIZE, heightMeasureSpec, 0)
        setMeasuredDimension(measuredSize, measuredSize)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        drawFrameBackground(canvas)
        paletteShape.draw(canvas, width.toFloat(), height.toFloat())
    }

    private fun drawFrameBackground(canvas: Canvas) {
        canvas.drawRoundRect(
            RectF(0F, 0F, width.toFloat(), height.toFloat()),
            20F,
            20F,
            framePaint,
        )
    }

    companion object {
        private const val MINIMUM_SIZE = 120
        private const val START_MARGIN = 20

        fun create(
            context: Context,
            paletteShape: PaletteShape,
            onShapeSelected: (PaletteShape) -> Unit,
        ): ShapeView = ShapeView(context).also { view ->
            view.setOnClickListener { onShapeSelected(paletteShape) }
            view.paletteShape = paletteShape
        }
    }
}
