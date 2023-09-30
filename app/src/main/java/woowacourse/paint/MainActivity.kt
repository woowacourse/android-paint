package woowacourse.paint

import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import woowacourse.paint.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupBinding()
        setUpView()
    }

    private fun setupBinding() {
        setContentView(binding.root)
        binding.paintingPaper

        binding.rvColors.adapter = ColorAdapter { binding.paintingPaper.color = it }
    }

    private fun setUpView() {
        binding.paintingPaper.color = Color.RED

        binding.rsSlider.addOnChangeListener { _, value, _ ->
            binding.paintingPaper.brushSize = value * 100
        }

        binding.btnClear.setOnClickListener {
            binding.paintingPaper.clear()
        }

        binding.btnUndo.setOnClickListener {
            binding.paintingPaper.undo()
        }
    }
}
