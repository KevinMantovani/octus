package br.com.dactus.octusapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class ClienteDAO {

    private Conect conexao;
    private SQLiteDatabase banco;

    public ClienteDAO(Context context){
        conexao = new Conect(context);
        banco = conexao.getWritableDatabase();
    }

    //REALIZA A INSERÇÃO DO CADASTRO
    public long inserir(Cliente cliente){

        ContentValues values = new ContentValues();
        values.put("nome", cliente.getNome());
        values.put("cpfCnpj",cliente.getCpfCnpj());
        return banco.insert("cliente", null, values);
    }

    //LISTA TODOS OS REGISTROS
    public List<Cliente> obterTodos(){
        List<Cliente> clientes = new ArrayList<>();
        Cursor cursor = banco.query("cliente",new String[]{"id","nome","cpfCnpj"},
                null,null,null,null,null);
        while(cursor.moveToNext()){
            Cliente c = new Cliente();
            c.setId(cursor.getInt(0));
            c.setNome(cursor.getString(1));
            c.setCpfCnpj(cursor.getString(2));
            clientes.add(c);
        }
        return clientes;
    }

    //EXCLUI O REGISTRO SELECIONADO
    public void excluir(Cliente c){
        banco.delete("cliente","id = ?", new String[]{c.getId().toString()});
    }

    //ATUALIZA O REGISTRO SELECIONADO
    public void atualizar(Cliente cliente){
        ContentValues values = new ContentValues();
        values.put("nome", cliente.getNome());
        values.put("cpfCnpj",cliente.getCpfCnpj());
        banco.update("cliente", values,
                "id = ?", new String[]{cliente.getId().toString()});
    }
}
