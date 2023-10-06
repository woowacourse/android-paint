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
    }
}
