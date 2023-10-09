package woowacourse.paint

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import woowacourse.paint.databinding.ActivityMainBinding
import woowacourse.paint.model.DrawMode

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initBinding()
        setContentView(binding.root)

        initPenTool()
        initShapes()
        initEraser()
        initUndo()
        initRedo()
    }

    private fun initBinding() {
        binding = ActivityMainBinding.inflate(layoutInflater)
        binding.lifecycleOwner = this
    }

    private fun initPenTool() {
        val selectedPen = binding.penToolView.selectedPen
        binding.paintView.pen = selectedPen
        binding.btnOpenPenTool.setOnClickListener {
            binding.penToolView.toggleVisibility()
            binding.paintView.drawMode = DrawMode.LINE
        }
    }

    private fun initShapes() {
        binding.btnShapeRectangle.setOnClickListener {
            binding.paintView.drawMode = DrawMode.RECT
        }
        binding.btnShapeCircle.setOnClickListener {
            binding.paintView.drawMode = DrawMode.CIRCLE
        }
    }

    private fun initEraser() {
        binding.btnEraser.setOnClickListener {
            binding.paintView.drawMode = DrawMode.ERASER
        }
    }

    private fun initUndo() = with(binding) {
        btnUndo.setOnClickListener {
            paintView.undo()
        }
    }

    private fun initRedo() = with(binding) {
        btnRedo.setOnClickListener {
            paintView.redo()
        }
    }
}
