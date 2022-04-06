package com.example.ilija_dimitrijevic_rn9920.fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.ilija_dimitrijevic_rn9920.activities.MainActivity;
import com.example.ilija_dimitrijevic_rn9920.R;

public class UserProfileFragment extends Fragment {

    TextView tvUsername;
    TextView tvEmail;
    ImageView imgUser;
    Button btnLogout;
    SharedPreferences sharedPreferences;

    public UserProfileFragment() {
        super(R.layout.fragment_user_profile);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init(view);
    }

    private void init(View view){
        initFields(view);
        bind();
        initListeners();
    }

    private void initListeners(){
        btnLogout.setOnClickListener(view -> {
            sharedPreferences.edit().clear().apply();
            getActivity().finish();
        });
    }

    private void bind(){
        sharedPreferences = getContext().getSharedPreferences(getContext().getPackageName(), Context.MODE_PRIVATE);
        String username = sharedPreferences.getString(MainActivity.USERNAME_KEY,null);
        String email = sharedPreferences.getString(MainActivity.EMAIL_KEY,null);
        tvUsername.setText(username);
        tvEmail.setText(email);
    }

    private void initFields(View view){
        tvUsername = view.findViewById(R.id.usernameTextField);
        tvEmail = view.findViewById(R.id.emailTextField);
        imgUser = view.findViewById(R.id.userIcon);
        btnLogout = view.findViewById(R.id.btnLogout);

        imgUser.setImageResource(R.drawable.profile_icon);
    }


}
