package woowacourse.paint.ui

import android.graphics.Color
import android.os.Bundle
import android.widget.SeekBar
import androidx.appcompat.app.AppCompatActivity
import woowacourse.paint.databinding.ActivityMainBinding
import woowacourse.paint.model.BrushStyle
import woowacourse.paint.model.BrushType
import woowacourse.paint.ui.adapter.BrushColorAdapter
import woowacourse.paint.ui.adapter.BrushTypeAdapter

class MainActivity : AppCompatActivity(), BrushColorActionHandler, BrushTypeActionHandler {
    private lateinit var binding: ActivityMainBinding

    private val drawingColorAdapter: BrushColorAdapter by lazy { BrushColorAdapter(this@MainActivity) }
    private val brushTypeAdapter: BrushTypeAdapter by lazy { BrushTypeAdapter(this@MainActivity) }

    private lateinit var drawingView: DrawingView
    private var brushStyle = BrushStyle()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initView()
        initAdapter()
        initDrawingView()
        initObserve()
    }

    private fun initView() {
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    private fun initAdapter() {
        binding.rvDrawingColor.adapter = drawingColorAdapter
        binding.rvDrawingType.adapter = brushTypeAdapter

        drawingColorAdapter.submitList(brushStyles)
        brushTypeAdapter.submitList(BrushType.entries)
    }

    private fun initDrawingView() {
        addView()
    }

    private fun initObserve() {
        binding.seekbarDrawingStrokeWidth.setOnSeekBarChangeListener(
            object :
                SeekBar.OnSeekBarChangeListener {
                override fun onProgressChanged(
                    seekBar: SeekBar?,
                    progress: Int,
                    fromUser: Boolean,
                ) {
                    brushStyle = brushStyle.copy(strokeWidth = progress.toFloat())
                    addView()
                }

                override fun onStartTrackingTouch(seekBar: SeekBar?) {}

                override fun onStopTrackingTouch(seekBar: SeekBar?) {}
            },
        )
    }

    override fun changeBrushColor(color: Int) {
        brushStyle = brushStyle.copy(color = color)
        addView()
    }

    override fun changeBrushType(brushType: BrushType) {
        brushStyle = brushStyle.copy(brushType = brushType)
        addView()
    }

    private fun addView() {
        drawingView = DrawingView(context = this, brushStyle = brushStyle)
        binding.constraintPaintBoard.addView(drawingView)
    }

    companion object {
        private val orange = Color.parseColor("#FFA500")
        val brushStyles =
            listOf(
                BrushStyle(color = Color.RED),
                BrushStyle(color = orange),
                BrushStyle(color = Color.YELLOW),
                BrushStyle(color = Color.GREEN),
                BrushStyle(color = Color.BLUE),
            )
    }
}
