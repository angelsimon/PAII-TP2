package com.example.tp2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import java.io.Serializable;

public class AgregarContactoDosActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_contacto_dos);

        try {
            Intent i = this.getIntent();
            Contacto reg = (Contacto) i.getSerializableExtra("CONTACTO");
            Toast.makeText(this, reg.toString(), Toast.LENGTH_SHORT).show();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
}