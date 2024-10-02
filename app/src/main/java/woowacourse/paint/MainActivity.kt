package woowacourse.paint

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import woowacourse.paint.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.rangeSlider.addOnChangeListener { _, width, _ ->
            binding.paintView.changeBrushWidth(width)
        }
        binding.btnRed.setOnClickListener {
            binding.paintView.changePaintColor(ColorType.RED)
        }

        binding.btnOrange.setOnClickListener {
            binding.paintView.changePaintColor(ColorType.ORANGE)
        }

        binding.btnYellow.setOnClickListener {
            binding.paintView.changePaintColor(ColorType.YELLOW)
        }

        binding.btnGreen.setOnClickListener {
            binding.paintView.changePaintColor(ColorType.GREEN)
        }

        binding.btnBlue.setOnClickListener {
            binding.paintView.changePaintColor(ColorType.BLUE)
        }
    }
}
