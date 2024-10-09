package woowacourse.paint

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.isGone
import androidx.databinding.DataBindingUtil
import com.google.android.material.slider.RangeSlider
import woowacourse.paint.adapter.ColorAdapter
import woowacourse.paint.adapter.DiagramAdapter
import woowacourse.paint.adapter.PaintCanvasHandler
import woowacourse.paint.databinding.ActivityMainBinding
import woowacourse.paint.paintcanvas.Diagram
import woowacourse.paint.paintcanvas.PaintCanvasView

class MainActivity : AppCompatActivity(), PaintCanvasHandler {
    private val layoutResourceId: Int get() = R.layout.activity_main
    private var _binding: ActivityMainBinding? = null
    private val binding get() = requireNotNull(_binding)

    private lateinit var paintCanvas: PaintCanvasView
    private lateinit var colorAdapter: ColorAdapter
    private lateinit var diagramAdapter: DiagramAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = DataBindingUtil.setContentView(this, layoutResourceId)
        binding.handler = this
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
        paintCanvas.selectColor(color)
    }

    override fun selectDiagram(selectedDiagram: Diagram) {
        binding.strokeWidthSlider.isGone =
            !(selectedDiagram == Diagram.PEN || selectedDiagram == Diagram.ERASER)
        paintCanvas.selectDiagram(selectedDiagram)
    }

    override fun undo() {
        paintCanvas.undo()
    }

    override fun redo() {
        paintCanvas.redo()
    }
}
