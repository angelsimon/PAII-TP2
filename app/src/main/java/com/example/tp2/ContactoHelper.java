package com.example.tp2;
import android.app.Application;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import java.io.EOFException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class ContactoHelper {
    Context ctx;

    public ContactoHelper(Context _ctx){
        ctx = _ctx;
    }

    public long Autoincrement() throws IOException, ClassNotFoundException{
        Contacto contacto = new Contacto();
        long id = 0;
        try{
            if (file_exists(Globals.FILE_CONTACTOS, this.ctx.fileList())){
                ObjectInputStream objInput = new ObjectInputStream(ctx.openFileInput(Globals.FILE_CONTACTOS));
                while(contacto != null){
                    contacto = (Contacto) objInput.readObject();
                    id++;
                }
            }
            else{
                return (long) 1;
            }
            return (long) -1;
        }
        catch(EOFException e){
            return id + 1;
        }
        catch (IOException e){
            throw e;
        }
    }

    public ArrayList<Contacto> getAll() throws  Exception{
        ArrayList<Contacto> lista = new ArrayList<Contacto>();
        long id = 1;
        try{
            while(true) {
                lista.add(Leer(id));
                id++;
            }
        }
        catch(Exception e){
            return lista;
        }
    }

    public Contacto Leer(long ID) throws Exception {
        try{
            Contacto contacto = new Contacto();
            if (file_exists(Globals.FILE_CONTACTOS, this.ctx.fileList())){
                ObjectInputStream objInput = new ObjectInputStream(ctx.openFileInput(Globals.FILE_CONTACTOS));
                while(contacto != null){
                    contacto = (Contacto) objInput.readObject();
                    if (ID == contacto.getId()){
                        objInput.close();
                        return contacto;
                    }
                }
            }
            else{
                throw new FileNotFoundException("No existe el archivo.");
            }
            return contacto;
        }
        catch(EOFException e){
            throw new Exception("No se encontró el contacto");
        } catch (FileNotFoundException e){
            throw e;
        } catch (IOException e){
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

    public boolean save(Contacto reg){
        try{

            if (file_exists(Globals.FILE_CONTACTOS, ctx.fileList())){
                AppendableObjectOutputStream oout = new AppendableObjectOutputStream(ctx.openFileOutput(Globals.FILE_CONTACTOS, ctx.MODE_APPEND));
                oout.writeObject(reg);
                oout.close();
            }
            else{
                ObjectOutputStream oout = new ObjectOutputStream(ctx.openFileOutput(Globals.FILE_CONTACTOS, ctx.MODE_PRIVATE));
                oout.writeObject(reg);
                oout.close();
            }



            return true;
        }catch (IOException e){
            return false;
        }
    }
    public Contacto getByID(long ID) throws Exception {
        try{
            Contacto c = new Contacto();
            c = Leer(ID);
            return c;
        }
        catch(Exception e){
            throw e;
        }
    }

}
