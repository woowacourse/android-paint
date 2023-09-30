package woowacourse.paint

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import woowacourse.paint.databinding.ActivityMainBinding
import woowacourse.paint.palette.PaletteAdapter

class MainActivity : AppCompatActivity() {
    private lateinit var viewBinding: ActivityMainBinding
    private var paintStrokeWidth = 0f
    private var paintColor = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)
        setListener()
        initRecyclerView()
    }

    private fun initRecyclerView() {
        val colors = listOf(
            getColor(R.color.red),
            getColor(R.color.orange),
            getColor(R.color.yellow),
            getColor(R.color.green),
            getColor(R.color.blue),
        )
        val adapter = PaletteAdapter(colors) { paintColor = colors[it] }
        viewBinding.rvColor.adapter = adapter
    }

    private fun setListener() {
        viewBinding.btnColorChange.setOnClickListener {
            viewBinding.customCanvas.changeColor(paintColor)
        }

        viewBinding.btnThicknessChange.setOnClickListener {
            viewBinding.customCanvas.changeStrokeWidth(paintStrokeWidth)
        }

        viewBinding.rangeSlider.addOnChangeListener { _, value, _ ->
            paintStrokeWidth = value
        }
    }
}
