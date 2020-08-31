package com.example.tp2;
import android.app.Application;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class ContactoHelper {

    private Contacto contacto;

    public static Contacto Leer(long ID, Context ctx) throws IOException, ClassNotFoundException {
        try{
            ObjectInputStream objInput = new ObjectInputStream(ctx.openFileInput(Globals.FILE_CONTACTOS));
            Contacto contacto1 = (Contacto) objInput.readObject();
            Contacto contacto2 = (Contacto) objInput.readObject();
            Contacto contacto3 = (Contacto) objInput.readObject();
            objInput.close();
            return contacto2;
        }
        catch (IOException e){
            throw e;
        } catch (ClassNotFoundException e) {
            throw e;
        }
    }

    private static boolean file_exists(String file, String []files){
        for (String f:files) {
            if (f.equals(file)){
                return true;
            }
        }
        return false;
    }

    public static boolean save(Contacto reg, Context ctx){
        try{

            if (file_exists(Globals.FILE_CONTACTOS, ctx.fileList())){
                Toast.makeText(ctx, "Existe archivo", Toast.LENGTH_SHORT).show();
                AppendableObjectOutputStream oout = new AppendableObjectOutputStream(ctx.openFileOutput(Globals.FILE_CONTACTOS, ctx.MODE_APPEND));
                oout.writeObject(reg);
                oout.close();
            }
            else{
                Toast.makeText(ctx, "No existe archivo", Toast.LENGTH_SHORT).show();
                ObjectOutputStream oout = new ObjectOutputStream(ctx.openFileOutput(Globals.FILE_CONTACTOS, ctx.MODE_PRIVATE));
                oout.writeObject(reg);
                oout.close();
            }



            return true;
        }catch (IOException e){
            return false;
        }
    }
    public static Contacto getByID(long ID, Context ctx) throws IOException, ClassNotFoundException {
        try{
            Contacto c = new Contacto();
            c = Leer(1, ctx);
            return c;
        }
        catch(Exception e){
            throw e;
        }
    }

}
