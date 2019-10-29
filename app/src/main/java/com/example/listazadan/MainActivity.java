package com.example.listazadan;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    EditText etValue;
    Button bAdd;
    RecyclerView rvList;

    ArrayList<String> items = new ArrayList<>(0);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etValue = findViewById(R.id.etValue);
        bAdd = findViewById(R.id.bAdd);
        rvList = findViewById(R.id.rvList);

        rvList.setAdapter(new ListAdapter(items));
        rvList.setLayoutManager(new LinearLayoutManager(this));

        bAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String value = etValue.getText().toString().trim();

                if(value.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Podaj wartość", Toast.LENGTH_SHORT).show();
                } else {
                    items.add(0, value);
                    etValue.setText("");

                    rvList.getAdapter().notifyItemInserted(0);

                    rvList.scrollToPosition(0);
                }
            }
        });
    }
}
