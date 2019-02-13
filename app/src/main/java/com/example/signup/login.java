package com.example.signup;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class login extends AppCompatActivity {

    SQLiteOpenHelper openHelper;
    SQLiteDatabase db;
    Button blogin;
    Cursor cursor;
    EditText  textpass,textemail;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        openHelper=new DatabaseHelper(this);
        db=openHelper.getReadableDatabase();
        textpass = (EditText)findViewById(R.id.editpassword);
        textemail = (EditText)findViewById(R.id.editemail);
        blogin=(Button)findViewById(R.id.login);
        blogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String  email=textemail.getText().toString();
                String  password=textpass.getText().toString();

                cursor = db.rawQuery("SELECT *FROM " + DatabaseHelper.TABLE_NAME + " WHERE " + DatabaseHelper.COL_4 + "=? AND " + DatabaseHelper.COL_3 + "=?", new String[]{email, password});
                if (cursor != null) {
                    if (cursor.getCount() > 0) {
                        cursor.moveToNext();
                        Toast.makeText(getApplicationContext(), "Login Success", Toast.LENGTH_SHORT).show();

                    } else {
                        Toast.makeText(getApplicationContext(), "Login error", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}
