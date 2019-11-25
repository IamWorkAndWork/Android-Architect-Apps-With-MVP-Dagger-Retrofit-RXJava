package app.workrepo.daggerexample

import android.app.Application
import app.workrepo.daggerexample.di.ApplicationComponent
import app.workrepo.daggerexample.di.ApplicationModule
import app.workrepo.daggerexample.di.DaggerApplicationComponent
import app.workrepo.daggerexample.di.LoginModule

class App : Application() {

    private lateinit var component: ApplicationComponent

    override fun onCreate() {
        super.onCreate()

        component = DaggerApplicationComponent.builder()
            .applicationModule(
                ApplicationModule(
                    this
                )
            )
            .loginModule(LoginModule())
            .build()

    }

    fun getComponent(): ApplicationComponent {
        return component
    }
}