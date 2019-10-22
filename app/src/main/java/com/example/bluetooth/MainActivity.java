package com.example.bluetooth;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Set;

public class MainActivity extends AppCompatActivity {

    Button btn1, btn2, btn3, btn4;
    private BluetoothAdapter BA;
    private Set<BluetoothDevice> pairedDevices;
    ListView lst;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn1 = findViewById(R.id.btnOn);
        btn2 = findViewById(R.id.btnOff);
        btn3 = findViewById(R.id.btnList);
        btn4 = findViewById(R.id.btnVisible);
        BA = (BluetoothAdapter.getDefaultAdapter());
        lst = findViewById(R.id.listView);
    }

    public void on(View view){
        if (!BA.isEnabled()){
            Intent turnOn = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(turnOn, 0);
            Toast.makeText(getApplicationContext(), "Menghidupkan Bluetooth", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(getApplicationContext(), "Bluetooth Telah Hidup", Toast.LENGTH_SHORT).show();
        }
    }

    public void off(View view){
        BA.disable();
        Toast.makeText(getApplicationContext(), "Mematikan Bluetooth", Toast.LENGTH_SHORT).show();
    }

    public void visible(View view){
        Intent getVisible = new Intent(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE);
        startActivityForResult(getVisible, 0);
    }

    public void list(View view){
        pairedDevices = BA.getBondedDevices();
        ArrayList list = new ArrayList();
        for (BluetoothDevice bt : pairedDevices)
            list.add(bt.getName());
        Toast.makeText(getApplicationContext(), "Menampilkan Perangkat Bluetooth", Toast.LENGTH_SHORT).show();
        final ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, list);
        lst.setAdapter(adapter);
    }
}
