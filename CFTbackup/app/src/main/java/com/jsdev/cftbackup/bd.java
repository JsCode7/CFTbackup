package com.jsdev.cftbackup;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class bd extends SQLiteOpenHelper {

    public bd(Context context, String nombre, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, nombre, factory, version);
    }

    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table alumno(codigo integer primary key, nombre text, edad text)");
        db.execSQL("create table curso(codcurso integer primary key, nombrecurso text, cupo text, codprof text)");
        db.execSQL("create table profesor(codprof integer primary key, nombreprof text, telefono text)");
    }

    public void onUpgrade(SQLiteDatabase db, int versionAnte, int versionNue) {
        db.execSQL("drop table if exists alumno");
        db.execSQL("create table alumno(codigo integer primary key, nombre text, edad text)");
        db.execSQL("drop table if exists curso");
        db.execSQL("create table curso(codcurso integer primary key, nombrecurso text, cupo text, codprof text)");
        db.execSQL("drop table if exists profesor");
        db.execSQL("create table profesor(codprof integer primary key, nombreprof text, telefono text)");
    }

}