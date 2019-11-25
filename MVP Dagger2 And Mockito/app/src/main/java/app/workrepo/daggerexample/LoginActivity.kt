package app.workrepo.daggerexample

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_login.*
import javax.inject.Inject

class LoginActivity : AppCompatActivity(), LoginActivityMVP.View {

    @Inject
    lateinit var presenter: LoginActivityMVP.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        (application as App).getComponent().inject(this)

        loginActivity_login_button.setOnClickListener {
            presenter.loginButtonClicked()
        }

    }

    override fun onResume() {
        super.onResume()

        presenter.setView(this)

        presenter.getCurrentUser()

    }

    override fun getFirstName(): String {
        return loginActivity_firstname_editText.text.toString()
    }

    override fun getLastName(): String {
        return loginActivity_surname_editText.text.toString()
    }

    override fun showUserNotAvailable() {

        Toast.makeText(this, "Error The user is npt available", Toast.LENGTH_SHORT).show()

    }

    override fun showInputError() {
        Toast.makeText(this, "Error showInputError", Toast.LENGTH_SHORT).show()
    }

    override fun setFirstName(fname: String) {
        loginActivity_firstname_editText.setText(fname)
    }

    override fun setLastName(lname: String) {
        loginActivity_surname_editText.setText(lname)
    }

    override fun showUserSavedMessage() {
        Toast.makeText(this, "User saved!!!", Toast.LENGTH_SHORT).show()

    }
}
