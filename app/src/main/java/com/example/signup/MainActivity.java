package com.example.signup;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    SQLiteOpenHelper openHelper;
    SQLiteDatabase db;
    Button breg, blogin;
    EditText textname,  textpass, textemail, textnumber;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        openHelper = new DatabaseHelper(this);

        textname = (EditText)findViewById(R.id.editTextname);

        textpass = (EditText)findViewById(R.id.editpassword);
        textemail = (EditText)findViewById(R.id.editemail);
        textnumber = (EditText)findViewById(R.id.editnumber);
        blogin=(Button)findViewById(R.id.login);
        breg=(Button)findViewById(R.id.register);

        breg.setOnClickListener(new View.OnClickListener() {
            boolean valid = true;

            @Override
            public void onClick(View v) {
                db=openHelper.getWritableDatabase();
                String fname=textname.getText().toString();

                String pass=textpass.getText().toString();
                String email=textemail.getText().toString();
                String phone=textnumber.getText().toString();

                if (fname.isEmpty() || fname.length() < 3) {
                    textname.setError("at least 3 characters");
                    valid = false;
                } else {
                    textname.setError(null);
                }

                if (pass.isEmpty() || pass.length() < 4 || pass.length() > 10) {
                    textpass.setError("between 4 and 10 alphanumeric characters");
                    valid = false;
                } else {
                    textpass.setError(null);
                }
                if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                    textemail.setError("enter a valid email address");
                    valid = false;
                } else {
                    textemail.setError(null);
                }

                if (phone.isEmpty() || phone.length()!=10) {
                    textnumber.setError("Enter Valid Mobile Number");
                    valid = false;
                } else {
                    textnumber.setError(null);
                }





                insertdata(fname,pass,email,phone);
                Toast.makeText(getApplicationContext(), "register successfully",Toast.LENGTH_LONG).show();

            }
        });

        blogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,login.class);
                startActivity(intent);
            }
        });
    }
    public void insertdata(String fname, String pass, String email, String phone){
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseHelper.COL_2, fname);
        contentValues.put(DatabaseHelper.COL_3, pass);
        contentValues.put(DatabaseHelper.COL_4, email);
        contentValues.put(DatabaseHelper.COL_5, phone);
        long id = db.insert(DatabaseHelper.TABLE_NAME, null, contentValues);
    }


}
