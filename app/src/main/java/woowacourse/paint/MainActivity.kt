package woowacourse.paint

import android.os.Bundle
import android.widget.Toast
import androidx.annotation.ColorRes
import androidx.appcompat.app.AppCompatActivity
import woowacourse.paint.databinding.ActivityMainBinding
import woowacourse.paint.paintboard.FileNameDialog
import woowacourse.paint.paintboard.PaintMode
import woowacourse.paint.paintboard.pentool.PenToolDialog

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val penToolDialog: PenToolDialog by lazy { createPenToolDialog() }
    private lateinit var bitmapSaver: BitmapSaver

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setView()
        setClickListeners()
        bitmapSaver = BitmapSaver(this)
    }

    private fun setView() {
        binding.ivPen.isSelected = true
    }

    private fun createPenToolDialog(): PenToolDialog {
        val coordinateY = binding.clCanvasTop.bottom + binding.clCanvasTop.height
        return PenToolDialog(coordinateY, ::setBrushColor, ::setBrushWidth)
    }

    private fun setClickListeners() {
        binding.ivPen.setOnClickListener {
            convertTool(PaintMode.Pen)
        }
        binding.ivEraser.setOnClickListener {
            convertTool(PaintMode.Eraser)
        }
        binding.ivRevert.setOnClickListener {
            binding.paintBoard.revertDrawing()
        }
        binding.ivSave.setOnClickListener {
            showFileNameDialog()
        }
    }

    private fun setBrushColor(@ColorRes colorId: Int) {
        binding.paintBoard.setPenColor(colorId)
    }

    private fun setBrushWidth(value: Float) {
        binding.paintBoard.penWidth = value
    }

    private fun convertTool(paintMode: PaintMode) {
        when (paintMode) {
            is PaintMode.Eraser -> {
                binding.paintBoard.setEraserMode()
                convertButtonIsSelected(false)
            }

            is PaintMode.Pen -> {
                binding.paintBoard.setPenMode()
                convertButtonIsSelected(true)
                showPenToolDialog()
            }
        }
    }

    private fun showPenToolDialog() {
        penToolDialog.show(supportFragmentManager, PEN_TOOL_DIALOG)
    }

    private fun convertButtonIsSelected(isPenSelected: Boolean) {
        binding.ivPen.isSelected = isPenSelected
        binding.ivEraser.isSelected = !isPenSelected
    }

    private fun showFileNameDialog() {
        FileNameDialog(::saveImage).show(supportFragmentManager, FILE_NAME_DIALOG)
    }

    private fun saveImage(name: String) {
        val bitmap = binding.paintBoard.getBitmap()
        var message: String = ""
        bitmapSaver.save(bitmap, name)
            .onSuccess { message = getString(R.string.image_save_success) }
            .onFailure { message = getString(R.string.image_save_failure) }
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    companion object {
        private const val PEN_TOOL_DIALOG = "penToolDialog"
        private const val FILE_NAME_DIALOG = "fileNameDialog"
    }
}
