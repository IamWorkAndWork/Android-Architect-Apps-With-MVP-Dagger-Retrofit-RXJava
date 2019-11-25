package app.workrepo.daggerexample

interface LoginRepository {

    fun getUser(): User?

    fun saveUser(user: User)

}