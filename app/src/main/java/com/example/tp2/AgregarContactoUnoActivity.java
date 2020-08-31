package com.example.tp2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Date;

public class AgregarContactoUnoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_contacto_uno);
        // Fecha de nacimiento menor a la actual
        DatePicker datePicker = (DatePicker) findViewById(R.id.txtNacimiento);
        Calendar today = Calendar.getInstance();
        today.set(Calendar.HOUR_OF_DAY, 0); // same for minutes and seconds
        datePicker.setMaxDate(today.getTimeInMillis());

    }

    public void buttonGuardar_Click(View view){
        Toast.makeText(this, "Me agrada", Toast.LENGTH_SHORT).show();
    }
}