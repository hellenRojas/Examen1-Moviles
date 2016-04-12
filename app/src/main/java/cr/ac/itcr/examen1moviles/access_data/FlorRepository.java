package cr.ac.itcr.examen1moviles.access_data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;

import cr.ac.itcr.examen1moviles.entity.Flor;

/**
 * Created by Hellen Rojas Rojas on 30/03/2016.
 */
public class FlorRepository implements IRepository<Flor> {
    private Connexion connexion;
    public FlorRepository(Context context){
        connexion = new Connexion(context);
    }

    @Override
    public boolean Save(Flor flor) {
        try{
            SQLiteDatabase db = connexion.getWritableDatabase();
            if(db != null){
                ContentValues newData = new ContentValues();
                newData.put("name", flor.getName());
                newData.put("nameC", flor.getNamec());
                newData.put("color", flor.getColor());
                db.insert("flor", null, newData);
                connexion.close();
                return false;
            }
        }
        catch (Exception error){
            Log.d("error",error.getMessage());
        }
        return true;
    }

    @Override
    public boolean Update(Flor flor) {
        try{
            SQLiteDatabase db = connexion.getWritableDatabase();
            if(db != null){
                ContentValues updateData = new ContentValues();
                updateData.put("nameC", flor.getNamec());
                updateData.put("color", flor.getColor());
                db.update("flor", updateData, "name=?", new String[]{String.valueOf(flor.getName())});
                connexion.close();
                return false;
            }
        }
        catch (Exception error){
            Log.d("error",error.getMessage());
        }
        return true;
    }

    @Override
    public boolean Delete(Flor place) {
        try{
            SQLiteDatabase db = connexion.getWritableDatabase();
            if(db != null){

                String[] args = new String[]{String.valueOf(place.getName())};
                db.delete("flor","name=?",args);
                connexion.close();
                return false;
            }
        }
        catch (Exception error){
            Log.d("error",error.getMessage());
        }
        return true;
    }

    @Override
    public ArrayList<String> GetAll() {
        ArrayList<String> listFlores = new ArrayList<String>();
        try{
            SQLiteDatabase db = connexion.getWritableDatabase();
            if(db != null){
                Cursor cursor = db.query("flor",new String[]{"name","nameC","color"},null,null,null,null,"name desc",null);
                if (cursor.moveToFirst()){
                    do{
                        String nombre = cursor.getString(0);

                        listFlores.add(nombre);
                    }while (cursor.moveToNext());
                }
                connexion.close();
                return listFlores;
            }
        }
        catch (Exception error){
            Log.d("error",error.getMessage());
        }
        return listFlores;
    }

    @Override
    public Flor GetBy(String flor) {
        Flor florTemp = new Flor();
        try{
            SQLiteDatabase db = connexion.getWritableDatabase();
            if(db != null){
                String[] args = new String[]{flor};
                Cursor cursor = db.query("flor",new String[]{"name","nameC","color"},"name=?",args,null,null,"name desc",null);
                if (cursor.moveToFirst()){


                        String nombre = cursor.getString(0);
                        String nombreC = cursor.getString(1);
                        String color = cursor.getString(2);

                        florTemp.setName(nombre);
                        florTemp.setNameC(nombreC);
                        florTemp.setColor(color);


                }
                connexion.close();
            }
        }
        catch (Exception error){
            Log.d("error",error.getMessage());
        }
        return florTemp;
    }
}
