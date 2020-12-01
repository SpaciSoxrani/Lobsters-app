package com.example.lobsters.ui.login;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class LoginViewModel extends ViewModel {

    private MutableLiveData<Boolean> isLoginError;
    private MutableLiveData<Boolean> isPasswordError;

    public LoginViewModel() {
        isLoginError = new MutableLiveData<>();
        isPasswordError = new MutableLiveData<>();
        isLoginError.setValue(false);
        isPasswordError.setValue(false);
    }

    public MutableLiveData<Boolean> getIsLoginError() {
        return isLoginError;
    }

    public void setIsLoginError(boolean isLoginError) {
        this.isLoginError.setValue(isLoginError);
    }

    public MutableLiveData<Boolean> getIsPasswordError() {
        return isPasswordError;
    }

    public void setIsPasswordError(boolean isPasswordError) {
        this.isPasswordError.setValue(isPasswordError);
    }

}
