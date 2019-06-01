package com.example.maryapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
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
        mPassword.setText( user.getPassword() );

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
