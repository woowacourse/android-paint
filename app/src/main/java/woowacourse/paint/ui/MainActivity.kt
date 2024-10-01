package woowacourse.paint.ui

import android.graphics.Color
import android.os.Bundle
import android.widget.SeekBar
import androidx.appcompat.app.AppCompatActivity
import woowacourse.paint.databinding.ActivityMainBinding
import woowacourse.paint.model.DrawingStyle
import woowacourse.paint.ui.adapter.DrawingColorAdapter

class MainActivity : AppCompatActivity(), ActionHandler {
    private lateinit var binding: ActivityMainBinding

    private val adapter: DrawingColorAdapter by lazy { DrawingColorAdapter(this@MainActivity) }

    private lateinit var currentDrawingStyle: DrawingStyle
    private lateinit var drawingView: DrawingView

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
        binding.rvDrawingColor.adapter = adapter
        adapter.submitList(drawingStyles)
    }

    private fun initDrawingView() {
        currentDrawingStyle = DrawingStyle(Color.RED)
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
                    currentDrawingStyle = currentDrawingStyle.copy(strokeWidth = progress.toFloat())
                    addView()
                }

                override fun onStartTrackingTouch(seekBar: SeekBar?) {}

                override fun onStopTrackingTouch(seekBar: SeekBar?) {}
            },
        )
    }

    override fun changeDrawingColor(color: Int) {
        currentDrawingStyle = currentDrawingStyle.copy(color = color)
        addView()
    }

    private fun addView() {
        drawingView =
            DrawingView(
                context = this,
                drawingStyle = currentDrawingStyle,
            )
        binding.constraintPaintBoard.addView(drawingView)
    }

    companion object {
        private val orange = Color.parseColor("#FFA500")
        val drawingStyles =
            listOf(
                DrawingStyle(color = Color.RED),
                DrawingStyle(color = orange),
                DrawingStyle(color = Color.YELLOW),
                DrawingStyle(color = Color.GREEN),
                DrawingStyle(color = Color.BLUE),
            )
    }
}
