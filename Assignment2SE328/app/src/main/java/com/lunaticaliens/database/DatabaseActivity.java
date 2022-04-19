package com.lunaticaliens.database;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class DatabaseActivity extends AppCompatActivity {

    DatabaseHelper myDB;
    EditText productQuantity, productName;
    TextView productID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_database);

        Button bttnAdd = findViewById(R.id.bttnAdd);
        Button bttnFind = findViewById(R.id.bttnFind);
        Button bttnDelete = findViewById(R.id.bttnDelete);

        myDB = new DatabaseHelper(this);

        productID = findViewById(R.id.productID);
        productQuantity = findViewById(R.id.productQuantity);
        productName = findViewById(R.id.productName);

        bttnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                if (!myDB.addData("FirstITem", "9")) {
//                    Toast.makeText(DatabaseActivity.this, "Insertion Failed", Toast.LENGTH_SHORT).show();
//                }
//                myDB.addData("SecondItem", "13");

                String name = productName.getText().toString().trim();
                String quantity = productQuantity.getText().toString().trim();
                if (!name.isEmpty() && !quantity.isEmpty())
                    myDB.addData(name, quantity);
                else
                    Toast.makeText(DatabaseActivity.this, "Fields cannot be empty", Toast.LENGTH_SHORT).show();
            }
        });
        bttnFind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Cursor cursor = myDB.structuredQuery(1);
                if (cursor != null && cursor.moveToFirst()) {
                    String cID = cursor.getString(0);
                    String cName = cursor.getString(1);
                    String cPrQuantity = cursor.getString(2);
                    Toast.makeText(DatabaseActivity.this, cID + " , " + cName + " , " + cPrQuantity, Toast.LENGTH_SHORT).show();
                } else
                    Toast.makeText(DatabaseActivity.this, "Record not found", Toast.LENGTH_SHORT).show();
            }
        });
        bttnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myDB.deleteQuery(1);
                Toast.makeText(DatabaseActivity.this, "Record Deleted", Toast.LENGTH_SHORT).show();
            }
        });
    }
}