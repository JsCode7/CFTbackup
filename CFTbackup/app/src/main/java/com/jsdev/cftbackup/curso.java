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

public class curso extends AppCompatActivity {
    private EditText et_codcurso, et_nombrecurso, et_cupo, et_codprof;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_curso);

        et_codcurso = (EditText) findViewById(R.id.txt_codcurso);
        et_nombrecurso = (EditText) findViewById(R.id.txt_nombrecurso);
        et_cupo = (EditText) findViewById(R.id.txt_cupo);
        et_codprof = (EditText) findViewById(R.id.txt_codprof);
    }
    public void Registrar3(View view){
        bd admin = new bd(this, "curso", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase();

        String codcurso = et_codcurso.getText().toString();
        String nombrecurso = et_nombrecurso.getText().toString();
        String cupo = et_cupo.getText().toString();
        String codprof = et_codprof.getText().toString();

        if(!codcurso.isEmpty() && !nombrecurso.isEmpty() && !cupo.isEmpty() && !codprof.isEmpty()){
            ContentValues registro = new ContentValues();
            registro.put("codcurso", codcurso);
            registro.put("nombrecurso", nombrecurso);
            registro.put("cupo", cupo);
            registro.put("codprof", codprof);

            bd.insert("curso", null, registro);

            bd.close();

            et_codcurso.setText("");
            et_nombrecurso.setText("");
            et_cupo.setText("");
            et_codprof.setText("");
            Toast.makeText(this, "Registro exitoso", Toast.LENGTH_SHORT).show();
        } else{
            Toast.makeText(this, "Debe llenar todos los campos", Toast.LENGTH_SHORT).show();
        }
    }

    //metodo de consulta
    public void Buscar3(View view){
        bd admin = new bd(this, "curso", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase();

        String codcurso = et_codcurso.getText().toString();

        if (!codcurso.isEmpty()){
            Cursor fila = bd.rawQuery
                    ("select codcurso, nombrecurso, cupo, codprof from curso where codcurso=" + codcurso, null);

            if(fila.moveToFirst()){
                et_codcurso.setText(fila.getString(0));
                et_nombrecurso.setText(fila.getString(1));
                et_cupo.setText(fila.getString(2));
                et_codprof.setText(fila.getString(3));
                bd.close();
            } else{
                Toast.makeText(this, "No existe el curso", Toast.LENGTH_SHORT).show();
                bd.close();
            }
        } else{
            Toast.makeText(this, "Debes introducir el codigo del curso", Toast.LENGTH_SHORT).show();
            bd.close();
        }
    }

    //metodo eliminar
    public void Eliminar3(View view){
        bd admin = new bd(this, "curso", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase();

        String codcurso = et_codcurso.getText().toString();

        if(!codcurso.isEmpty()){
            int cantidad = bd.delete("curso", "codcurso=" +codcurso, null);
            bd.close();

            et_codcurso.setText("");
            et_nombrecurso.setText("");
            et_cupo.setText("");
            et_codprof.setText("");

            if(cantidad == 1){
                Toast.makeText(this, "Curso eliminado exitosamente", Toast.LENGTH_SHORT).show();
            } else{
                Toast.makeText(this, "El Curso no existe", Toast.LENGTH_SHORT).show();
            }

        } else{
            Toast.makeText( this, "Debe introducir el codigo del Curso", Toast.LENGTH_LONG).show();
            bd.close();
        }
    }

    //metodo de modificar algun articulo
    public void Modificar3(View view) {
        bd admin = new bd(this, "curso", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase();

        String codcurso = et_codcurso.getText().toString();
        String nombrecurso = et_nombrecurso.getText().toString();
        String cupo = et_cupo.getText().toString();
        String codprof = et_codprof.getText().toString();

        if (!codcurso.isEmpty() && !nombrecurso.isEmpty() && !cupo.isEmpty() && !codprof.isEmpty()) {
            ContentValues registro = new ContentValues();
            registro.put("codcurso", codcurso);
            registro.put("nombrecurso", nombrecurso);
            registro.put("cupo", cupo);
            registro.put("codprof", codprof);

            int cantidad = bd.update("curso", registro, "codcurso=" + codcurso, null);
            bd.close();

            if (cantidad == 1) {
                Toast.makeText(this, "Curso modificado correctamente", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "El Curso no existe", Toast.LENGTH_SHORT).show();
            }

            et_codcurso.setText("");
            et_nombrecurso.setText("");
            et_cupo.setText("");
            et_codprof.setText("");

        } else {
            Toast.makeText(this, "Debe llenar los campos", Toast.LENGTH_SHORT).show();
            bd.close();
        }
    }
}
