package com.example.tp2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tp2.Contacto;
import com.example.tp2.ContactoHelper;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


public class ListarContactoUnoActivity extends AppCompatActivity {
    private EditText txtNombre, txtApellido, txtTelefono, txtEmail, txtDireccion;
    private Spinner cbxTipoTelefono, cbxTipoEmail;
    private TextView txtNacimiento, txtIntereses, txtRecibeInfo, txtNivelEstudio;


    private void bindForm(){
        /* Bind de los controles */
        txtNombre = (EditText) findViewById(R.id.txtNombre);
        txtApellido = (EditText) findViewById(R.id.txtApellido);
        txtTelefono = (EditText) findViewById(R.id.txtTelefono);
        txtEmail = (EditText) findViewById(R.id.txtEmail);
        txtDireccion = (EditText) findViewById(R.id.txtDireccion);
        cbxTipoTelefono = (Spinner) findViewById(R.id.cbxTipoTelefono);
        cbxTipoEmail = (Spinner) findViewById(R.id.cbxTipoEmails);
        txtNacimiento = (TextView) findViewById(R.id.txtNacimiento);
        txtRecibeInfo = (TextView) findViewById(R.id.txtRecibeInfo);
        txtNivelEstudio = (TextView) findViewById(R.id.txtNivelEstudio);
        txtIntereses = (TextView) findViewById(R.id.txtIntereses);


        txtNombre.setEnabled(false);
        txtApellido.setEnabled(false);
        txtTelefono.setEnabled(false);
        txtEmail.setEnabled(false);
        txtDireccion.setEnabled(false);
        cbxTipoTelefono.setEnabled(false);
        cbxTipoEmail.setEnabled(false);
    }
    private void bindData(Contacto reg) throws ParseException {
        try {
            txtNombre.setText(reg.getNombre());
            txtApellido.setText(reg.getApellido());
            txtTelefono.setText(reg.getTelefono());
            cbxTipoTelefono.setSelection(Integer.parseInt(reg.getTipoTel()));
            txtEmail.setText(reg.getEmail());
            cbxTipoEmail.setSelection(Integer.parseInt(reg.getTipoEmail()));
            //SimpleDateFormat sd = new SimpleDateFormat("dd/MM/yyyy");
            //Date d = sd.parse(reg.getFechanac());
            //txtNacimiento.updateDate(d.getYear()+1900, d.getMonth(), d.getDate());
            txtDireccion.setText(reg.getDireccion());
            txtNacimiento.setText(reg.getFechanac());
            txtRecibeInfo.setText(reg.getRecibeInfo()==true?"Sí":"No");
            txtIntereses.setText(reg.getIntereses());
            txtNivelEstudio.setText(reg.getNivelEstudio());
        }
        catch (Exception ex) {
            throw ex;
        }
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_contacto_uno);
        try {
            Intent i = this.getIntent();
            Long ID = i.getLongExtra("CONTACTO_ID", 0);
            bindForm();
            setSpinnerMail();
            setSpinnerTelefono();
            ContactoHelper helper = new ContactoHelper(this);
            bindData(helper.getByID(ID));
        } catch (ParseException e) {

        }
        catch (Exception e){
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
            finish();
        }
    }

    public void btnVolver_Click(View view){
        finish();
    }
}