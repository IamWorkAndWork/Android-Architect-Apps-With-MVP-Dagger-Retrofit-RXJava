package app.workrepo.finaleexample.topmovies

import app.workrepo.finaleexample.http.MoreInfoApiService
import app.workrepo.finaleexample.http.MovieApiService
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class TopMoviesModule {


    @Provides
    fun provideTopMoviesActivityPresenter(model: TopMoviesActivityMVP.Model): TopMoviesActivityMVP.Presenter {
        return TopMoviesPresenter(model)
    }

    @Provides
    fun proviewTopMoviesActivityModel(repository: Repository): TopMoviesActivityMVP.Model {
        return TopMoviesModel(repository)
    }

    @Singleton
    @Provides
    fun provideRepo(
        movieApiService: MovieApiService,
        moreInfoApiService: MoreInfoApiService
    ): Repository {
        return TopMoviesRepository(movieApiService, moreInfoApiService)
    }

}