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
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AuthFragment extends Fragment {

    private EditText mLogin;
    private EditText mPassword;

    private Button mEnter;
    private Button mRegister;

    private SharedPreferencesHelper mSharedPreferencesHelper;

    public static AuthFragment newInstance() {
        Bundle args = new Bundle();

        AuthFragment fragment = new AuthFragment();
        fragment.setArguments(args);
        return fragment;
    }




    private View.OnClickListener mOnEnterClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            boolean isLoginSuccess=false;
            for(User user: mSharedPreferencesHelper.getUsers())
            {
                if(user.getLogin().equalsIgnoreCase( mLogin.getText().toString() )
                && user.getmPassword().equals( mPassword.getText().toString() ))
                {
                    isLoginSuccess = true;
                    if(isEmailValid() && isPasswordValid())
                    {
                        Intent startProfileIntent = new Intent(getActivity(), ProfileActivity.class);
                        startProfileIntent.putExtra(ProfileActivity.USER_KEY, new User (mLogin.getText().toString(),mPassword.getText().toString()) );
                        startActivity(startProfileIntent);

                    }
                    else
                    {
                        showMessage( R.string.inputError );
                    }
                }
                break;
            }

            if(!isLoginSuccess)
            {
                showMessage( R.string.login_register_error );
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

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fr_auth, container, false);

        mSharedPreferencesHelper = new SharedPreferencesHelper( getActivity() );

        mLogin = v.findViewById(R.id.etLogin);
        mPassword = v.findViewById(R.id.etPasssword);
        mEnter = v.findViewById(R.id.buttonEnter);
        mRegister = v.findViewById(R.id.buttonRegister);

        mEnter.setOnClickListener(mOnEnterClickListener);
        mRegister.setOnClickListener(mOnRegisterClickListener);

        return v;
    }
}
