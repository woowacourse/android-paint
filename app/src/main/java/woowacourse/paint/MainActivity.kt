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
            paintView.drawMode = DrawMode.LINE
        }
    }

    private fun initShapes() = with(binding) {
        btnShapeRectangle.setOnClickListener {
            paintView.drawMode = DrawMode.RECT
        }
        btnShapeCircle.setOnClickListener {
            paintView.drawMode = DrawMode.CIRCLE
        }
    }

    private fun initEraser() = with(binding) {
        btnEraser.setOnClickListener {
            paintView.drawMode = DrawMode.ERASER
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
