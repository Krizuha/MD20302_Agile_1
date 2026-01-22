package com.example.md20302_agile;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Register extends AppCompatActivity {

    private EditText edtName, edtPhone, edtPassword, edtRePassword;
    private Button btnRegister;
    private com.hbb20.CountryCodePicker ccp;
    private TextView tvSignIn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_register);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        InitUI();
        ccp.registerCarrierNumberEditText(edtPhone);
        InitCode();
    }
    private void InitUI() {
        edtName = findViewById(R.id.edtName);
        edtPhone = findViewById(R.id.edtPhone);
        edtPassword = findViewById(R.id.edtPassword);
        edtRePassword = findViewById(R.id.edtConfirmPassword);
        btnRegister = findViewById(R.id.btnRegister);
        tvSignIn = findViewById(R.id.tvSignIn);
        ccp = findViewById(R.id.ccp);
    }
    private void InitCode() {
        btnRegister.setOnClickListener(v -> {
            String name = edtName.getText().toString();
            String phone = edtPhone.getText().toString();
            String fullPhoneNumber = ccp.getFullNumberWithPlus();
            String rawPhone = edtPhone.getText().toString().trim();
            String password = edtPassword.getText().toString();
            String rePassword = edtRePassword.getText().toString();



            if (name.isEmpty() || phone.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                return;
            }

            if (rawPhone.isEmpty()) {
                edtPhone.setError("Vui lòng nhập số điện thoại");
                return;
            }
            // Thực hiện các bước đăng ký tiếp theo với fullPhoneNumber
            performRegistration(fullPhoneNumber);

            if (!password.equals(rePassword)) {
                Toast.makeText(this, "Mật khẩu nhập lại không khớp", Toast.LENGTH_SHORT).show();
                return;
            }

            if (password.length() < 6) {
                Toast.makeText(this, "Mật khẩu phải ít nhất 6 ký tự", Toast.LENGTH_SHORT).show();
                return;
            }

            // Nếu mọi thứ OK, thực hiện đăng ký (Gửi lên Server hoặc Firebase)
            Toast.makeText(this, "Đăng ký thành công!", Toast.LENGTH_LONG).show();

                // Lấy số điện thoại định dạng đầy đủ (Ví dụ: +84912345678)
                // Ví dụ chuyển hướng sau khi đăng ký thành công
                 Intent intent = new Intent(Register.this, MainActivity.class);
                 startActivity(intent);
                 finish();
            });

    }
        private void performRegistration(String phone) {
            // Logic gửi dữ liệu lên server hoặc chuyển sang màn hình OTP
            Toast.makeText(this, "Số điện thoại của bạn: " + phone, Toast.LENGTH_SHORT).show();
        }
}