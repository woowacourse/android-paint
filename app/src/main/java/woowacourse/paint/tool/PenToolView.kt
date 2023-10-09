package woowacourse.paint.tool

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.MarginLayoutParamsCompat
import com.google.android.material.slider.Slider
import woowacourse.paint.R
import woowacourse.paint.model.PaletteColor
import woowacourse.paint.model.pen.Pen

class PenToolView(
    context: Context,
    attrs: AttributeSet? = null,
) : ConstraintLayout(context, attrs) {

    private lateinit var slider: Slider
    private lateinit var colorContainer: LinearLayout

    var selectedPen: Pen = Pen.createDefaultPenInstance()
        private set

    init {
        initView()
        initSlider()
        initColorPalette()
    }

    fun toggleVisibility() {
        visibility =
            if (visibility == View.VISIBLE) {
                View.GONE
            } else {
                View.VISIBLE
            }
    }

    private fun initView() {
        val inflateService = Context.LAYOUT_INFLATER_SERVICE
        val layoutInflater = context.getSystemService(inflateService) as LayoutInflater
        val v: View = layoutInflater.inflate(R.layout.view_pen_tool, this, false)
        addView(v)

        slider = findViewById(R.id.slider_pen_tool_pen_width)
        colorContainer = findViewById(R.id.linear_layout_pen_tool_color_container)
    }

    private fun initSlider() {
        slider.value = Pen.DEFAULT_WIDTH
        slider.valueTo = Pen.MAX_WIDTH
        slider.valueFrom = Pen.MIN_WIDTH
        slider.stepSize = Pen.WIDTH_STEP

        slider.addOnChangeListener { slider, value, fromUser ->
            selectedPen.width = value
        }
    }

    private fun initColorPalette() {
        val container = colorContainer
        val paletteColors = PaletteColor.values().toList()

        repeat(paletteColors.size) { index ->
            val view = createColorView(paletteColors, index)
            container.addView(view)
        }
    }

    private fun createColorView(paletteColors: List<PaletteColor>, index: Int): View {
        val paletteColor = paletteColors[index]
        val view = TextView(context)

        val params = MarginLayoutParams(COLOR_VIEW_WIDTH, COLOR_VIEW_HEIGHT)
        if (index != paletteColors.lastIndex) {
            MarginLayoutParamsCompat.setMarginEnd(params, COLOR_VIEW_MARGIN_BETWEEN)
        }

        view.layoutParams = params
        view.setBackgroundColor(Color.parseColor(paletteColor.hexCode))

        view.setOnClickListener {
            selectedPen.paletteColor = paletteColor
        }

        return view
    }

    companion object {
        private const val COLOR_VIEW_WIDTH = 240
        private const val COLOR_VIEW_HEIGHT = 240
        private const val COLOR_VIEW_MARGIN_BETWEEN = 40
    }
}
