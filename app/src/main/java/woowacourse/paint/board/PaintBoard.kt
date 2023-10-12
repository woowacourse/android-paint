package woowacourse.paint.board

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.PorterDuff
import android.graphics.PorterDuffXfermode
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.ScaleGestureDetector
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.view.ViewTreeObserver
import android.widget.FrameLayout
import androidx.annotation.ColorRes
import androidx.constraintlayout.widget.ConstraintLayout
import woowacourse.paint.R
import woowacourse.paint.board.draw.GraphicObject
import woowacourse.paint.board.draw.GraphicObjectType
import woowacourse.paint.board.draw.Line
import woowacourse.paint.board.draw.Oval
import woowacourse.paint.board.draw.Rectangle
import woowacourse.paint.palette.Palette
import woowacourse.paint.palette.Tool

class PaintBoard(context: Context, attrs: AttributeSet) : ConstraintLayout(context, attrs) {
    private val screenWidth = resources.displayMetrics.widthPixels
    private val screenHeight = resources.displayMetrics.heightPixels
    private val expandedWidth = screenWidth * BOARD_WIDTH_EXPANSION_RATE
    private val expandedHeight = screenHeight * BOARD_HEIGHT_EXPANSION_RATE

    private val graphicObjects: MutableList<GraphicObject> = mutableListOf()

    private var eraseMode: Boolean = false

    private var currentGraphicObjectType: GraphicObjectType = GraphicObjectType.LINE

    private var currentGraphicObject: GraphicObject? = null

    @ColorRes
    private var currentSelectedColor: Int = R.color.black
    private var currentStrokeWidth: Float = 10f

    private lateinit var palette: Palette

    private val twoPointerDragScaleDetector =
        ScaleGestureDetector(
            context,
            TwoPointerDragListener(this, screenWidth, screenHeight).apply {
                setOnScreenMoveListener(::moveStickyPaletteToFollowBoard)
            },
        )

    init {
        addStickyPalette()
        initEraserSetting()
    }

    private fun initEraserSetting() {
        setLayerType(LAYER_TYPE_HARDWARE, null)
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        adjustBoardSize()
        moveScreenToBoardCenter()
    }

    private fun adjustBoardSize() {
        layoutParams = FrameLayout.LayoutParams(expandedWidth, expandedHeight)
    }

    private fun moveScreenToBoardCenter() {
        moveBoardToCenter()
        moveStickyPaletteToBoardCenter()
    }

    private fun moveBoardToCenter() {
        x = (-expandedWidth / 2 + screenWidth / 2).toFloat()
        y = (-expandedHeight / 2 + screenHeight / 2).toFloat()
    }

    private fun moveStickyPaletteToBoardCenter() {
        val palettePreDrawListener: ViewTreeObserver.OnPreDrawListener =
            object : ViewTreeObserver.OnPreDrawListener {
                override fun onPreDraw(): Boolean {
                    palette.viewTreeObserver.removeOnPreDrawListener(this)
                    moveStickyPaletteToFollowBoard()
                    return true
                }
            }

        palette.viewTreeObserver.apply {
            addOnPreDrawListener(palettePreDrawListener)
        }
    }

    private fun moveStickyPaletteToFollowBoard() {
        palette.x = -x
        palette.y = -y + screenHeight - palette.height - PALETTE_BOTTOM_MARGIN_PIXEL
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        when (event.actionMasked) {
            MotionEvent.ACTION_POINTER_DOWN -> multiTouchActionPointerDownEventListener()
        }
        twoPointerDragScaleDetector.onTouchEvent(event)
        graphicObjectEvent(event)
        return true
    }

    private fun multiTouchActionPointerDownEventListener() {
        currentGraphicObject = null
    }

    private fun graphicObjectEvent(event: MotionEvent): Boolean {
        when (event.action) {
            MotionEvent.ACTION_DOWN -> registerCurrentGraphicObject()
            MotionEvent.ACTION_UP -> addCurrentGraphicObjectToGraphicObjects()
        }
        currentGraphicObject?.onTouchEventAction(event)
        return true
    }

    private fun registerCurrentGraphicObject() {
        currentGraphicObject = when (currentGraphicObjectType) {
            GraphicObjectType.LINE -> getLineInstance()
            GraphicObjectType.RECTANGLE -> getRectangleInstance()
            GraphicObjectType.OVAL -> getOvalInstance()
        }
    }

    private fun getLineInstance(): Line {
        val paint: Paint = if (eraseMode) {
            Paint().apply { xfermode = PorterDuffXfermode(PorterDuff.Mode.CLEAR) }
        } else {
            Paint().apply { color = context.getColor(currentSelectedColor) }
        }
        return Line(paint, currentStrokeWidth, ::invalidate)
    }

    private fun getRectangleInstance(): Rectangle = Rectangle(
        Paint().apply { color = context.getColor(currentSelectedColor) },
        ::invalidate,
    )

    private fun getOvalInstance(): Oval = Oval(
        Paint().apply { color = context.getColor(currentSelectedColor) },
        ::invalidate,
    )

    private fun addCurrentGraphicObjectToGraphicObjects() {
        currentGraphicObject?.let { graphicObject ->
            graphicObjects.add(graphicObject)
        }
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        graphicObjects.forEach { it.onDrawAction(canvas) }
        currentGraphicObject?.onDrawAction(canvas)
    }

    private fun addStickyPalette() {
        palette = Palette(context, null)
        palette.layoutParams = FrameLayout.LayoutParams(screenWidth, WRAP_CONTENT)
        palette.setOnSelectedColorIdChangedListener { colorId -> currentSelectedColor = colorId }
        palette.setStrokeWidthChangedListener { strokeWidth -> currentStrokeWidth = strokeWidth }
        palette.setToolChangedListener(::toolChangedListener)
        addView(palette)
    }

    private fun toolChangedListener(tool: Tool) {
        when (tool) {
            Tool.LINE -> lineSelectedListener()
            Tool.OVAL -> ovalSelectedListener()
            Tool.RECTANGLE -> rectangleSelectedListener()
            Tool.ERASE -> eraseSelectedListener()
        }
    }

    private fun eraseSelectedListener() {
        eraseMode = true
        currentGraphicObjectType = GraphicObjectType.LINE
    }

    private fun rectangleSelectedListener() {
        eraseMode = false
        currentGraphicObjectType = GraphicObjectType.RECTANGLE
    }

    private fun ovalSelectedListener() {
        eraseMode = false
        currentGraphicObjectType = GraphicObjectType.OVAL
    }

    private fun lineSelectedListener() {
        eraseMode = false
        currentGraphicObjectType = GraphicObjectType.LINE
    }

    companion object {
        private const val BOARD_HEIGHT_EXPANSION_RATE = 5
        private const val BOARD_WIDTH_EXPANSION_RATE = 15
        private const val PALETTE_BOTTOM_MARGIN_PIXEL = 100
    }
}
