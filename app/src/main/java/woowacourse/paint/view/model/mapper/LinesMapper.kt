package woowacourse.paint.view.model.mapper

import woowacourse.paint.domain.Lines
import woowacourse.paint.view.model.Inks
import woowacourse.paint.view.model.mapper.LineMapper.toModel

object LinesMapper {
    fun Lines.toModel(): Inks = value.map {
        it.toModel()
    }.let {
        Inks(it)
    }
}
