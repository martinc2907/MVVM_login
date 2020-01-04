package com.example.mvvm_login;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.text.TextUtils;
import android.util.Patterns;

public class LoginViewModel extends BaseObservable {

    private User user;
    private String successMessage = "Login was successful";
    private String errorMessage = "Email or password invalid";
    private String toastMessage = null;

    @Bindable
    public String getToastMessage(){
        return toastMessage;
    }

    @Bindable
    public String getUserEmail(){
        return user.getEmail();
    }

    @Bindable
    public String getUserPassword(){
        return user.getPassword();
    }

    public void setUserEmail(String email) {
        user.setEmail(email);
        notifyPropertyChanged(BR.userEmail);
    }

    public void setUserPassword(String password){
        user.setPassword(password);
        notifyPropertyChanged(BR.userPassword);
    }

    public void setToastMessage(String toastMessage) {
        this.toastMessage = toastMessage;
        notifyPropertyChanged(BR.toastMessage);
    }

    public LoginViewModel(){
        user = new User("","");
    }

    public void onLoginClicked(){
        if(isInputDataValid()){
            setToastMessage(successMessage);
        }else{
            setToastMessage(errorMessage);
        }
    }

    public boolean isInputDataValid() {
        return !TextUtils.isEmpty(getUserEmail()) && Patterns.EMAIL_ADDRESS.matcher(getUserEmail()).matches() && getUserPassword().length() > 5;
    }
}
