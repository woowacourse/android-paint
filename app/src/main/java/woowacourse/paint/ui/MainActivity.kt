package woowacourse.paint.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import dagger.hilt.android.AndroidEntryPoint
import woowacourse.paint.R
import woowacourse.paint.databinding.ActivityMainBinding
import woowacourse.paint.model.BrushShape

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    private val vm: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setUpBinding()
        setUpView()
    }

    private fun setUpBinding() {
        setContentView(binding.root)
    }

    private fun setUpView() = with(binding) {
        binding.rvColors.adapter = ColorAdapter { binding.paintingPaper.brushColor = it }

        rsSlider.addOnChangeListener { _, value, _ -> paintingPaper.brushSize = value }

        paintingPaper.onUndoHistoryChangeListener = { btnUndo.isEnabled = it }

        paintingPaper.onRedoHistoryChangeListener = { btnRedo.isEnabled = it }

        paintingPaper.brushHistory = vm.brushHistory

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
