package com.dimitryroshchin.sqldemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    //references to buttons and other controls on the layout
    androidx.appcompat.widget.AppCompatButton btn_add, btn_viewAll;
    EditText et_name, et_age;
    androidx.appcompat.widget.SwitchCompat sw_active;
    androidx.recyclerview.widget.RecyclerView rv_customerList;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_add = findViewById(R.id.btn_add);
        btn_viewAll = findViewById(R.id.btn_viewAll);
        et_name = findViewById(R.id.et_name);
        et_age = findViewById(R.id.et_age);
        sw_active = findViewById(R.id.sw_active);
        rv_customerList = findViewById(R.id.rv_customerList);

        // button click listeners for the add and view all buttons
        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    CustomerModel customerModel = new CustomerModel(-1, et_name.getText().toString(), Integer.parseInt(et_age.getText().toString()), sw_active.isChecked());
                    Toast.makeText(MainActivity.this, customerModel.toString(), Toast.LENGTH_SHORT).show();

                }
                catch (Exception e) {
                    Toast.makeText(MainActivity.this, "Error Creating Customer", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btn_viewAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "View All Button", Toast.LENGTH_LONG).show();

            }
        });
    }
}