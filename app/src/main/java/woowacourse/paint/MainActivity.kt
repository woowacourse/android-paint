package woowacourse.paint

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import woowacourse.paint.adapter.BrushTypeAdapter
import woowacourse.paint.adapter.ColorPaletteAdapter
import woowacourse.paint.brush.BrushType
import woowacourse.paint.brush.ColorPalette
import woowacourse.paint.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var activityViewBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityViewBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityViewBinding.root)
        initPalettes()
        initBrushTypes()
        setViewType(activityViewBinding.rvBrush)
        initListener()
    }

    private fun initPalettes() {
        activityViewBinding.rvColor.adapter =
            ColorPaletteAdapter(
                colorPalettes = ColorPalette.entries,
            ) { color ->
                activityViewBinding.customCanvas.changeColor(color)
            }
    }

    private fun initBrushTypes() {
        activityViewBinding.rvBrush.adapter =
            BrushTypeAdapter(
                brushTypes = BrushType.entries,
            ) { brushType ->
                activityViewBinding.customCanvas.changeBrushType(brushType)
            }
    }

    private fun initListener() {
        activityViewBinding.rangeSlider.addOnChangeListener { _, width, _ ->
            activityViewBinding.customCanvas.changeLineWidth(width)
        }
        activityViewBinding.btnColorChange.setOnClickListener {
            setViewType(activityViewBinding.rvColor)
        }
        activityViewBinding.btnWidthChange.setOnClickListener {
            setViewType(activityViewBinding.rangeSlider)
        }
        activityViewBinding.btnBrushChange.setOnClickListener {
            setViewType(activityViewBinding.rvBrush)
        }
    }

    private fun setViewType(currentView: View) {
        activityViewBinding.rvBrush.visibility = View.GONE
        activityViewBinding.rvColor.visibility = View.GONE
        activityViewBinding.rangeSlider.visibility = View.GONE
        currentView.visibility = View.VISIBLE
    }
}
