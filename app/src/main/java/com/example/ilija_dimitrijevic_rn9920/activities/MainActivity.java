package com.example.ilija_dimitrijevic_rn9920.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.splashscreen.SplashScreen;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ilija_dimitrijevic_rn9920.model.User;
import com.example.ilija_dimitrijevic_rn9920.model.UserType;
import com.example.ilija_dimitrijevic_rn9920.R;

import java.util.HashSet;
import java.util.Set;

import timber.log.Timber;

public class MainActivity extends AppCompatActivity {


    private TextView email;
    private TextView username;
    private TextView password;
    private Button loginBtn;
    private ImageView loginIcon;
    private User loggedUser;
    private SharedPreferences sharedPreferences;
    public static final String USERNAME_KEY = "username";
    public static final String PASSWORD_KEY = "password";
    public static final String EMAIL_KEY = "email";
    public static final String TYPE_KEY = "userType";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SplashScreen splashScreen = SplashScreen.installSplashScreen(this);
        splashScreen.setKeepOnScreenCondition(() -> {
            this.isLogged();
            return false;
        });
        Timber.plant(new Timber.DebugTree());
        setContentView(R.layout.activity_main);
        this.init();
    }

    private void init(){
        this.initFields();
        this.initListeners();

    }

    private void initListeners(){
        this.login();
    }

    private void login(){
        this.loginBtn.setOnClickListener(view -> {
            boolean validation = false;
            if(username.getText().toString().equals("") || password.getText().toString().equals("") || email.getText().toString().equals("")){
                Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show();

            }else if(!this.validateLogin()){
                Toast.makeText(this, "Password must contain at least 5 characters", Toast.LENGTH_SHORT).show();

            }else if(username.getText().toString().equals("user")){
                loggedUser = new User(UserType.USER, email.getText().toString(),username.getText().toString(),password.getText().toString());
                this.setLoggedUser(loggedUser);
                this.redirect();
                Toast.makeText(this, "User Logged", Toast.LENGTH_SHORT).show();

            }else if(username.getText().toString().equals("admin") && password.getText().toString().equals("admin")){
                loggedUser = new User(UserType.ADMIN, email.getText().toString(),username.getText().toString(),password.getText().toString());
                this.setLoggedUser(loggedUser);
                this.redirect();
                Toast.makeText(this, "Admin logged", Toast.LENGTH_SHORT).show();

            }else{
                Toast.makeText(this, "Wrong usernam or password", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void isLogged(){
        sharedPreferences = this.getSharedPreferences(this.getPackageName(),MODE_PRIVATE);
        if(sharedPreferences.contains(this.USERNAME_KEY)){
            this.redirect();
        }
    }

   private void redirect(){
       Intent intent = new Intent(MainActivity.this,AppActivity.class);
       this.startActivity(intent);
   }


    private boolean validateLogin(){
        String password = this.password.getText().toString();
        if(password.length()<=4){
            return false;
        }
        return true;
    }

    private void initFields() {
        this.email = this.findViewById(R.id.email);
        this.username = this.findViewById(R.id.username);
        this.password = this.findViewById(R.id.password);
        this.loginBtn = this.findViewById(R.id.loginBtn);
        this.loginIcon = this.findViewById(R.id.loginIcon);
        loginIcon.setImageResource(R.drawable.brightness_icon);
    }

    private void setLoggedUser(User user){
        this.sharedPreferences.edit().clear().apply();
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(USERNAME_KEY,user.getUsername());
        editor.putString(PASSWORD_KEY,user.getPassword());
        editor.putString(EMAIL_KEY,user.getEmail());
        editor.putString(TYPE_KEY,user.getUserType().toString());
        editor.apply();
    }
}