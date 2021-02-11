package com.dimitryroshchin.sqldemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;

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
    }
}