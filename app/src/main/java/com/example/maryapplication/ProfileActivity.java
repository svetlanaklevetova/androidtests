package com.example.maryapplication;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;

import java.util.List;

import static com.example.maryapplication.SharedPreferencesHelper.USERS_KEY;
import static com.example.maryapplication.SharedPreferencesHelper.USERS_TYPE;

public class ProfileActivity extends AppCompatActivity {

    private ImageView mPhoto;
    private TextView mLogin;
    private TextView mPassword;
    private User user;

    private Gson gson = new Gson();

    private SharedPreferences mHelper;

   // SharedPreferencesHelper mSharedPreferencesHelper;

    public static String USER_KEY = "USER_KEY";
    public static final int REQUEST_CODE = 101;

    private View.OnClickListener mOnPhotoClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            openGallery();
        }
    };

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode == REQUEST_CODE && resultCode == Activity.RESULT_OK && data != null)
        {
            Uri photoUri = data.getData(  );
            mPhoto.setImageURI( photoUri );
            user.setPhotoUri( photoUri );
           // mHelper.edit().putString(USERS_KEY, gson.toJson(users, USERS_TYPE)).apply();

        }
        else {
            super.onActivityResult( requestCode, resultCode, data );
        }

    }

    private void openGallery()
    {
        Intent intent = new Intent(  );
        intent.setType( "image/*" );
        intent.setAction( Intent.ACTION_GET_CONTENT );
        startActivityForResult( intent, REQUEST_CODE );

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.ac_profile );

        mPhoto = findViewById( R.id.photo );
        mLogin = findViewById( R.id.tvEmail );
        mPassword = findViewById( R.id.tvPassword );

        Bundle bundle = getIntent().getExtras();
        user = (User) bundle.get(USER_KEY);
        mLogin.setText(user.getLogin());
        mPassword.setText( user.getPassword() );
        mPhoto.setImageURI(  user.getPhotoUri());

        mPhoto.setOnClickListener( mOnPhotoClickListener );
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate( R.menu.profile_menu, menu );
        return super.onCreateOptionsMenu( menu );
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch (item.getItemId())
        {
            case R.id.actionLogout:
                startActivity( new Intent( this, AuthActivity.class ) );
                finish();
                break;
                default:break;
        }
        return super.onOptionsItemSelected( item );
    }
}
