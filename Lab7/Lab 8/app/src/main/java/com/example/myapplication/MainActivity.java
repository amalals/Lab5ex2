package com.example.myapplication;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    String valID;
    String valName;
    int valSalary;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText id = (EditText) findViewById(R.id.name1);
        final EditText name = (EditText) findViewById(R.id.name2);
        final EditText salary = (EditText) findViewById(R.id.salary);
        final Button add = (Button) findViewById(R.id.add);
        final Button view = (Button) findViewById(R.id.view);
        final Button delete = (Button) findViewById(R.id.delete);
        final DatabaseHelper db = new DatabaseHelper(this);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                valID = id.getText().toString();
                valName = name.getText().toString();
                valSalary = Integer.parseInt(salary.getText().toString());

                db.AddEmployee( valID, valName, valSalary);
                Toast.makeText(MainActivity.this, "Successful Add", Toast.LENGTH_LONG).show();
            }
        });


        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Cursor cur = db.ViewEmployees();
                StringBuffer buffer = new StringBuffer();

                while (cur.moveToNext()){

                    buffer.append("ID: "+ cur.getString(0) + "\n");
                    buffer.append("Name: "+ cur.getString(1) + "\n");
                    buffer.append("Salary: "+ cur.getString(2) + "\n\n");
                }

                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setCancelable(true);
                builder.setTitle("All Employees");
                builder.setMessage(buffer.toString());
                builder.show();

                Toast.makeText(MainActivity.this, "Successful View", Toast.LENGTH_LONG).show();

            }
        });


        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                valID = id.getText().toString();
                db.DeleteEmployees(valID);

                Toast.makeText(MainActivity.this, "Successful Delete", Toast.LENGTH_LONG).show();

            }
        });


    }
}