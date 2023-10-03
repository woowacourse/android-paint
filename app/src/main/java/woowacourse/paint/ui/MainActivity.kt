package woowacourse.paint.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import woowacourse.paint.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setUpBinding()
        setUpView()
    }

    private fun setUpBinding() {
        setContentView(binding.root)
        binding.rvColors.adapter = ColorAdapter { binding.paintingPaper.color = it }
    }

    private fun setUpView() {
        binding.rsSlider.addOnChangeListener { _, value, _ ->
            binding.paintingPaper.brushSize = value
        }

        binding.btnUndo.setOnClickListener { binding.paintingPaper.undo() }

        binding.btnRedo.setOnClickListener { binding.paintingPaper.redo() }

        binding.btnClear.setOnClickListener { binding.paintingPaper.clear() }
    }
}
