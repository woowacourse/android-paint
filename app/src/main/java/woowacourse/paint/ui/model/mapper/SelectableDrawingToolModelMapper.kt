package woowacourse.paint.ui.model.mapper

import woowacourse.paint.model.DrawingTool
import woowacourse.paint.ui.model.SelectableDrawingToolModel

fun DrawingTool.toSelectableDrawingToolModel(isSelected: Boolean): SelectableDrawingToolModel {
    return SelectableDrawingToolModel(this.toDrawingToolModel(), isSelected)
}
