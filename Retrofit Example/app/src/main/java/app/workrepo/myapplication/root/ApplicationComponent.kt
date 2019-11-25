package app.workrepo.myapplication.root

import app.workrepo.myapplication.MainActivity
import app.workrepo.myapplication.http.ApiModule
import dagger.Component
import javax.inject.Singleton


@Singleton
@Component(modules = [ApplicationModule::class, ApiModule::class])
interface ApplicationComponent {

    fun inject(activity: MainActivity)

}