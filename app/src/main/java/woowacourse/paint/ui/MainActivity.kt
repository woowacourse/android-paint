package woowacourse.paint.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import woowacourse.paint.R
import woowacourse.paint.databinding.ActivityMainBinding
import woowacourse.paint.ui.brushtype.Circle
import woowacourse.paint.ui.brushtype.Eraser
import woowacourse.paint.ui.brushtype.Line
import woowacourse.paint.ui.brushtype.Rectangle
import woowacourse.paint.ui.recyclerview.PaintAdapter

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setRangeSlider()
        setPalette()
        setOnClickListener()
    }

    private fun setRangeSlider() {
        binding.rsStrokeController.addOnChangeListener { _, value, _ ->
            binding.cvCanvas.setMyStrokeWidth(value)
        }
    }

    private fun setPalette() {
        binding.rvPalette.apply {
            adapter = PaintAdapter(
                listOf(
                    getColor(R.color.red),
                    getColor(R.color.orange),
                    getColor(R.color.yellow),
                    getColor(R.color.green),
                    getColor(R.color.blue),
                ),
                binding.cvCanvas::setMyStrokeColor,
            )
            setHasFixedSize(true)
        }
    }

    private fun setOnClickListener() {
        binding.apply {
            ivCircle.setOnClickListener {
                cvCanvas.setupBrush(Circle())
            }
            ivRectangle.setOnClickListener {
                cvCanvas.setupBrush(Rectangle())
            }
            ivPen.setOnClickListener {
                cvCanvas.setupBrush(Line())
            }
            ivEraser.setOnClickListener {
                cvCanvas.setupBrush(Eraser())
            }
            ivReset.setOnClickListener { cvCanvas.resetCanvas() }
        }
    }
}
