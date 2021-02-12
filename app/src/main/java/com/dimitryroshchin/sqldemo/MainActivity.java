package com.dimitryroshchin.sqldemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.ListView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    //references to buttons and other controls on the layout
    androidx.appcompat.widget.AppCompatButton btn_add, btn_viewAll;
    EditText et_name, et_age;
    androidx.appcompat.widget.SwitchCompat sw_active;
    ListView rv_customerList;

    ArrayAdapter customerArrayAdapter;
    DataBase dataBaseHelper;


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

        dataBaseHelper = new DataBase(MainActivity.this);
        ShowCustomersOnListView();

        // button click listeners for the add and view all buttons
        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                CustomerModel customerModel;

                try {
                    customerModel  = new CustomerModel(-1, et_name.getText().toString(), Integer.parseInt(et_age.getText().toString()), sw_active.isChecked());
                    Toast.makeText(MainActivity.this, customerModel.toString(), Toast.LENGTH_SHORT).show();

                }
                catch (Exception e) {
                    Toast.makeText(MainActivity.this, "Error Creating Customer", Toast.LENGTH_SHORT).show();
                    customerModel = new CustomerModel(-1, "error", 0, false);
                }

                DataBase dataBase = new DataBase((MainActivity.this));

                boolean success = dataBase.addOne(customerModel);

                Toast.makeText(MainActivity.this, "success=" + success, Toast.LENGTH_SHORT).show();
                ShowCustomersOnListView();
            }
        });

        btn_viewAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DataBase dataBase = new DataBase(MainActivity.this);

                ShowCustomersOnListView();


                //Toast.makeText(MainActivity.this, everyone.toString(), Toast.LENGTH_LONG).show();

            }
        });

        rv_customerList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                CustomerModel clickedCustomer = (CustomerModel) parent.getItemAtPosition(position);
                dataBaseHelper.deleteOne(clickedCustomer);
                ShowCustomersOnListView();
                Toast.makeText(MainActivity.this, "Deleted" + clickedCustomer.toString(), Toast.LENGTH_SHORT).show();
                
            }
        });
    }

    private void ShowCustomersOnListView() {
        customerArrayAdapter = new ArrayAdapter<CustomerModel>(MainActivity.this, android.R.layout.simple_list_item_1, dataBaseHelper.getEveryone());
        rv_customerList.setAdapter(customerArrayAdapter);
    }
}