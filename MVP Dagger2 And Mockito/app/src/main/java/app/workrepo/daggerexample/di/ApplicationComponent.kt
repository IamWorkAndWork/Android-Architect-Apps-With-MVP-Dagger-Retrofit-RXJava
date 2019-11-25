package app.workrepo.daggerexample.di

import app.workrepo.daggerexample.LoginActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ApplicationModule::class, LoginModule::class])
interface ApplicationComponent {

    fun inject(activity: LoginActivity)

}