package com.cliknfix.user.homeScreen.bottomFragments.presenter;

import com.cliknfix.user.responseModels.SaveUserProfileResponseModel;
import com.cliknfix.user.responseModels.UserProfileResponseModel;

public interface IPUserProfileFragment {

    void getUserProfile(int id, String token);
    void getUserProfileSuccess(UserProfileResponseModel userProfileResponseModel);
    void getUserProfileFailure(String message);
    void saveUserProfile(String name, String phone, String blood_group, String age, String address, String imgUrl, String token);
    void saveUserProfileSuccess(SaveUserProfileResponseModel model);
    void saveUserProfileFailure(String msgg);
}
