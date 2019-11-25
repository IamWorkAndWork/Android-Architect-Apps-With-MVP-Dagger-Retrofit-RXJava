package app.workrepo.daggerexample

import androidx.annotation.Nullable

class LoginActivityPresenter(private var model: LoginActivityMVP.Model) :
    LoginActivityMVP.Presenter {


    @Nullable
    private lateinit var view: LoginActivityMVP.View


    override fun setView(view: LoginActivityMVP.View) {
        this.view = view
    }

    override fun loginButtonClicked() {

        if (view != null) {
            if (view.getFirstName().trim().equals("") || view.getLastName().trim().equals("")) {
                view.showInputError()
            } else {
                model.createUser(view.getFirstName(), view.getLastName())
                view.showUserSavedMessage()
            }
        }

    }

    override fun getCurrentUser() {

        var user: User? = model.getUser()

//        if (user == null) {
//            if (view != null) {
//                view.showUserNotAvailable()
//            }
//        } else {
//            if (view != null) {
//                view.setFirstName(user.firstName)
//                view.setLastName(user.Lastname)
//            }
//        }

        if (user != null) {
            if (view != null) {
                view.setFirstName(user.firstName)
                view.setLastName(user.Lastname)
            }
        } else {
            if (view != null) {
                view.showUserNotAvailable()
            }
        }


    }


    override fun saveUser() {
        if (view != null) {
            if (view.getFirstName().trim().equals("") || view.getLastName().equals("")) {
                view.showInputError()
            } else {
                model.createUser(view.getFirstName(), view.getLastName())
                view.showUserSavedMessage()
            }
        }

    }
}