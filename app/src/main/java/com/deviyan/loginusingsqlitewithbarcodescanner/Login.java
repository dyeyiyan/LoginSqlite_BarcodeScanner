package com.deviyan.loginusingsqlitewithbarcodescanner;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.KeyEvent;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.deviyan.loginusingsqlitewithbarcodescanner.database.DatabaseHelper;

public class Login extends AppCompatActivity {

    EditText etUsername, etPass;
    Button btnLogin;
    TextView tvTitle;

    DatabaseHelper databaseHelper;
    String name, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etUsername = findViewById(R.id.username);
        etPass = findViewById(R.id.password);
        btnLogin = findViewById(R.id.login);

//        tvTitle = findViewById(R.id.title);
//
//        Cursor  cursor = databaseHelper.getData();
//
//
//        tvTitle.setText(cursor.getString(1));

        databaseHelper = new DatabaseHelper(this);

        btnLogin.setOnClickListener(view -> {
            saveUserAccount();
        });

    }

    private void saveUserAccount() {

        Cursor  cursor = databaseHelper.getData();

        name = etUsername.getText().toString().trim();
        password = etPass.getText().toString().trim();

        if(name.isEmpty() | password.isEmpty()){
            Toast.makeText(Login.this, "Please input all fields", Toast.LENGTH_SHORT).show();
        }
        else {
//                insert data
//                long id = databaseHelper.insertUserAccount(
//                        "" + name,
//                        "" + password
//                );
//                Toast.makeText(Login.this, "User successfully login", Toast.LENGTH_LONG).show();
//                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
//                intent.putExtra("USER", name);
//                startActivity(intent);


//            if(cursor.getCount() == 0){
//                Toast.makeText(Login.this,"No entries Exists",Toast.LENGTH_LONG).show();
//            }

            if(checkLogin(cursor, name, password)){
                Toast.makeText(Login.this, "User successfully login", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                intent.putExtra("USER", name);
                startActivity(intent);
            }
            else{
                Toast.makeText(Login.this, "Wrong username and password", Toast.LENGTH_LONG).show();
            }

        }
        databaseHelper.close();
    }

    private boolean checkLogin(Cursor cursor, String name, String password) {
        while (cursor.moveToNext()){
            if(cursor.getString(0).equals(name)){
                if(cursor.getString(1).equals(password)){
                    return true;
                }
                return false;
            }
        }
        return false;
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode == event.KEYCODE_BACK){
            moveTaskToBack(true);
        }
        return super.onKeyDown(keyCode, event);
    }

}