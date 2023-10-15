package woowacourse.paint

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import woowacourse.paint.databinding.ActivityPaintBinding
import woowacourse.paint.paintboard.toolbar.Tools

class PaintActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPaintBinding
    private lateinit var bitmapSaver: BitmapSaver
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPaintBinding.inflate(layoutInflater)
        setContentView(binding.root)
        bitmapSaver = BitmapSaver(this)
        setToolbar()
        binding.paintBoard.updateBrush(binding.paintToolbar.getCurrentBrush(Tools.LINE))
    }

    private fun setToolbar() {
        binding.paintToolbar.setRedoAction { binding.paintBoard.redo() }
        binding.paintToolbar.setUndoAction { binding.paintBoard.undo() }
        binding.paintToolbar.setClearAction { binding.paintBoard.clear() }
        binding.paintToolbar.setSaveAction { }
        binding.paintToolbar.setBrushListener { binding.paintBoard.updateBrush(it) }
        binding.paintToolbar.setFileNameListener { saveImage(it) }
    }

    private fun saveImage(name: String) {
        val bitmap = binding.paintBoard.getBitmap()
        var message: String = ""
        bitmapSaver.save(bitmap, name)
            .onSuccess { message = getString(R.string.image_save_success) }
            .onFailure { message = getString(R.string.image_save_failure) }
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}
