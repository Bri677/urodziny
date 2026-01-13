package com.example.urodziny;

import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ArrayList<String> rzeczyDozrobienia;
    private ListView listView;
    private ArrayAdapter<String> arrayAdapter;

    private Button button;
    private EditText editText;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;

        });
        listView=findViewById(R.id.listview);
        button=findViewById(R.id.button);

        editText=findViewById(R.id.editTextText);

        rzeczyDozrobienia=new ArrayList<>();

        rzeczyDozrobienia.add("Wyjscie do kina");
        rzeczyDozrobienia.add("nauczyc sie");
        rzeczyDozrobienia.add("pomyslec");

        arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,rzeczyDozrobienia);
        listView.setAdapter(arrayAdapter);
        //dodanie do listy

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String rzeczyDoZrobienia=editText.getText().toString();
                rzeczyDozrobienia.add(rzeczyDoZrobienia);
                arrayAdapter.notifyDataSetChanged();
                editText.setText("");
            }
        });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                view.setBackgroundColor(Color.GRAY);
                TextView textView=(TextView) view;
               if(textView.getPaintFlags()==Paint.STRIKE_THRU_TEXT_FLAG){
                   textView.setPaintFlags(Paint.ANTI_ALIAS_FLAG);

               }
               else{
                   textView.setPaintFlags(Paint.STRIKE_THRU_TEXT_FLAG);
               }

            }


        });
        listView.setOnItemLongClickListener(
                new AdapterView.OnItemLongClickListener() {
                    @Override
                    public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                        rzeczyDozrobienia.remove(i);
                        arrayAdapter.notifyDataSetChanged();
                        return false;
                    }
                }
        );
    }
}