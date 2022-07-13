package com.example.infyproject.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;

import com.example.infyproject.databinding.BottomSheetDialogBinding;
import com.example.infyproject.receivers.OTPAutoFillReceiver;
import com.example.infyproject.R;
import com.example.infyproject.databinding.ActivitySecondLoginBinding;
import com.google.android.material.bottomsheet.BottomSheetDialog;

public class SecondLoginActivity extends AppCompatActivity {

    ActivitySecondLoginBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySecondLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setData();
    }

    private void setData(){
        binding.registerText.setText(Html.fromHtml(getString(R.string.register_text)));
        binding.secondLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showBottomSheetDialog();
            }
        });
        requestSmsPermission();
    }

    private void showBottomSheetDialog() {
        //Create bottom sheet dialog
        final BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(this);
        bottomSheetDialog.setContentView(R.layout.bottom_sheet_dialog);
        new OTPAutoFillReceiver().setEditText(bottomSheetDialog.findViewById(R.id.otp));
        bottomSheetDialog.findViewById(R.id.dialog_submit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bottomSheetDialog.dismiss();
            }
        });
        //Open a bottom sheet dialog
        bottomSheetDialog.show();
    }

    private void requestSmsPermission() {
        String smsPermission = Manifest.permission.RECEIVE_SMS;
        int grant = ContextCompat.checkSelfPermission(this,smsPermission);
        //check if read SMS permission is granted or not
        if(grant!= PackageManager.PERMISSION_GRANTED)
        {
            String[] permission_list = new String[1];
            permission_list[0]=smsPermission;
            ActivityCompat.requestPermissions(this,permission_list,1);
        }
    }
}