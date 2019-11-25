package app.workrepo.daggerexample

interface LoginActivityMVP {

    interface View {

        fun getFirstName(): String
        fun getLastName(): String

        fun showUserNotAvailable()
        fun showInputError()

        fun setFirstName(fname: String)
        fun setLastName(lname: String)

        fun showUserSavedMessage()

    }

    interface Presenter {

        fun setView(view: LoginActivityMVP.View)

        fun loginButtonClicked()

        fun getCurrentUser()

        fun saveUser()

    }

    interface Model {

        fun createUser(fname: String, lname: String)
        fun getUser(): User?
    }

}