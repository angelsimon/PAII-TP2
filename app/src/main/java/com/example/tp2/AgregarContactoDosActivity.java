package com.example.tp2;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;

public class AgregarContactoDosActivity extends AppCompatActivity {
    private Contacto reg;
    RadioGroup nivelesEstudio;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_contacto_dos);
        nivelesEstudio = (RadioGroup) this.findViewById(R.id.rdgNivelEstudios);
        nivelesEstudio.check(R.id.rb_Otros);
        try {
            Intent i = this.getIntent();
            reg = (Contacto) i.getSerializableExtra("CONTACTO");

        }
        catch(Exception e){
            throw e;
        }
    }
    private String bindIntereses(){
        String intereses = "";
        CheckBox deporte, musica, arte, tecnologia;
        deporte = (CheckBox) this.findViewById(R.id.chkDeporte);
        musica = (CheckBox) this.findViewById(R.id.chkMusica);
        arte = (CheckBox) this.findViewById(R.id.chkArte);
        tecnologia = (CheckBox) this.findViewById(R.id.chkTecno);
        ArrayList<String> lista_intereses = new ArrayList<String>();
        if (deporte.isChecked())
            lista_intereses.add("Deporte");
        if (musica.isChecked())
            lista_intereses.add("Música");
        if (arte.isChecked())
            lista_intereses.add("Arte");
        if (tecnologia.isChecked())
            lista_intereses.add("Tecnología");

        if (lista_intereses.size() > 0){
            for (String interes:lista_intereses) {
                intereses = intereses + interes;
                intereses = intereses + ", ";
            }
            // Quitamos la coma final
            intereses = intereses.substring(0, intereses.length()-2);
            // Reemplazamos la última coma por un " y "

            int ultimaComa = intereses.lastIndexOf(",");
            if (ultimaComa > 0)
                intereses = intereses.substring(0, ultimaComa) + " y" + intereses.substring(ultimaComa+1, intereses.length());
        }
        return intereses;
    }

    private Contacto bindData(){
        RadioButton nivelEstudio = (RadioButton) this.findViewById(nivelesEstudio.getCheckedRadioButtonId());
        Switch masInfo = (Switch) this.findViewById(R.id.switch1);
        reg.setNivelEstudio(nivelEstudio.getText().toString());
        reg.setIntereses(bindIntereses());
        reg.setRecibeInfo(masInfo.isChecked());
        return reg;
    }

    public void btnGuardar_Click(View view){
        try {
            ContactoHelper helper = new ContactoHelper(this);
            Intent i = new Intent();
            if (helper.save(bindData())) {
                i.putExtra("resultado", 1);
            }
            else{
                i.putExtra("resultado", 0);
            }
            setResult(Activity.RESULT_OK, i);
            finish();
        }
        catch(Exception e){

        }
    }

}