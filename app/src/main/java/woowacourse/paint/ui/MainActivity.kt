package woowacourse.paint.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import woowacourse.paint.R
import woowacourse.paint.databinding.ActivityMainBinding
import woowacourse.paint.model.Shape

class MainActivity : AppCompatActivity() {

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setUpBinding()
        setUpView()
    }

    private fun setUpBinding() {
        setContentView(binding.root)
        binding.rvColors.adapter = ColorAdapter {
            binding.paintingPaper.color = it
            binding.paintingPaper.drawMode()
        }
    }

    private fun setUpView() {
        binding.rsSlider.addOnChangeListener { _, value, _ ->
            binding.paintingPaper.brushSize = value
        }

        binding.btnEraser.setOnClickListener { binding.paintingPaper.eraseMode() }

        binding.btnUndo.setOnClickListener { binding.paintingPaper.undo() }

        binding.btnRedo.setOnClickListener { binding.paintingPaper.redo() }

        binding.btnClear.setOnClickListener { binding.paintingPaper.clear() }

        binding.paintingPaper.onUndoHistoryChangeListener = {
            binding.btnUndo.isEnabled = it
        }

        binding.paintingPaper.onRedoHistoryChangeListener = {
            binding.btnRedo.isEnabled = it
        }

        binding.paintingPaper.onEraseModeChangeListener = {
            binding.btnEraser.isSelected = it
        }

        binding.rgShapes.setOnCheckedChangeListener { _, checkedId ->
            when (checkedId) {
                R.id.rbPen -> binding.paintingPaper.shape = Shape.LINE
                R.id.rbRect -> binding.paintingPaper.shape = Shape.RECT
                R.id.rbCircle -> binding.paintingPaper.shape = Shape.CIRCLE
            }
        }
    }
}
