package com.androidprojects.sunilsharma.mysqldemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.rengwuxian.materialedittext.MaterialEditText;

public class MainActivity extends AppCompatActivity {

    MaterialEditText edtUserName;
    MaterialEditText edtPassword;

    FButton btnLogin;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtUserName = (MaterialEditText) findViewById(R.id.edtUserName);
        edtPassword = (MaterialEditText) findViewById(R.id.edtPassword);

        btnLogin = (FButton) findViewById(R.id.btnLogin);
    }

    public void onLogin(View view)
    {
        String username = edtUserName.getText().toString();
        String password = edtPassword.getText().toString();
        String type = "login";

        BackgroundWorker backgroundWorker = new BackgroundWorker(this);
        backgroundWorker.execute(type , username , password);
    }


    public void OpenRegistration(View view)
    {
        Intent registerIntent = new Intent(MainActivity.this , RegistrationActivity.class);
        startActivity(registerIntent);
    }
}
