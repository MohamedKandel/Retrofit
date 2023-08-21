package com.example.retrofit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class number extends AppCompatActivity {

    EditText num;
    Button fetch;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_number);

        num = findViewById(R.id.txt_num);
        fetch = findViewById(R.id.btn_fetch);
        intent = new Intent(this,MainActivity.class);
        fetch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.putExtra("number",Integer.parseInt(num.getText().toString()));
                startActivity(intent);
            }
        });

    }
}