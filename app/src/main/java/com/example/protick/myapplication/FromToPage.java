package com.example.protick.myapplication;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;
import com.example.protick.myapplication.MapsActivity;


public class FromToPage extends Activity {
    public static ArrayList<String>Bus1,Bus2;
    public static HashMap<String,Integer>B1,B2,B3;
    public static int srcv,destv,modv;
    private Spinner spinner1, spinner2,spinner3;
    private Button btnSubmit,btnSubmit1;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bus1=new ArrayList<>();
        Bus2=new ArrayList<>();
        B1=new HashMap<>();
        B2=new HashMap<>();
        B3=new HashMap<>();

        Bus1.add("From");
        Bus2.add("To");
        B1.put("From",0);
        B2.put("To",0);

        for(int i=1;i<=15;i++)
        {
            Bus1.add(MapsActivity.Bus_Stops.get(i));
            Bus2.add(MapsActivity.Bus_Stops.get(i));
            B1.put(MapsActivity.Bus_Stops.get(i),i);
            B2.put(MapsActivity.Bus_Stops.get(i),i);

        }

        setContentView(R.layout.activity_from_to_page);
        addItemsOnSpinner1();
        addItemsOnSpinner2();
        addItemsOnSpinner3();
        addListenerOnButton();
        addListenerOnSpinnerItemSelection();
    }

    // add items into spinner dynamically
    public void addItemsOnSpinner1() {

        spinner1 = (Spinner) findViewById(R.id.spinner);
        List<String> list = new ArrayList<String>();

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, Bus1);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner1.setAdapter(dataAdapter);
    }
    public void addItemsOnSpinner2() {

        spinner2 = (Spinner) findViewById(R.id.spinner2);
        List<String> list = new ArrayList<String>();

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, Bus2);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner2.setAdapter(dataAdapter);
    }

    public void addItemsOnSpinner3() {

        spinner3 = (Spinner) findViewById(R.id.spinner3);
        List<String> list = new ArrayList<String>();
        list.add("Time");
        B3.put("Time",1);
        list.add("Price");
        B3.put("Price",2);
        list.add("Time & Price");
        B3.put("Time & Price",3);
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, list);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner3.setAdapter(dataAdapter);
    }
    public void addListenerOnSpinnerItemSelection() {
        spinner1 = (Spinner) findViewById(R.id.spinner);
        spinner1.setOnItemSelectedListener(new fsgc());
    }

    // get the selected dropdown list value
    public void addListenerOnButton() {

        spinner1 = (Spinner) findViewById(R.id.spinner);
        spinner2 = (Spinner) findViewById(R.id.spinner2);
        spinner3 = (Spinner) findViewById(R.id.spinner3);
        btnSubmit = (Button) findViewById(R.id.btnSubmit);
        btnSubmit1 = (Button) findViewById(R.id.btnSubmit11);

        btnSubmit.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                if(String.valueOf(spinner1.getSelectedItem())=="From" || String.valueOf(spinner2.getSelectedItem())=="To")
                {
                    Toast.makeText(FromToPage.this,
                            "Please select a valid source and destination",
                            Toast.LENGTH_SHORT).show();
                }
                else
                {
                    srcv=B1.get(String.valueOf(spinner1.getSelectedItem()));
                    destv=B2.get(String.valueOf(spinner2.getSelectedItem()));
                    modv=B3.get(String.valueOf(spinner3.getSelectedItem()));
                    Intent in = new Intent(FromToPage.this,MapsActivity2.class);
                    startActivity(in);

                }
            }

        });

        btnSubmit1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {


                Intent in = new Intent(FromToPage.this,about.class);
                startActivity(in);

            }


        });
    }
    public void onBackPressed()
    {
        Intent toy = new Intent(FromToPage.this,MapsActivity.class);
        startActivity(toy);
    }
}