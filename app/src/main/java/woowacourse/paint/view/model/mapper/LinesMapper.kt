package woowacourse.paint.view.model.mapper

import woowacourse.paint.domain.Lines
import woowacourse.paint.view.model.mapper.LineMapper.toModel
import woowacourse.paint.view.model.pen.ink.Inks

object LinesMapper {
    fun Lines.toModel(): Inks = value.map {
        it.toModel()
    }.let {
        Inks(it)
    }
}
