package app.workrepo.daggerexample

class MemoryRepository() : LoginRepository {

    private var user: User? = null

    override fun getUser(): User? {
        if (user == null) {
            val user = User(null, "Fox", "Mulder")
            user.id = 0
            return user
        } else {
            return user
        }
    }

    override fun saveUser(user: User) {
        if (user == null) {
            this.user = getUser()
        } else {
            this.user = user
        }
    }


}