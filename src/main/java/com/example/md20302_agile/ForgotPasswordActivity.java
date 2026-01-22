package com.example.md20302_agile;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ForgotPasswordActivity extends AppCompatActivity {

    private LinearLayout layoutStep1, layoutStep2, layoutStep3;
    private EditText etPhoneNumber, etOTP, etNewPass, etConfirmPass;
    private Button btnGetOTP, btnVerifyOTP, btnResetPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_forgot_password);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        InitUI();
        InitCode();
    }

    private void InitUI() {
        layoutStep1 = findViewById(R.id.layoutStep1);
        layoutStep2 = findViewById(R.id.layoutStep2);
        layoutStep3 = findViewById(R.id.layoutStep3);
        etPhoneNumber = findViewById(R.id.etPhoneNumber);
        etOTP = findViewById(R.id.etOTP);
        etNewPass = findViewById(R.id.etNewPassword);
        etConfirmPass = findViewById(R.id.etConfirmNewPassword);
        btnGetOTP = findViewById(R.id.btnGetOTP);
        btnVerifyOTP = findViewById(R.id.btnVerifyOTP);
        btnResetPassword = findViewById(R.id.btnResetPassword);
    }

    private void InitCode() {
        btnGetOTP.setOnClickListener(v -> {
            String phone = etPhoneNumber.getText().toString();
            if (!phone.isEmpty()) {
                showStep(2);
            } else {
                Toast.makeText(this, "Please enter phone number", Toast.LENGTH_SHORT).show();
            }
        });

        btnVerifyOTP.setOnClickListener(v -> {
            String otp = etOTP.getText().toString();
            if (otp.length() == 6) {
                showStep(3);
            } else {
                Toast.makeText(this, "Invalid OTP", Toast.LENGTH_SHORT).show();
            }
        });

        btnResetPassword.setOnClickListener(v -> {
            String p1 = etNewPass.getText().toString();
            String p2 = etConfirmPass.getText().toString();
            if (!p1.isEmpty() && p1.equals(p2)) {
                Toast.makeText(this, "Password changed successfully!", Toast.LENGTH_LONG).show();
                finish(); // Quay lại màn hình đăng nhập
            } else {
                Toast.makeText(this, "Passwords do not match", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void showStep(int step) {
        layoutStep1.setVisibility(step == 1 ? View.VISIBLE : View.GONE);
        layoutStep2.setVisibility(step == 2 ? View.VISIBLE : View.GONE);
        layoutStep3.setVisibility(step == 3 ? View.VISIBLE : View.GONE);
    }
}