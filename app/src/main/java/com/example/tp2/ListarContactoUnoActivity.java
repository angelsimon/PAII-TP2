package com.example.tp2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.tp2.Contacto;
import com.example.tp2.ContactoHelper;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class ListarContactoUnoActivity extends AppCompatActivity {
    private EditText txtNombre, txtApellido, txtTelefono, txtEmail, txtDireccion;
    private Spinner cbxTipoTelefono, cbxTipoEmail;
    private DatePicker txtNacimiento;


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

        txtNombre.setEnabled(false);
        txtApellido.setEnabled(false);
        txtTelefono.setEnabled(false);
        txtEmail.setEnabled(false);
        txtDireccion.setEnabled(false);
        cbxTipoTelefono.setEnabled(false);
        cbxTipoEmail.setEnabled(false);
        txtNacimiento.setEnabled(false);
    }
    private void bindData(Contacto reg) throws ParseException {
        try {
            txtNombre.setText(reg.getNombre());
            txtApellido.setText(reg.getApellido());
            txtTelefono.setText(reg.getTelefono());
            cbxTipoTelefono.setSelection(Integer.parseInt(reg.getTipoTel()));
            txtEmail.setText(reg.getEmail());
            cbxTipoEmail.setSelection(Integer.parseInt(reg.getTipoEmail()));
            SimpleDateFormat sd = new SimpleDateFormat("dd/MM/yyyy");
            Date d = sd.parse(reg.getFechanac());
            txtNacimiento.updateDate(d.getYear()+1900, d.getMonth(), d.getDate());
            txtDireccion.setText(reg.getDireccion());
        }
        catch (Exception ex) {
            throw ex;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_contacto_uno);
        try {
            bindForm();
            ContactoHelper helper = new ContactoHelper(this);
            bindData(helper.getByID((long) 3)); // TODO: Recibir el ID del Activity VerTodos
        } catch (ParseException e) {

        }
        catch (Exception e){
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
            finish();
        }
    }
}