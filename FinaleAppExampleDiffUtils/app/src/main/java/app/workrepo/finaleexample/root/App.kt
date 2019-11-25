package app.workrepo.finaleexample.root

import android.app.Application
import app.workrepo.finaleexample.http.ApiModuleForName

class App : Application() {

    private lateinit var component: ApplicationComponent


    override fun onCreate() {
        super.onCreate()

        component = DaggerApplicationComponent
            .builder()
            .applicationModule(ApplicationModule(this))
//            .apiModuleForName(ApiModuleForName())
            .build()
//            .create()

    }

    fun getComponent(): ApplicationComponent {
        return component
    }
}