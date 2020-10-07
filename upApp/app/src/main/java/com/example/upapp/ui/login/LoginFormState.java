package com.example.upapp.ui.login;

import android.content.Intent;

import androidx.annotation.Nullable;

/**
 * Data validation state of the login form.
 */
class LoginFormState {
    @Nullable
    private Integer usernameError;
    @Nullable
    private Integer passwordError;
    @Nullable
    private Integer nameError;
    @Nullable
    private Integer lastNameError;
    @Nullable
    private Integer phoneError;

    private boolean isDataValid;

    LoginFormState(@Nullable Integer usernameError, @Nullable Integer passwordError) {
        this.usernameError = usernameError;
        this.passwordError = passwordError;
        this.isDataValid = false;
    }
    LoginFormState(@Nullable Integer nameError, @Nullable Integer lastNameError, @Nullable Integer phoneError, @Nullable Integer usernameError) {
        this.nameError = nameError;
        this.lastNameError = lastNameError;
        this.phoneError = phoneError;
        this.usernameError = usernameError;
        this.isDataValid = false;
    }

    LoginFormState(boolean isDataValid) {
        this.usernameError = null;
        this.passwordError = null;
        this.isDataValid = isDataValid;
    }

    @Nullable
    Integer getUsernameError() {
        return usernameError;
    }

    @Nullable
    Integer getNameError() {
        return nameError;
    }

    @Nullable
    Integer getLastNameError() {
        return lastNameError;
    }

    @Nullable
    Integer getPhoneError() {
        return phoneError;
    }

    @Nullable
    Integer getPasswordError() {
        return passwordError;
    }

    boolean isDataValid() {
        return isDataValid;
    }
}