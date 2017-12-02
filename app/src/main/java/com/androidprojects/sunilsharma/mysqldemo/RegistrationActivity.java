package com.androidprojects.sunilsharma.mysqldemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.rengwuxian.materialedittext.MaterialEditText;

public class RegistrationActivity extends AppCompatActivity
{

    MaterialEditText edtName;
    MaterialEditText edtSurname;
    MaterialEditText edtage;
    MaterialEditText edtUsername;
    MaterialEditText edtPassword;
    FButton btnReg;


    String str_name;
    String str_surname;
    String str_age;
    String str_username;
    String str_password;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        edtName = (MaterialEditText) findViewById(R.id.edtName);
        edtSurname = (MaterialEditText) findViewById(R.id.edtsurname);
        edtage = (MaterialEditText) findViewById(R.id.edtage);
        edtUsername = (MaterialEditText) findViewById(R.id.edtUsername);
        edtPassword = (MaterialEditText) findViewById(R.id.edtPassword);

        btnReg = (FButton) findViewById(R.id.btnReg);
    }
    public void onRegistration(View view)
    {
        str_name = edtName.getText().toString();
        str_surname = edtSurname.getText().toString();
        str_age = edtage.getText().toString();
        str_username = edtUsername.getText().toString();
        str_password = edtPassword.getText().toString();

        String type = "registration";

        BackgroundWorker backgroundWorker = new BackgroundWorker(this);
        backgroundWorker.execute(type , str_name , str_surname , str_age , str_username , str_password);

    }
}
