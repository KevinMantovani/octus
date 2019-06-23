package br.com.dactus.octusapp;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

// REALIZA O PROCESSO DE CONEXÃO COM O BANCO DE DADOS (UTILIZADO O SQLITE), E CRIAÇÃO DAS TABELAS.
public class Conect extends SQLiteOpenHelper {

    private static final String name = "banco.db";
    private static final int version = 1;

    public Conect(@Nullable Context context) {
        super(context, name, null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

            db.execSQL("create table cliente(id integer primary key autoincrement," +
                    " nome varchar(50), cpfCnpj varchar(50))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
