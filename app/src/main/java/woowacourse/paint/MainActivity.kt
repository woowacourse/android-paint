package woowacourse.paint

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import woowacourse.paint.view.CanvasView
import woowacourse.paint.view.PaletteColor
import woowacourse.paint.view.PaletteShape
import woowacourse.paint.view.PaletteView

class MainActivity : AppCompatActivity() {
    private val canvasView: CanvasView by lazy { findViewById(R.id.canvas_view) }
    private val paletteView: PaletteView by lazy { findViewById(R.id.palette_view) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupCanvasView()
        setupPaletteView()
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
    }
}
