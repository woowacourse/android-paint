package woowacourse.paint

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import woowacourse.paint.brush.Brush
import woowacourse.paint.brush.ColorPalette
import woowacourse.paint.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private lateinit var activityViewBinding: ActivityMainBinding
    private var brush: Brush = Brush()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityViewBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityViewBinding.root)
        initPalettes()
        initListener()
    }

    private fun initPalettes() {
        activityViewBinding.customCanvas.changeColor(brush)
        activityViewBinding.rvColor.adapter = ColorPaletteAdapter(
            colorPalettes = ColorPalette.entries,
        ) { color ->
            brush = brush.changeColor(color)
            activityViewBinding.customCanvas.changeColor(brush)
        }
    }

    private fun initListener() {
        activityViewBinding.rangeSlider.addOnChangeListener { _, width, _ ->
            brush = brush.changeWidth(width)
            activityViewBinding.customCanvas.changeLineWidth(brush)
        }
    }
}
