package woowacourse.paint.paintboard.toolbar

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.FragmentManager
import woowacourse.paint.R
import woowacourse.paint.databinding.PaintToolbarBinding
import woowacourse.paint.paintboard.FileNameDialog
import woowacourse.paint.paintboard.common.Brush
import woowacourse.paint.paintboard.common.Shape
import woowacourse.paint.paintboard.toolbar.dialog.EraserToolDialog
import woowacourse.paint.paintboard.toolbar.dialog.LineToolDialog
import woowacourse.paint.paintboard.toolbar.dialog.ShapeToolDialog
import woowacourse.paint.paintboard.toolbar.dialog.ToolDialog

class PaintToolbar(context: Context, attrs: AttributeSet? = null) :
    ConstraintLayout(context, attrs) {
    private var layoutInflater: LayoutInflater? = null
    private lateinit var binding: PaintToolbarBinding
    private lateinit var lineToolDialog: ToolDialog
    private lateinit var eraserToolDialog: ToolDialog
    private lateinit var rectToolDialog: ToolDialog
    private lateinit var ovalToolDialog: ToolDialog
    private lateinit var getBrush: (brush: Brush) -> Unit
    private lateinit var getFileName: (name: String) -> Unit
    private val supportFragmentManager: FragmentManager by lazy {
        (this.context as AppCompatActivity).supportFragmentManager
    }

    private val coordinateY get() = binding.root.bottom + binding.root.height
    private var minWidth: Int = DEFAULT_MAX_WIDTH.toInt()
    private var maxWidth: Int = DEFAULT_MIN_WIDTH.toInt()
    private var brushToolState = BrushToolState(lineSelected = true)
    private lateinit var tools: List<View>

    init {
        initView()
        setAttrs(attrs)
        initTools()
        setClickListeners()
    }

    private fun initView() {
        layoutInflater ?: run {
            layoutInflater = LayoutInflater.from(context)
            binding = PaintToolbarBinding.inflate(layoutInflater!!, this, true)
        }
    }

    private fun setAttrs(attrs: AttributeSet?) {
        context.theme.obtainStyledAttributes(
            attrs,
            R.styleable.PaintToolbar,
            0,
            0,
        ).apply {
            minWidth = getFloat(R.styleable.PaintToolbar_minLineWidth, DEFAULT_MIN_WIDTH).toInt()
            maxWidth = getFloat(R.styleable.PaintToolbar_maxLineWidth, DEFAULT_MAX_WIDTH).toInt()
            recycle()
        }
    }

    private fun initTools() {
        tools = listOf(
            binding.ivPaintToolLine,
            binding.ivPaintToolEraser,
            binding.ivPaintToolRect,
            binding.ivPaintToolOval,
        )
        tools.first().isSelected = true
    }

    fun setUndoAction(action: () -> Unit) {
        binding.ivPaintToolUndo.setOnClickListener { action() }
    }

    fun setRedoAction(action: () -> Unit) {
        binding.ivPaintToolRedo.setOnClickListener { action() }
    }

    fun setClearAction(action: () -> Unit) {
        binding.ivPaintToolClear.setOnClickListener { action() }
    }

    fun setSaveAction(action: () -> Unit) {
        binding.ivPaintToolSave.setOnClickListener { action() }
    }

    fun setBrushListener(action: (brush: Brush) -> Unit) {
        getBrush = action
        createToolDialog()
    }

    fun setFileNameListener(action: (name: String) -> Unit) {
        getFileName = action
    }

    fun getCurrentBrush(tool: Tools): Brush {
        return getToolDialog(tool).brush
    }

    private fun setClickListeners() {
        binding.ivPaintToolLine.setOnClickListener {
            selectTool(Tools.LINE)
        }
        binding.ivPaintToolEraser.setOnClickListener {
            selectTool(Tools.ERASER)
        }
        binding.ivPaintToolRect.setOnClickListener {
            selectTool(Tools.RECT)
        }
        binding.ivPaintToolOval.setOnClickListener {
            selectTool(Tools.OVAL)
        }
        binding.ivPaintToolToImage.setOnClickListener {
            FileNameDialog(getFileName).show(supportFragmentManager, FILE_NAME_DIALOG)
        }
    }

    private fun createToolDialog() {
        lineToolDialog = LineToolDialog(context, minWidth, maxWidth, getBrush)
        eraserToolDialog = EraserToolDialog(context, minWidth, maxWidth, getBrush)
        rectToolDialog = ShapeToolDialog(context, minWidth, maxWidth, Shape.RECT, getBrush)
        ovalToolDialog = ShapeToolDialog(context, minWidth, maxWidth, Shape.OVAL, getBrush)
    }

    private fun selectTool(tool: Tools) {
        togglePaintToolSelection(tool)
        notifyToolBrush(tool)
        toolStateChanged(tool)
    }

    private fun togglePaintToolSelection(tool: Tools) {
        tools.forEach { it.isSelected = false }
        tools[tool.ordinal].isSelected = true
    }

    private fun notifyToolBrush(tool: Tools) {
        getBrush(getToolDialog(tool).brush)
    }

    private fun toolStateChanged(tool: Tools) {
        brushToolState = brushToolState.select(tool).also {
            if (it.isDoubleChecked(tool)) showToolDialog(tool)
        }
    }

    private fun showToolDialog(tool: Tools) {
        getToolDialog(tool).run {
            setCoordinateY(coordinateY)
            show()
        }
    }

    private fun getToolDialog(tool: Tools): ToolDialog {
        return when (tool) {
            Tools.LINE -> lineToolDialog
            Tools.ERASER -> eraserToolDialog
            Tools.RECT -> rectToolDialog
            Tools.OVAL -> ovalToolDialog
        }
    }

    companion object {
        private const val FILE_NAME_DIALOG = "fileNameDialog"
        private const val DEFAULT_MAX_WIDTH = 200f
        private const val DEFAULT_MIN_WIDTH = 0f
    }
}
