package woowacourse.paint

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.isGone
import androidx.databinding.DataBindingUtil
import com.google.android.material.slider.RangeSlider
import woowacourse.paint.adapter.ColorAdapter
import woowacourse.paint.adapter.ColorHandler
import woowacourse.paint.adapter.DiagramAdapter
import woowacourse.paint.adapter.DiagramHandler
import woowacourse.paint.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), ColorHandler, DiagramHandler {
    private val layoutResourceId: Int get() = R.layout.activity_main
    private var _binding: ActivityMainBinding? = null
    private val binding get() = requireNotNull(_binding)

    private lateinit var paintCanvas: PaintCanvasView
    private lateinit var colorAdapter: ColorAdapter
    private lateinit var diagramAdapter: DiagramAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = DataBindingUtil.setContentView(this, layoutResourceId)
        initPaintCanvas()
        initAdapter()
        initWidthSeekBar()
    }

    private fun initPaintCanvas() {
        paintCanvas = binding.paintCanvas
    }

    private fun initAdapter() {
        colorAdapter = ColorAdapter(this)
        binding.rvColor.adapter = colorAdapter
        diagramAdapter = DiagramAdapter(this)
        binding.rvDiagram.adapter = diagramAdapter
    }

    private fun initWidthSeekBar() {
        binding.strokeWidthSlider.apply {
            valueFrom = 0.0f
            valueTo = 100.0f

            addOnChangeListener(
                RangeSlider.OnChangeListener { _, value, _ ->
                    paintCanvas.selectStrokeWidth(value)
                },
            )
        }
    }

    override fun selectColor(selectedColor: PaintColor) {
        val color = ContextCompat.getColor(this, selectedColor.res)
        paintCanvas.selectColorInt(color)
    }

    override fun selectDiagram(selectedDiagram: Diagram) {
        binding.strokeWidthSlider.isGone = (selectedDiagram != Diagram.PEN)
        paintCanvas.selectDiagram(selectedDiagram)
    }
}
