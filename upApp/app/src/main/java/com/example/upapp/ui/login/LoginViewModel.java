package com.example.upapp.ui.login;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import android.util.Patterns;

import com.example.upapp.data.LoginRepository;
import com.example.upapp.data.Result;
import com.example.upapp.data.model.LoggedInUser;
import com.example.upapp.R;

public class LoginViewModel extends ViewModel {

    private MutableLiveData<LoginFormState> loginFormState = new MutableLiveData<>();
    private MutableLiveData<LoginResult> loginResult = new MutableLiveData<>();
    private MutableLiveData<LoginResult> registerResult = new MutableLiveData<>();
    private LoginRepository loginRepository;

    LoginViewModel(LoginRepository loginRepository) {
        this.loginRepository = loginRepository;
    }

    LiveData<LoginFormState> getLoginFormState() {
        return loginFormState;
    }

    LiveData<LoginResult> getLoginResult() {
        return loginResult;
    }
    /**/
    LiveData<LoginResult> getRegisterResult() {
        return registerResult;
    }

    public void login(String username, String password) {
        // can be launched in a separate asynchronous job
        Result<LoggedInUser> result = loginRepository.login(username, password);

        if (result instanceof Result.Success) {
            LoggedInUser data = ((Result.Success<LoggedInUser>) result).getData();
            loginResult.setValue(new LoginResult(new LoggedInUserView(data.getDisplayName())));
        } else {
            loginResult.setValue(new LoginResult(R.string.login_failed));
        }
    }

    public void loginDataChanged(String username, String password) {
        if (!isUserNameValid(username)) {
            loginFormState.setValue(new LoginFormState(R.string.invalid_username, null));
        } else if (!isPasswordValid(password)) {
            loginFormState.setValue(new LoginFormState(null, R.string.invalid_password));
        } else {
            loginFormState.setValue(new LoginFormState(true));
        }
    }
    public void loginDataChanged(String name, String lastName, String phone, String email) {
         if (!isNameValid(name)) {
            loginFormState.setValue(new LoginFormState(R.string.invalid_name, null));
        } else if (!isLastNameValid(lastName)) {
            loginFormState.setValue(new LoginFormState(R.string.invalid_lastname,null));
        } else if (!isPhoneValid(phone)) {
            loginFormState.setValue(new LoginFormState(R.string.invalid_phone, null));
        } else if (!isUserNameValid(email)) {
            loginFormState.setValue(new LoginFormState(R.string.invalid_username, null));
        } else{
            loginFormState.setValue(new LoginFormState(true));
        }
    }

    // A placeholder username validation check
    private boolean isUserNameValid(String username) {
        if (username == null) {
            return false;
        }
        if (username.contains("@")) {
            return Patterns.EMAIL_ADDRESS.matcher(username).matches();
        } else {
            return !username.trim().isEmpty();
        }
    }

    // A placeholder password validation check
    private boolean isPasswordValid(String password) {
        return password != null && password.trim().length() > 5;
    }
    private boolean isNameValid(String name) {
        return name != null && name.trim().length() > 10;
    }
    private boolean isLastNameValid(String lastName) {
        return lastName != null && lastName.trim().length() > 5;
    }
    private boolean isPhoneValid(String phone) {
        return phone != null && phone.trim().length() == 9;
    }
}