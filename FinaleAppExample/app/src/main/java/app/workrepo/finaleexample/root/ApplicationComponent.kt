package app.workrepo.finaleexample.root

import app.workrepo.finaleexample.http.ApiModuleForInfo
import app.workrepo.finaleexample.http.ApiModuleForName
import app.workrepo.finaleexample.topmovies.TopMoviesActivity
import app.workrepo.finaleexample.topmovies.TopMoviesModule
import dagger.Component
import javax.inject.Singleton


@Singleton
@Component(modules = [ApiModuleForInfo::class, ApiModuleForName::class, ApplicationModule::class, TopMoviesModule::class])
interface ApplicationComponent {

    fun inject(activity: TopMoviesActivity)

}