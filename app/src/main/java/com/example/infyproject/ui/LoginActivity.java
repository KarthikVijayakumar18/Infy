package com.example.infyproject.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.view.View;

import com.example.infyproject.R;
import com.example.infyproject.databinding.ActivityLoginBinding;
import com.google.android.material.snackbar.Snackbar;

import java.util.Objects;

public class LoginActivity extends AppCompatActivity {

    ActivityLoginBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setData();
    }

    private void setData(){

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            // Create channel to show notifications.
            String channelId  = getString(R.string.default_notification_channel_id);
            String channelName = getString(R.string.default_notification_channel_name);
            NotificationManager notificationManager =
                    getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(new NotificationChannel(channelId,
                    channelName, NotificationManager.IMPORTANCE_LOW));
        }

        binding.register.setText(Html.fromHtml(getString(R.string.register_text)));

        binding.loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(validation()){
                    Intent intent = new Intent(LoginActivity.this, SecondLoginActivity.class);
                    startActivity(intent);
                }
            }
        });
        
    }

    private boolean validation(){
        if(!Objects.requireNonNull(binding.username.getText()).toString().equals("admin")){
            Snackbar.make(binding.constraintLayout,getResources().getString(R.string.username_validation),Snackbar.LENGTH_SHORT).show();
        }else if(!Objects.requireNonNull(binding.password.getText()).toString().equals("admin")){
            Snackbar.make(binding.constraintLayout,getResources().getString(R.string.password_validation),Snackbar.LENGTH_SHORT).show();

        }else{
            return true;
        }
        return false;

    }

}