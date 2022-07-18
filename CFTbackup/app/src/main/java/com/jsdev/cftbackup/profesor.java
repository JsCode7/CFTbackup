package com.jsdev.cftbackup;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.FontResourcesParserCompat;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class profesor extends AppCompatActivity {
    private EditText et_codprof, et_nombreprof, et_telefono;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profesor);

        et_codprof = (EditText) findViewById(R.id.txt_codprof);
        et_nombreprof = (EditText) findViewById(R.id.txt_nombreprof);
        et_telefono = (EditText) findViewById(R.id.txt_telefono);
    }
    public void Registrar2(View view){
        bd admin = new bd(this, "profesor", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase();

        String codprof = et_codprof.getText().toString();
        String nombreprof = et_nombreprof.getText().toString();
        String telefono = et_telefono.getText().toString();

        if(!codprof.isEmpty() && !nombreprof.isEmpty() && !telefono.isEmpty()){
            ContentValues registro = new ContentValues();
            registro.put("codprof", codprof);
            registro.put("nombreprof", nombreprof);
            registro.put("telefono", telefono);

            bd.insert("profesor", null, registro);

            bd.close();

            et_codprof.setText("");
            et_nombreprof.setText("");
            et_telefono.setText("");
            Toast.makeText(this, "Registro exitoso", Toast.LENGTH_SHORT).show();
        } else{
            Toast.makeText(this, "Debe llenar todos los campos", Toast.LENGTH_SHORT).show();
        }
    }

    //metodo de consulta
    public void Buscar2(View view){
        bd admin = new bd(this, "profesor", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase();

        String codprof = et_codprof.getText().toString();

        if (!codprof.isEmpty()){
            Cursor fila = bd.rawQuery
                    ("select codprof, nombreprof, telefono from profesor where codprof=" + codprof, null);

            if(fila.moveToFirst()){
                et_codprof.setText(fila.getString(0));
                et_nombreprof.setText(fila.getString(1));
                et_telefono.setText(fila.getString(2));
                bd.close();
            } else{
                Toast.makeText(this, "No existe el profesor", Toast.LENGTH_SHORT).show();
                bd.close();
            }
        } else{
            Toast.makeText(this, "Debes introducir el codigo del profesor", Toast.LENGTH_SHORT).show();
            bd.close();
        }
    }

    //metodo eliminar
    public void Eliminar2(View view){
        bd admin = new bd(this, "profesor", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase();

        String codprof = et_codprof.getText().toString();

        if(!codprof.isEmpty()){
            int cantidad = bd.delete("profesor", "codprof=" +codprof, null);
            bd.close();

            et_codprof.setText("");
            et_nombreprof.setText("");
            et_telefono.setText("");

            if(cantidad == 1){
                Toast.makeText(this, "Profesor eliminado exitosamente", Toast.LENGTH_SHORT).show();
            } else{
                Toast.makeText(this, "El profesor no existe", Toast.LENGTH_SHORT).show();
            }

        } else{
            Toast.makeText( this, "Debe introducir el codigo del profesor", Toast.LENGTH_LONG).show();
            bd.close();
        }
    }

    //metodo de modificar algun articulo
    public void Modificar2(View view) {
        bd admin = new bd(this, "profesor", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase();

        String codprof = et_codprof.getText().toString();
        String nombreprof = et_nombreprof.getText().toString();
        String telefono = et_telefono.getText().toString();

        if (!codprof.isEmpty() && !nombreprof.isEmpty() && !telefono.isEmpty()) {
            ContentValues registro = new ContentValues();
            registro.put("codprof", codprof);
            registro.put("nombreprof", nombreprof);
            registro.put("telefono", telefono);

            int cantidad = bd.update("profesor", registro, "codprof=" + codprof, null);
            bd.close();

            if (cantidad == 1) {
                Toast.makeText(this, "Profesor modificado correctamente", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "El profesor no existe", Toast.LENGTH_SHORT).show();
            }

            et_codprof.setText("");
            et_nombreprof.setText("");
            et_telefono.setText("");

        } else {
            Toast.makeText(this, "Debe llenar los campos", Toast.LENGTH_SHORT).show();
            bd.close();
        }
    }
}
