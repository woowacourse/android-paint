package woowacourse.paint

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import woowacourse.paint.databinding.ActivityMainBinding
import woowacourse.paint.model.drawingEngine.path.LineDrawingEngine
import woowacourse.paint.model.drawingEngine.path.PathEraserDrawingEngine
import woowacourse.paint.model.drawingEngine.shape.OvalDrawingEngine
import woowacourse.paint.model.drawingEngine.shape.RectangleDrawingEngine

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
        paintView.selectedPen = penToolView.selectedPen
        btnOpenPenTool.setOnClickListener {
            penToolView.toggleVisibility()
            paintView.selectedDrawingEngineInstantiation = LineDrawingEngine.Companion::createInstance
        }
    }

    private fun initShapes() = with(binding) {
        btnShapeRectangle.setOnClickListener {
            paintView.selectedDrawingEngineInstantiation =
                RectangleDrawingEngine.Companion::createInstance
        }
        btnShapeCircle.setOnClickListener {
            paintView.selectedDrawingEngineInstantiation =
                OvalDrawingEngine.Companion::createInstance
        }
    }

    private fun initEraser() = with(binding) {
        btnEraser.setOnClickListener {
            paintView.selectedDrawingEngineInstantiation =
                PathEraserDrawingEngine.Companion::createInstance
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
