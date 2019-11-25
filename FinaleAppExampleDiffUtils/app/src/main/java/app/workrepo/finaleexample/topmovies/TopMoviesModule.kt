package app.workrepo.finaleexample.topmovies

import app.workrepo.finaleexample.http.MovieApiService
import dagger.Module
import dagger.Provides

@Module
class TopMoviesModule {

    @Provides
    fun provideTopMoviesActivityPresenter(service: MovieApiService): TopMoviesActivityMVP.Presenter {
        return TopMoviesPresenter(service)
    }


}