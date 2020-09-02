package com.example.tp2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class AgregarContactoUnoActivity extends AppCompatActivity {
    private EditText txtNombre, txtApellido, txtTelefono, txtEmail, txtDireccion;
    private Spinner cbxTipoTelefono, cbxTipoEmail;
    private DatePicker txtNacimiento;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_contacto_uno);
        // Fecha de nacimiento menor a la actual
        bindForm();
        Calendar today = Calendar.getInstance();
        today.set(Calendar.HOUR_OF_DAY, 0);
        txtNacimiento.setMaxDate(today.getTimeInMillis());
    }
    private void bindForm(){
        /* Bind de los controles */
        txtNombre = (EditText) findViewById(R.id.txtNombre);
        txtApellido = (EditText) findViewById(R.id.txtApellido);
        txtTelefono = (EditText) findViewById(R.id.txtTelefono);
        txtEmail = (EditText) findViewById(R.id.txtEmail);
        txtDireccion = (EditText) findViewById(R.id.txtDireccion);
        cbxTipoTelefono = (Spinner) findViewById(R.id.cbxTipoTelefono);
        cbxTipoEmail = (Spinner) findViewById(R.id.cbxTipoEmails);
        txtNacimiento = (DatePicker) findViewById(R.id.txtNacimiento);
    }
    private Contacto bindData() throws IOException, ClassNotFoundException {
        try {
            Contacto reg = new Contacto();
            ContactoHelper helper = new ContactoHelper(this);
            reg.setId(helper.Autoincrement());
            reg.setNombre(txtNombre.getText().toString());
            reg.setApellido(txtApellido.getText().toString());
            reg.setTelefono(txtTelefono.getText().toString());
            reg.setTipoTel(String.valueOf(cbxTipoTelefono.getSelectedItemId()));
            reg.setEmail(txtEmail.getText().toString());
            reg.setTipoEmail(String.valueOf(cbxTipoEmail.getSelectedItemId()));
            reg.setDireccion(txtDireccion.getText().toString());
            Date d = new Date(txtNacimiento.getYear()-1900, txtNacimiento.getMonth(), txtNacimiento.getDayOfMonth());
            SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
            reg.setFechanac(df.format(d));
            return reg;
        }
        catch (Exception e){
            Toast.makeText(this, "Error al bindear el contacto", Toast.LENGTH_LONG).show();
            throw e;
        }
    }

    public void buttonGuardar_Click(View view){
        try {
            ContactoHelper helper = new ContactoHelper(this);
            if (helper.save(bindData())) {
                Toast.makeText(this, "Contacto guardado correctamente", Toast.LENGTH_SHORT).show();
                finish();
            }
            else{
                Toast.makeText(this, "No se pudo guardar el contacto", Toast.LENGTH_SHORT).show();
            }
        }
        catch (Exception e){
            Toast.makeText(this, "No se pudo guardar el contacto", Toast.LENGTH_SHORT).show();
        }
    }

        private boolean Validar(){
            //que no haya números en Nombre y Apellido
            return true;
        }
}