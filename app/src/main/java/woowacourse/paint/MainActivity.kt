package woowacourse.paint

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import woowacourse.paint.databinding.ActivityMainBinding
import woowacourse.paint.model.DrawingMode

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
        initClear()
    }

    private fun initBinding() {
        binding = ActivityMainBinding.inflate(layoutInflater)
        binding.lifecycleOwner = this
    }

    private fun initPenTool() = with(binding) {
        paintView.pen = penToolView.selectedPen
        btnOpenPenTool.setOnClickListener {
            penToolView.toggleVisibility()
            paintView.drawingMode = DrawingMode.LINE
        }
    }

    private fun initShapes() = with(binding) {
        btnShapeRectangle.setOnClickListener {
            paintView.drawingMode = DrawingMode.RECT
        }
        btnShapeCircle.setOnClickListener {
            paintView.drawingMode = DrawingMode.OVAL
        }
    }

    private fun initEraser() = with(binding) {
        btnEraser.setOnClickListener {
            paintView.drawingMode = DrawingMode.ERASER
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

    private fun initClear() = with(binding) {
        btnAllCancel.setOnClickListener {
            paintView.clear()
        }
    }
}
