package com.example.endsem;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class Register extends AppCompatActivity {

    private Button registerBtn, gotoLoginBtn;
    private SQLiteOpenHelper openHelper;
    private SQLiteDatabase db;
    private EditText regName, regPhone, regGmail, regPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        // Initialize Database Helper
        openHelper = new DatabaseHelper(this);

        // Bind views
        registerBtn = findViewById(R.id.btnRegLogin);
        gotoLoginBtn = findViewById(R.id.btnGotoLogin);
        regName = findViewById(R.id.etRegName);
        regPhone = findViewById(R.id.etRegPhone);
        regGmail = findViewById(R.id.etRegGmail);
        regPassword = findViewById(R.id.etRegPassword);

        // Set listeners
        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db = openHelper.getWritableDatabase();
                String fname = regName.getText().toString().trim();
                String fPhone = regPhone.getText().toString().trim();
                String fGmail = regGmail.getText().toString().trim();
                String fPassword = regPassword.getText().toString().trim();

                // Check for empty fields
                if (fname.isEmpty() || fPhone.isEmpty() || fGmail.isEmpty() || fPassword.isEmpty()) {
                    Toast.makeText(Register.this, "Please fill all the details", Toast.LENGTH_SHORT).show();
                } else {
                    // Insert data into database
                    long id = insertData(fname, fPhone, fGmail, fPassword);
                    if (id > 0) {
                        Toast.makeText(Register.this, "Registration Successful", Toast.LENGTH_SHORT).show();
                        clearFields();
                    } else {
                        Toast.makeText(Register.this, "Registration Failed", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        gotoLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Redirect to Login page
                startActivity(new Intent(Register.this, MainActivity.class));
                finish();
            }
        });
    }

    // Method to insert data into database
    public long insertData(String fname, String fPhone, String fGmail, String fPassword) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseHelper.COL_2, fname);
        contentValues.put(DatabaseHelper.COL_3, fPhone);
        contentValues.put(DatabaseHelper.COL_4, fGmail);
        contentValues.put(DatabaseHelper.COL_5, fPassword);

        return db.insert(DatabaseHelper.TABLE_NAME, null, contentValues);
    }

    // Method to clear input fields after registration
    private void clearFields() {
        regName.setText("");
        regPhone.setText("");
        regGmail.setText("");
        regPassword.setText("");
    }
}
