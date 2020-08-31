package com.example.tp2;

public class ContactoHelper {

    private Contacto contacto;

    public static Contacto getByID(long ID){
        Contacto c = new Contacto();
        // Leer datos en el archivo a partir del ID
        // Generar el objeto a partir de la serialización leída

        // Datos de prueba del método
        c.setApellido("Vélez");
        c.setNombre("Laura");
        c.setFechanac("17/02/2004");
        c.setTipoEmail("2");
        c.setEmail("laurafvelez@gmail.com");
        return c;
    }

}
