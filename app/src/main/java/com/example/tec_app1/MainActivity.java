package com.example.tec_app1;

import androidx.appcompat.app.AppCompatActivity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<String> todoItems;
    ArrayAdapter<String> aa;
    ListView myListView;
    EditText myEditText;
    ArrayList<Integer> selectedItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        todoItems = new ArrayList<String>();
        aa = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, todoItems);
        myListView = (ListView)findViewById(R.id.myListView);
        myEditText = (EditText)findViewById(R.id.myEditText);
        selectedItems = new ArrayList<Integer>();

        Button addBtn = (Button) findViewById(R.id.addBtn);
        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                todoItems.add(todoItems.size(), myEditText.getText().toString());
                myEditText.setText("");
                aa.notifyDataSetChanged();
            }
        });

        myListView.setAdapter(aa);
        myListView.setOnItemClickListener((parent, view, position, id) -> {
            TextView textView = (TextView) view;
            if (selectedItems.contains(position)) {
                selectedItems.remove(selectedItems.indexOf(position));
                textView.setTextColor(Color.BLACK);
            } else {
                selectedItems.add(position);
                textView.setTextColor(Color.GRAY);
            }
        });

        myListView.setOnItemLongClickListener((parent, view, position, id) -> {
            todoItems.remove(position);
            aa.notifyDataSetChanged();
            if (selectedItems.contains(position)) {
                selectedItems.remove(selectedItems.indexOf(position));
            }
            selectedItems.clear();
            return true;
        });
    }
}


