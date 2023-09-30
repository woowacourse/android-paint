package woowacourse.paint.data

import woowacourse.paint.model.DrawingTool
import woowacourse.paint.model.PaintColor
import woowacourse.paint.repository.DrawingKitRepository
import javax.inject.Inject

class DefaultDrawingKitRepository @Inject constructor() : DrawingKitRepository {
    private var drawingTool = DrawingTool.PEN
    private val drawingTools = DrawingTool.values().toList()
    private var thickness = 0f
    private var paintColor = PaintColor("#FF0000")
    private val paintColors = listOf(
        PaintColor("#FF0000"), // 빨강
        PaintColor("#FF8C00"), // 주황
        PaintColor("#FFFF00"), // 노랑
        PaintColor("#008000"), // 초록
        PaintColor("#0000FF"), // 파랑
        PaintColor("#000080"), // 남색
        PaintColor("#8B00FF"), // 보라
        PaintColor("#FFC0CB"), // 분홍
        PaintColor("#964B00"), // 갈색
        PaintColor("#808080"), // 회색
        PaintColor("#9DD84B"), // 연두
        PaintColor("#99CCFF"), // 하늘
        PaintColor("#000000"), // 검정
        PaintColor("#FFFFFF"), // 흰색
    )

    override fun getDrawingTool(): DrawingTool {
        return drawingTools.first()
    }

    override fun changeDrawingTool(drawingTool: DrawingTool) {
        this.drawingTool = drawingTool
    }

    override fun getAllDrawingTools(): List<DrawingTool> {
        return drawingTools
    }

    override fun getThickness(): Float {
        return thickness
    }

    override fun changeThickness(thickness: Float) {
        this.thickness = thickness
    }

    override fun getPaintColor(): PaintColor {
        return paintColor
    }

    override fun changePaintColor(paintColor: PaintColor) {
        this.paintColor = paintColor
    }

    override fun getAllPaintColors(): List<PaintColor> = paintColors
}
