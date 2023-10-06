package woowacourse.paint.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import woowacourse.paint.R
import woowacourse.paint.databinding.ActivityMainBinding
import woowacourse.paint.model.BrushShape

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
        }
    }

    private fun setUpView() = binding.apply {
        rsSlider.addOnChangeListener { _, value, _ -> paintingPaper.brushSize = value }

        btnUndo.setOnClickListener { paintingPaper.undo() }

        btnRedo.setOnClickListener { paintingPaper.redo() }

        btnClear.setOnClickListener { paintingPaper.clear() }

        paintingPaper.onUndoHistoryChangeListener = { btnUndo.isEnabled = it }

        paintingPaper.onRedoHistoryChangeListener = { btnRedo.isEnabled = it }

        rgShapes.setOnCheckedChangeListener { _, checkedId ->
            when (checkedId) {
                R.id.rbPen -> paintingPaper.brushShape = BrushShape.LINE
                R.id.rbRect -> paintingPaper.brushShape = BrushShape.RECT
                R.id.rbCircle -> paintingPaper.brushShape = BrushShape.CIRCLE
                R.id.rbEraser -> paintingPaper.brushShape = BrushShape.ERASER
            }
        }
    }
}
