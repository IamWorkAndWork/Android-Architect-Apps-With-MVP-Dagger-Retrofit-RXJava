package app.workrepo.daggerexample

import org.junit.Before
import org.junit.Test
import org.mockito.Mockito

class PresenterTests {

    lateinit var mockLoginModel: LoginActivityMVP.Model
    lateinit var mockView: LoginActivityMVP.View
    lateinit var presenter: LoginActivityPresenter
    var user: User? = null

    @Before
    fun setup() {
        //espresso ui test

        mockLoginModel = Mockito.mock(LoginActivityMVP.Model::class.java)

        user = User(0, "Fox", "Mulder")

//        Mockito.`when`(mockLoginModel.getUser()).thenReturn(user)

        mockView = Mockito.mock(LoginActivityMVP.View::class.java)

        presenter = LoginActivityPresenter(mockLoginModel)

        presenter.setView(mockView)

    }

//    @Test
//    fun noInteractionWithView() {
//
//        presenter.getCurrentUser()
//
////        Mockito.verifyZeroInteractions(mockView)
//
//
//    }


    @Test
    fun loadTheUserFromTheRepositoryWhenValidUserIsPresent() {

        Mockito.`when`(mockLoginModel.getUser()).thenReturn(user)

//        println("user1 = "+user.toString())

        presenter.getCurrentUser()

        println("user = " + user.toString())

        Mockito.verify(mockLoginModel, Mockito.times(1)).getUser()
        Mockito.verify(mockView, Mockito.times(1)).setFirstName("Fox")
        Mockito.verify(mockView, Mockito.times(1)).setLastName("Mulder")
        Mockito.verify(mockView, Mockito.never()).showUserNotAvailable()


    }


    @Test
    fun loadTheUserFromTheRepositoryWhenValidUserIsNull() {

        Mockito.`when`(mockLoginModel.getUser()).thenReturn(null)

//        println("user1 = "+user.toString())

        presenter.getCurrentUser()

//        println("user = " + user.toString())

        Mockito.verify(mockLoginModel, Mockito.times(1)).getUser()

        Mockito.verify(mockView, Mockito.never()).setFirstName("Fox")
        Mockito.verify(mockView, Mockito.never()).setLastName("Mulder")
        Mockito.verify(mockView, Mockito.times(1)).showUserNotAvailable()


    }

    @Test
    fun shouldCreateErrorMessageIfFieldAreEmpty() {

        Mockito.`when`(mockView.getFirstName()).thenReturn("")

        presenter.saveUser()

        Mockito.verify(mockView, Mockito.times(1)).getFirstName()
        Mockito.verify(mockView, Mockito.never()).getLastName()
        Mockito.verify(mockView, Mockito.times(1)).showInputError()

        Mockito.`when`(mockView.getFirstName()).thenReturn("Data")
        Mockito.`when`(mockView.getLastName()).thenReturn("")

        presenter.saveUser()

        Mockito.verify(mockView, Mockito.times(2)).getFirstName()
        Mockito.verify(mockView, Mockito.times(1)).getLastName()
        Mockito.verify(mockView, Mockito.times(2)).showInputError()

    }

    @Test
    fun shouldBeAbleToSaveAvalidUser() {

        Mockito.`when`(mockView.getFirstName()).thenReturn("Dana")
        Mockito.`when`(mockView.getLastName()).thenReturn("Scully")

        presenter.saveUser()

        Mockito.verify(mockView, Mockito.times(2)).getFirstName()
        Mockito.verify(mockView, Mockito.times(2)).getLastName()

        Mockito.verify(mockLoginModel, Mockito.times(1)).createUser("Dana", "Scully")

        Mockito.verify(mockView, Mockito.times(1)).showUserSavedMessage()
    }

}