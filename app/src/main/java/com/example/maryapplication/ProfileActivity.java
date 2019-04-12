package com.example.maryapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class ProfileActivity extends AppCompatActivity {

    private ImageView mPhoto;
    private TextView mLogin;
    private TextView mPassword;

    public static String USER_KEY = "USER_KEY";


    private View.OnClickListener mOnPhotoClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.ac_profile );

        mPhoto = findViewById( R.id.photo );
        mLogin = findViewById( R.id.tvEmail );
        mPassword = findViewById( R.id.tvPassword );

        Bundle bundle = getIntent().getExtras();
        User user = (User) bundle.get(USER_KEY);
        mLogin.setText(user.getLogin());
        mPassword.setText( user.getmPassword() );

        mPhoto.setOnClickListener( mOnPhotoClickListener );
    }
}
