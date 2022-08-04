package com.example.bookcarservicing.Controllers;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bookcarservicing.Interface.CarServicingAPI;
import com.example.bookcarservicing.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Registration extends AppCompatActivity implements View.OnClickListener {

    EditText fullname,email,phonenumber,address,username,password,cnfpassword,usertype;
    Button btn_register;
    String FullName, Email, Phonenumber, Address, Username, Password,ConfirmPassword,Usertype;
    TextView tv_login;
    CarServicingAPI api;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);


        fullname=findViewById(R.id.et_name);
        email=findViewById(R.id.et_email);
        phonenumber=findViewById(R.id.et_phonenumber);
        address=findViewById(R.id.et_address);
        username=findViewById(R.id.et_username);
        password=findViewById(R.id.et_password);
        cnfpassword=findViewById(R.id.et_cnfpassword);
        usertype=findViewById(R.id.et_usertype);


        btn_register=findViewById(R.id.btn_register);
        btn_register.setOnClickListener(this);

        tv_login=findViewById(R.id.tv_login);
        tv_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent LoginIntent= new Intent(Registration.this, Login.class);
                startActivity(LoginIntent);
            }
        });





//                if(password.equals(confirm_password)) {
//                    long val = db.addUser(name, email, phonenumber, address, username, password,spinnr);
//                    if (val > 0) {
//                        Toast.makeText(Registration.this, "You have registered", Toast.LENGTH_SHORT).show();
//                        Intent moveToLogin = new Intent(Registration.this, Login.class);
//                    }
//                }
//                else
//                {
//                    Toast.makeText(Registration.this,"Registeration Error",Toast.LENGTH_SHORT).show();
//                }

    }

    @Override
    public void onClick(View view) {

        if (TextUtils.isEmpty(fullname.getText().toString())) {
            fullname.setError("Please enter your first name");
            fullname.requestFocus();
            return;
        } else if (TextUtils.isEmpty(email.getText().toString())) {
            email.setError("Please enter your last name");
            email.requestFocus();
            return;
        } else if (TextUtils.isEmpty(phonenumber.getText().toString())) {
            phonenumber.setError("Please enter your last name");
            phonenumber.requestFocus();
            return;
        } else if (TextUtils.isEmpty(address.getText().toString())) {
            address.setError("Please enter your last name");
            address.requestFocus();
            return;
        } else if (TextUtils.isEmpty(username.getText().toString())) {
            username.setError("Please enter your last name");
            username.requestFocus();
            return;
        } else if (TextUtils.isEmpty(password.getText().toString())) {
            password.setError("Please enter your last name");
            password.requestFocus();
            return;
        } else if (TextUtils.isEmpty(cnfpassword.getText().toString())) {
            cnfpassword.setError("Please enter your last name");
            cnfpassword.requestFocus();
            return;
        } else if (!password.getText().toString().equals(cnfpassword.getText().toString())) {
            Toast.makeText(this, "Password do not match. Re-enter the password again.", Toast.LENGTH_SHORT).show();
        } else {
            FullName = fullname.getText().toString();
            Email = email.getText().toString();
            Phonenumber = phonenumber.getText().toString();
            Address = address.getText().toString();
            Username = username.getText().toString();
            Password = password.getText().toString();
            ConfirmPassword=cnfpassword.getText().toString();
            register();

            fullname.setText("");
            email.setText("");
            phonenumber.setText("");
            address.setText("");
            username.setText("");
            password.setText("");
        }
    }
        private void createInstance() {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("http://10.0.2.2:6060/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            api= retrofit.create(CarServicingAPI.class);

    }

    private void register() {
        createInstance();
        Usertype="User";
        Call<String> userCall=api.register (
                FullName,
                Email,
                Phonenumber,
                Address,
                Username,
                Password,
                ConfirmPassword,
                Usertype);

                userCall.enqueue(new Callback<String>() {
                                     @Override
                                     public void onResponse(Call<String> call, Response<String> response) {
                                         Toast.makeText(Registration.this,response.body(), Toast.LENGTH_SHORT).show();
                                     }

                                     @Override
                                     public void onFailure(Call<String> call, Throwable t) {
                                         Toast.makeText(Registration.this, "Error"+t.getMessage(), Toast.LENGTH_SHORT).show();

                                     }
                                 });
    }
}

