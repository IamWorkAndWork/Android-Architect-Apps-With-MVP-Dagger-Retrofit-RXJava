package app.workrepo.daggerexample

class LoginModel : LoginActivityMVP.Model {

    private var repository: LoginRepository

    constructor(repository: LoginRepository) {
        this.repository = repository
    }

    override fun createUser(fname: String, lname: String) {
        repository.saveUser(User(null,fname, lname))
    }

    override fun getUser(): User? {
        return repository.getUser()
    }
}