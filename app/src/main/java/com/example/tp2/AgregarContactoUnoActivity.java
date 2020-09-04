package com.example.tp2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class AgregarContactoUnoActivity extends AppCompatActivity {
    private static final int RESULT = 0;

    private EditText txtNombre, txtApellido, txtTelefono, txtEmail, txtDireccion;
    private Spinner cbxTipoTelefono, cbxTipoEmail;
    private DatePicker txtNacimiento;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_contacto_uno);
        // Fecha de nacimiento menor a la actual
        bindForm();
        setSpinnerTelefono();
        setSpinnerMail();
        Calendar today = Calendar.getInstance();
        today.set(Calendar.HOUR_OF_DAY, 0);
        txtNacimiento.setMaxDate(today.getTimeInMillis());
        dummyData();
    }

    private void setSpinnerTelefono(){
        ArrayList<String> opciones = new ArrayList<String>();
        opciones.add("Sin etiqueta");
        opciones.add("Casa");
        opciones.add("Trabajo");
        opciones.add("Móvil");
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, opciones);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        cbxTipoTelefono.setAdapter(adapter);
    }
    private void setSpinnerMail(){
        ArrayList<String> opciones = new ArrayList<String>();
        opciones.add("Sin etiqueta");
        opciones.add("Personal");
        opciones.add("Trabajo");
        opciones.add("Otro");
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, opciones);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        cbxTipoEmail.setAdapter(adapter);
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
    private void dummyData(){
        txtNombre.setText("Vaquita");
        txtApellido.setText("Michi");
        txtTelefono.setText("0111553777890");
        txtEmail.setText("vaquita@gmail.com");
        txtDireccion.setText("Libertador esq Maipu");
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == this.RESULT) {
            if (resultCode == RESULT_OK) {
                int res = data.getIntExtra("resultado", 0);
                if (res == 1) {
                    Toast.makeText(this, "Contacto guardado correctamente.", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "No se pudo guardar el contacto.", Toast.LENGTH_SHORT).show();
                }
                finish();
            }
        }
    }

    public void buttonGuardar_Click(View view){
        try {
            ContactoHelper helper = new ContactoHelper(this);
                if (Validar()) {
                    Intent i = new Intent(this, AgregarContactoDosActivity.class);
                    i.putExtra("CONTACTO", bindData());
                    startActivityForResult(i, this.RESULT);
                }
            }
        catch (IOException ex) {
            //ex.printStackTrace();
        }
        catch (ClassNotFoundException ex) {
            //ex.printStackTrace();
        }
        catch (Exception e){
            Toast.makeText(this, "No se puede continuar con la carga del contacto", Toast.LENGTH_LONG).show();
            finish();
        }
    }

        private boolean Validar() throws IOException, ClassNotFoundException {

            try {
                Contacto reg = bindData();
                String nombre_pattern = "[a-zA-ZáéíóúÁÉÍÓÚ [']]+$";
                String telefono_pattern = "^[\\+]?[(]?[0-9]{3}[)]?[-\\s\\.]?[0-9]{3}[-\\s\\.]?[0-9]{4,8}$";
                String email_pattern_RFC = "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])";
                String mensaje = "";
                boolean correcto = true;
                if (!reg.getNombre().trim().matches(nombre_pattern)){
                    mensaje = "El apellido no es correcto";
                    correcto = false;
                }
                else if (!reg.getApellido().trim().matches(nombre_pattern)){
                    mensaje = "El nombre no es correcto";
                    correcto = false;
                }
                else if(!reg.getEmail().trim().matches(email_pattern_RFC)){
                    mensaje = "El mail no es correcto";
                    correcto = false;
                }
                else if(!reg.getTelefono().trim().matches(telefono_pattern)){
                    mensaje = "El teléfono no es correcto";
                    correcto = false;
                }
                if (!correcto){
                    Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show();
                    return false;
                }
                return true;
            }
            catch(Exception e){
                return false;
            }

        }
}