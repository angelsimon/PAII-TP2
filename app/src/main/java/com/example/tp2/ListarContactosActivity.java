package com.example.tp2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.Toast;

import java.util.ArrayList;

public class ListarContactosActivity extends AppCompatActivity {
    ArrayList<Contacto> lista;
    GridView grilla;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_contactos);
        try {
            if (bindData()){
                grilla.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        loadDatosContacto(lista.get(position).getId());
                    }
                });
            }
            else{
                Toast.makeText(this, "No hay contactos.", Toast.LENGTH_SHORT).show();
            }
        }
        catch(Exception e){

        }
    }

    private void loadDatosContacto(Long id) {
        try {
            Intent i = new Intent(getBaseContext(), ListarContactoUnoActivity.class);
            i.putExtra("CONTACTO_ID", id);
            startActivity(i);
        }
        catch(Exception e){

        }
    }

    private boolean bindData() throws Exception {
        try {
            lista = getContactos();
            if (lista.size() == 0){
                return false;
            }
            String[] items = new String[lista.size()];
            int i=0;
            for (Contacto elemento:lista) {
                items[i] = elemento.Listado();
                i++;
            }
            grilla = (GridView) findViewById(R.id.grilla);
            ArrayAdapter adapter = new ArrayAdapter(this, R.layout.support_simple_spinner_dropdown_item, items);
            grilla.setAdapter(adapter);
            return true;
        }
        catch (Exception e){
            throw e;
        }

    }

    private ArrayList<Contacto> getContactos() throws Exception {
        ContactoHelper helper = new ContactoHelper(this);
        ArrayList<Contacto> lista = new ArrayList<Contacto>();
        try {
            lista = helper.getAll();
            return lista;
        } catch (Exception e) {
            throw e;
        }
    }
}