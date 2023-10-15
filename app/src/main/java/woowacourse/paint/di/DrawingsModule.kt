package woowacourse.paint.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.android.scopes.ActivityRetainedScoped
import woowacourse.paint.ui.glocanvas.drawing.Drawings
import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class DrawingsQualifier

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class SavedDrawingsQualifier

@Module
@InstallIn(ActivityRetainedComponent::class)
class DrawingsModule {
    @ActivityRetainedScoped
    @Provides
    @DrawingsQualifier
    fun provideDrawings(): Drawings = Drawings()

    @ActivityRetainedScoped
    @Provides
    @SavedDrawingsQualifier
    fun provideSavedDrawings(): Drawings = Drawings()
}
