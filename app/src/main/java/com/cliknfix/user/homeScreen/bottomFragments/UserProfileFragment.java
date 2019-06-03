package com.cliknfix.user.homeScreen.bottomFragments;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.cliknfix.user.R;
import com.cliknfix.user.homeScreen.bottomFragments.presenter.IPUserProfileFragment;
import com.cliknfix.user.homeScreen.bottomFragments.presenter.PUserProfileFragment;
import com.cliknfix.user.responseModels.SaveUserProfileResponseModel;
import com.cliknfix.user.responseModels.UserProfileResponseModel;
import com.cliknfix.user.util.Utility;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;


public class UserProfileFragment extends Fragment implements View.OnClickListener,IUserProfileFragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    @BindView(R.id.iv_edit)
    ImageView ivEdit;
    @BindView(R.id.iv_save)
    ImageView ivSave;
    @BindView(R.id.ll_user_profile)
    LinearLayout llUserProfile;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_username_text)
    TextView tvUserNameText;
    @BindView(R.id.et_username)
    EditText etUserName;
    @BindView(R.id.tv_email_text)
    TextView tvEmailText;
    @BindView(R.id.et_email)
    EditText etEmail;
    @BindView(R.id.tv_phone_text)
    TextView tvPhoneText;
    @BindView(R.id.et_phone)
    EditText etPhone;
    @BindView(R.id.tv_age_text)
    TextView tvAgeText;
    @BindView(R.id.et_age)
    EditText etAge;
    @BindView(R.id.tv_bld_grp_text)
    TextView tvBldGrpText;
    @BindView(R.id.et_bld_grp)
    EditText etBldGrp;
    @BindView(R.id.tv_address_text)
    TextView tvAddressText;
    @BindView(R.id.et_address)
    EditText etAddress;
    @BindView(R.id.profilePic)
    CircleImageView ivProfilePic;

    IPUserProfileFragment ipUserProfileFragment;
    ProgressDialog progressDialog;

    public UserProfileFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment UserProfileFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static UserProfileFragment newInstance(String param1, String param2) {
        UserProfileFragment fragment = new UserProfileFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_user_profile, container, false);
        ButterKnife.bind(this, view);
        ipUserProfileFragment = new PUserProfileFragment(this);
        init();
        return view;
    }

    public void init(){
        tvTitle.setTypeface(Utility.typeFaceForBoldText(getContext()));
        tvUserNameText.setTypeface(Utility.typeFaceForText(getContext()));
        etUserName.setTypeface(Utility.typeFaceForText(getContext()));
        tvEmailText.setTypeface(Utility.typeFaceForText(getContext()));
        etEmail.setTypeface(Utility.typeFaceForText(getContext()));
        tvPhoneText.setTypeface(Utility.typeFaceForText(getContext()));
        etPhone.setTypeface(Utility.typeFaceForText(getContext()));
        tvAgeText.setTypeface(Utility.typeFaceForText(getContext()));
        etAge.setTypeface(Utility.typeFaceForText(getContext()));
        tvBldGrpText.setTypeface(Utility.typeFaceForText(getContext()));
        etBldGrp.setTypeface(Utility.typeFaceForText(getContext()));
        tvAddressText.setTypeface(Utility.typeFaceForText(getContext()));
        etAddress.setTypeface(Utility.typeFaceForText(getContext()));


        progressDialog = Utility.showLoader(getContext());
        ipUserProfileFragment.getUserProfile(Utility.getUserId(),Utility.getToken());

        ivEdit.setOnClickListener(this);
        ivSave.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        int id = v.getId();
        InputMethodManager mgr = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        switch (id){
            case R.id.iv_edit:
                ivEdit.setVisibility(View.GONE);
                ivSave.setVisibility(View.VISIBLE);
                //etUserName.setEnabled(true);
                etUserName.setFocusableInTouchMode(true);
                etEmail.setFocusableInTouchMode(true);
                etPhone.setFocusableInTouchMode(true);
                etAge.setFocusableInTouchMode(true);
                etBldGrp.setFocusableInTouchMode(true);
                etAddress.setFocusableInTouchMode(true);
                etUserName.requestFocus();
                mgr.showSoftInput(etUserName, InputMethodManager.SHOW_IMPLICIT);
                break;
            case R.id.iv_save:
                ivEdit.setVisibility(View.VISIBLE);
                ivSave.setVisibility(View.GONE);
                llUserProfile.clearFocus();
                etUserName.setFocusable(false);
                etEmail.setFocusable(false);
                etPhone.setFocusable(false);
                etAge.setFocusable(false);
                etBldGrp.setFocusable(false);
                etAddress.setFocusable(false);
                mgr.hideSoftInputFromWindow(v.getWindowToken(),0);
                progressDialog = Utility.showLoader(getContext());
                ipUserProfileFragment.saveUserProfile(etUserName.getText().toString().trim(),
                        etPhone.getText().toString().trim(),
                        etBldGrp.getText().toString().trim(),
                        etAge.getText().toString().trim(),
                        etAddress.getText().toString().trim(),
                        null,
                        Utility.getToken());
                break;
        }
    }

    public void getUserProfileSuccessFromPresenter(UserProfileResponseModel userProfileResponseModel) {
        progressDialog.dismiss();
        etUserName.setText(userProfileResponseModel.getData().get(0).getName());
        etEmail.setText(userProfileResponseModel.getData().get(0).getEmail());
        etPhone.setText(userProfileResponseModel.getData().get(0).getPhone());
        etAge.setText(userProfileResponseModel.getData().get(0).getAge());
        etBldGrp.setText(userProfileResponseModel.getData().get(0).getBloodGroup());
        etAddress.setText(userProfileResponseModel.getData().get(0).getAddress());
    }

    public void getUserProfileFailureFromPresenter(String message) {
        progressDialog.dismiss();
        Toast.makeText(getContext(), "" + message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void saveUserProfileSuccessFromPresenter(SaveUserProfileResponseModel model) {
        progressDialog.dismiss();
        etUserName.setText(model.getData().get(0).getName());
        etEmail.setText(model.getData().get(0).getEmail());
        etPhone.setText(model.getData().get(0).getPhone());
        etAge.setText(model.getData().get(0).getAge());
        etBldGrp.setText(model.getData().get(0).getBloodGroup());
        etAddress.setText(model.getData().get(0).getAddress());
        Toast.makeText(getContext(), "" + model.getMessage(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void saveUserProfileFailureFromPresenter(String msgg) {
        progressDialog.dismiss();
        Toast.makeText(getContext(), "" + msgg, Toast.LENGTH_SHORT).show();
    }
}
