package app.workrepo.myapplication.root

import android.app.Application

class App : Application() {

    private lateinit var component: ApplicationComponent

    override fun onCreate() {
        super.onCreate()

        component = DaggerApplicationComponent.create()


    }

    fun getComponent(): ApplicationComponent {
        return component
    }
}