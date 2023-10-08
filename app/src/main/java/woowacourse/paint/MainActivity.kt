package woowacourse.paint

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import woowacourse.paint.view.CanvasView
import woowacourse.paint.view.PaletteColor
import woowacourse.paint.view.PaletteMode
import woowacourse.paint.view.PaletteShape
import woowacourse.paint.view.PaletteView

class MainActivity : AppCompatActivity() {
    private val canvasView: CanvasView by lazy { findViewById(R.id.canvas_view) }
    private val paletteView: PaletteView by lazy { findViewById(R.id.palette_view) }
    private val paletteModeView: LinearLayout by lazy { findViewById(R.id.palette_mode_view) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupCanvasView()
        setupPaletteView()
        setupPaletteModeView()
    }

    private fun setupCanvasView() {
        canvasView.setPaintThickness(paletteView.selectedPaintThickness)
        canvasView.setPaletteColor(paletteView.selectedPaletteColor)
    }

    private fun setupPaletteView() {
        paletteView.setOnPropertyChangeListener(object : PaletteView.OnPaintPropertyChangeListener {
            override fun onColorSelected(paletteColor: PaletteColor) {
                canvasView.setPaletteColor(paletteColor)
            }

            override fun onShapeSelected(paletteShape: PaletteShape) {
                canvasView.setPaletteShape(paletteShape)
            }

            override fun onStrokeThicknessChanged(paintThickness: Float) {
                canvasView.setPaintThickness(paintThickness)
            }
        })
        paletteView.changePaletteMode(PaletteMode.BRUSH)
    }

    private fun setupPaletteModeView() {
        PaletteMode.values().forEach { paletteMode ->
            val modeButton = createModeButton(paletteMode) { newPaletteMode ->
                paletteView.changePaletteMode(newPaletteMode)
                canvasView.changePaletteMode(newPaletteMode)
            }

            paletteModeView.addView(modeButton)
            modeButton.layoutParams = (modeButton.layoutParams as LinearLayout.LayoutParams).apply {
                weight = 1F
                marginStart = 10
                marginEnd = 10
            }
        }
    }

    private fun createModeButton(
        mode: PaletteMode,
        onClick: (PaletteMode) -> Unit,
    ): AppCompatButton = AppCompatButton(this).apply {
        text = mode.modeName
        textSize = 20F
        setTextColor(getColor(R.color.white))
        setBackgroundColor(getColor(R.color.purple_500))
        setOnClickListener { onClick(mode) }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.undo -> canvasView.undo()
            R.id.redo -> canvasView.redo()
            R.id.reset -> canvasView.reset()
        }
        return true
    }
}
