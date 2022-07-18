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

public class alumno extends AppCompatActivity {
    private EditText et_cod, et_nombre, et_edad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alumno);

        et_cod = (EditText) findViewById(R.id.txt_cod);
        et_nombre = (EditText) findViewById(R.id.txt_nombre);
        et_edad = (EditText) findViewById(R.id.txt_edad);
    }

    public void Registrar(View view){
        bd admin = new bd(this, "alumno", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase();

        String codigo = et_cod.getText().toString();
        String nombre = et_nombre.getText().toString();
        String edad = et_edad.getText().toString();

        if(!codigo.isEmpty() && !nombre.isEmpty() && !edad.isEmpty()){
            ContentValues registro = new ContentValues();
            registro.put("codigo", codigo);
            registro.put("nombre", nombre);
            registro.put("edad", edad);

            bd.insert("alumno", null, registro);

            bd.close();

            et_cod.setText("");
            et_nombre.setText("");
            et_edad.setText("");
            Toast.makeText(this, "Registro exitoso", Toast.LENGTH_SHORT).show();
        } else{
            Toast.makeText(this, "Debe llenar todos los campos", Toast.LENGTH_SHORT).show();
        }
    }

    //metodo de consulta
    public void Buscar(View view){
        bd admin = new bd(this, "alumno", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase();

        String codigo = et_cod.getText().toString();

        if (!codigo.isEmpty()){
            Cursor fila = bd.rawQuery
                    ("select codigo, nombre, edad from alumno where codigo=" + codigo, null);

            if(fila.moveToFirst()){
                et_cod.setText(fila.getString(0));
                et_nombre.setText(fila.getString(1));
                et_edad.setText(fila.getString(2));
                bd.close();
            } else{
                Toast.makeText(this, "No existe el articulo", Toast.LENGTH_SHORT).show();
                bd.close();
            }
        } else{
            Toast.makeText(this, "Debes introducir el codigo del articulo", Toast.LENGTH_SHORT).show();
            bd.close();
        }
    }

    //metodo eliminar
    public void Eliminar(View view){
        bd admin = new bd(this, "alumno", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase();

        String codigo = et_cod.getText().toString();

        if(!codigo.isEmpty()){
            int cantidad = bd.delete("alumno", "codigo=" +codigo, null);
            bd.close();

            et_cod.setText("");
            et_nombre.setText("");
            et_edad.setText("");

            if(cantidad == 1){
                Toast.makeText(this, "Alumno eliminado exitosamente", Toast.LENGTH_SHORT).show();
            } else{
                Toast.makeText(this, "El alumno no existe", Toast.LENGTH_SHORT).show();
            }

        } else{
            Toast.makeText( this, "Debe introducir el codigo del alumno", Toast.LENGTH_LONG).show();
            bd.close();
        }
    }

    //metodo de modificar algun articulo
    public void Modificar(View view){
        bd admin = new bd(this, "alumno", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase();

        String codigo = et_cod.getText().toString();
        String nombre = et_nombre.getText().toString();
        String edad = et_edad.getText().toString();

        if(!codigo.isEmpty() && !nombre.isEmpty() && !edad.isEmpty() ){
            ContentValues registro = new ContentValues();
            registro.put("codigo", codigo);
            registro.put("nombre", nombre);
            registro.put("edad", edad);

            int cantidad = bd.update("alumno", registro, "codigo=" + codigo, null);
            bd.close();

            if(cantidad == 1){
                Toast.makeText(this, "Alumno modificado correctamente", Toast.LENGTH_SHORT).show();
            } else{
                Toast.makeText(this, "El alumno no existe", Toast.LENGTH_SHORT).show();
            }

            et_cod.setText("");
            et_nombre.setText("");
            et_edad.setText("");

        } else{
            Toast.makeText(this, "Debe llenar los campos", Toast.LENGTH_SHORT).show();
            bd.close();
        }
    }
}