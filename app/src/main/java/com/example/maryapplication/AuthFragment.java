package com.example.maryapplication;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

public class AuthFragment extends Fragment {

    private AutoCompleteTextView mLogin;
    private EditText mPassword;

    private Button mEnter;
    private Button mRegister;

    private SharedPreferencesHelper mSharedPreferencesHelper;
    private ArrayAdapter<String> mLoginedUsersAdapter;

    public static AuthFragment newInstance() {
        Bundle args = new Bundle();

        AuthFragment fragment = new AuthFragment();
        fragment.setArguments(args);
        return fragment;
    }




    private View.OnClickListener mOnEnterClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if (isEmailValid() && isPasswordValid()) {
                if (mSharedPreferencesHelper.login(new User(
                        mLogin.getText().toString(),
                        mPassword.getText().toString()))) {
                    Intent startProfileIntent =
                            new Intent(getActivity(), ProfileActivity.class);
                    startProfileIntent.putExtra(ProfileActivity.USER_KEY,
                            new User(mLogin.getText().toString(), mPassword.getText().toString()));
                    startActivity(startProfileIntent);
                    getActivity().finish();
                } else {
                    showMessage(R.string.login_fail);
                }
            } else {
                showMessage(R.string.inputError);
            }



        }
    };


    private View.OnClickListener mOnRegisterClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            getFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragmentContainer, RegistrationFragment.newInstance())
                    .addToBackStack( RegistrationFragment.class.getName())
                    .commit();

        }
    };

    private void showMessage(@StringRes int string)
    {
        Toast.makeText( getActivity(), string, Toast.LENGTH_LONG ).show();

    }

    private boolean isEmailValid()
    {
        return !TextUtils.isEmpty( mLogin.getText())
        && Patterns.EMAIL_ADDRESS.matcher( mLogin.getText()).matches();
    }

    private boolean isPasswordValid()
    {
        return !TextUtils.isEmpty( mLogin.getText());
    }

    private View.OnFocusChangeListener mOnLoginFocusChangeListener = new View.OnFocusChangeListener() {
        @Override
        public void onFocusChange(View view, boolean hasFocus) {
            if (hasFocus) {
//                mLogin.showDropDown();
            }
        }
    };

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fr_auth, container, false);

        mSharedPreferencesHelper = new SharedPreferencesHelper( getActivity() );

        mLogin = v.findViewById(R.id.etLogin);
        mPassword = v.findViewById(R.id.etPasssword);
        mEnter = v.findViewById(R.id.buttonEnter);
        mRegister = v.findViewById(R.id.buttonRegister);
        

        mLogin.setOnFocusChangeListener( mOnLoginFocusChangeListener );
        mLoginedUsersAdapter = new ArrayAdapter<>( getActivity(),
                android.R.layout.simple_dropdown_item_1line,
                mSharedPreferencesHelper.getSuccessLogins());

        mLogin.setAdapter( mLoginedUsersAdapter );


        mEnter.setOnClickListener(mOnEnterClickListener);
        mRegister.setOnClickListener(mOnRegisterClickListener);

        return v;
    }
}
