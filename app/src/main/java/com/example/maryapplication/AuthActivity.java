package com.example.maryapplication;

import android.content.Intent;
import android.os.UserHandle;
import android.support.annotation.StringRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.Pattern;

public class AuthActivity extends AppCompatActivity {

    private EditText mLogin;
    private EditText mPassword;

    private Button mEnter;
    private Button mRegister;




    private View.OnClickListener mOnEnterClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(isEmailValid() && isPasswordValid())
            {
                Intent startProfileIntent = new Intent( AuthActivity.this, ProfileActivity.class);
                startProfileIntent.putExtra(ProfileActivity.USER_KEY, new User (mLogin.getText().toString(),mPassword.getText().toString()) );
                startActivity(startProfileIntent);

            }
            else
            {
                showMessage( R.string.inputError );
            }
        }
    };

    private View.OnClickListener mOnRegisterClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

        }
    };

    private void showMessage(@StringRes int string)
    {
        Toast.makeText( this, string, Toast.LENGTH_LONG ).show();
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.ac_auth );

        mLogin = findViewById( R.id.etLogin );
        mPassword = findViewById( R.id.etPasssword );

        mEnter = findViewById( R.id.buttonEnter );
        mRegister = findViewById( R.id.buttonRegister );

        mEnter.setOnClickListener( mOnEnterClickListener );
        mRegister.setOnClickListener( mOnRegisterClickListener );

    }
}
