package woowacourse.paint.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.android.scopes.ActivityRetainedScoped
import woowacourse.paint.data.DefaultDrawingKitRepository
import woowacourse.paint.repository.DrawingKitRepository

@Module
@InstallIn(ActivityRetainedComponent::class)
abstract class RepositoryModule {
    @ActivityRetainedScoped
    @Binds
    abstract fun bindDrawingKitRepository(drawingKitRepository: DefaultDrawingKitRepository): DrawingKitRepository
}
