package woowacourse.paint.presentation

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import androidx.annotation.ColorRes
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import woowacourse.paint.R
import woowacourse.paint.databinding.ViewPaintBinding

class PaintView(context: Context, attrs: AttributeSet) :
    ConstraintLayout(context, attrs) {
    private val binding by lazy {
        ViewPaintBinding.inflate(LayoutInflater.from(context), this, true)
    }

    private val lines: MutableList<Line> by lazy { mutableListOf() }
    private var ovalSize: Int = DEFAULT_OVAL_SIZE
    private var currentPath: Path = Path()
    private var currentPaint: Paint = Paint()

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        setWillNotDraw(false)
        initializeView()
    }

    private fun initializeView() {
        isFocusable = true
        isFocusableInTouchMode = true
        setupPaint()
        initializeLayout()
        initializeSelectColorLayout()
        initializeThicknessRangeSlider()
    }

    private fun setupPaint(@ColorRes colorResId: Int = DEFAULT_PAINT_COLOR_RES_ID) {
        val paintColor = ContextCompat.getColor(context, colorResId)
        currentPaint.color = paintColor
    }

    private fun initializeLayout() = with(binding) {
        btnChangeColor.setOnClickListener {
            layoutSelectColor.visibility = layoutSelectColor.reverseVisibility()
        }
        btnChangeThickness.setOnClickListener {
            rangeSliderThickness.visibility = rangeSliderThickness.reverseVisibility()
        }
    }

    private fun View.reverseVisibility() = if (visibility == VISIBLE) GONE else VISIBLE

    private fun initializeSelectColorLayout() = with(binding) {
        ivRed.setOnClickListener {
            setupPaint(R.color.red)
        }
        ivOrange.setOnClickListener {
            setupPaint(R.color.orange)
        }
        ivYellow.setOnClickListener {
            setupPaint(R.color.yellow)
        }
        ivGreen.setOnClickListener {
            setupPaint(R.color.green)
        }
        ivBlue.setOnClickListener {
            setupPaint(R.color.blue)
        }
    }

    private fun initializeThicknessRangeSlider() =
        with(binding.rangeSliderThickness) {
            valueFrom = OVAL_SIZE_MIN
            valueTo = OVAL_SIZE_MAX
            this.addOnChangeListener { _, value, _ ->
                ovalSize = value.toInt()
            }
        }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        lines.forEach { line ->
            canvas.drawPath(line.path, line.paint)
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(event: MotionEvent): Boolean {
        when (event.action) {
            MotionEvent.ACTION_DOWN, MotionEvent.ACTION_MOVE -> {
                addOvalToPath(event.x, event.y)
            }

            MotionEvent.ACTION_UP -> {
                lines.add(Line(currentPath, currentPaint))
                resetLine()
            }

            else -> super.onTouchEvent(event)
        }
        invalidate()
        return true
    }

    private fun addOvalToPath(x: Float, y: Float) {
        currentPath.addOval(
            x,
            y,
            x + ovalSize,
            y + ovalSize,
            Path.Direction.CW,
        )
    }

    private fun resetLine() {
        val currentPaintColor = currentPaint.color
        currentPath = Path()
        currentPaint = Paint().apply { color = currentPaintColor }
    }

    companion object {
        private const val OVAL_SIZE_MIN = 0.0f
        private const val OVAL_SIZE_MAX = 100.0f
        private val DEFAULT_PAINT_COLOR_RES_ID = R.color.red
        private const val DEFAULT_OVAL_SIZE = 50
    }
}
