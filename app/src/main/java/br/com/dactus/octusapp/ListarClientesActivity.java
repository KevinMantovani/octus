package br.com.dactus.octusapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;

import java.util.ArrayList;
import java.util.List;


//CLASS CRIADA PARA REALIZAR A ADMINISTRAÇÃO DOS DADOS
public class ListarClientesActivity extends AppCompatActivity {



    private ListView listaView;
    private ClienteDAO dao;
    private List<Cliente> clientes;
    private List<Cliente> clientesFiltrados = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_clientes);


        listaView = findViewById(R.id.lista_clientes);
        dao = new ClienteDAO(this);
        clientes = dao.obterTodos();
        clientesFiltrados.addAll(clientes);
        ClienteAdapter adaptador = new ClienteAdapter(this,clientesFiltrados);
        listaView.setAdapter(adaptador);
        registerForContextMenu(listaView);
    }

    public boolean onCreateOptionsMenu (Menu menu){
        MenuInflater i = getMenuInflater();
        i.inflate(R.menu.menu_principal, menu);

        SearchView sv = (SearchView) menu.findItem(R.id.app_bar_search).getActionView();
        sv.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                procuraCliente(s);
                return false;
            }
        });

        return true;
    }

    public void onCreateContextMenu (ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo){
        super.onCreateContextMenu(menu,v,menuInfo);
        MenuInflater i = getMenuInflater();
        i.inflate(R.menu.menu_contexto, menu);
    }

    //FUNÇÃO EM QUE REALIZA A BUSCA DOS CLIENTES
    public void procuraCliente (String nome){
        clientesFiltrados.clear();
        for(Cliente c: clientes){
            if (c.getNome().toLowerCase().contains(nome.toLowerCase())){
                clientesFiltrados.add(c);
            }
            listaView.invalidateViews();
        }
    }
    //REALIZA A EXCLUSÃO DO CLIENTE, POREM REALIZA UMA PERGUNTA ANTES.
    public void excluir(MenuItem item){

        AdapterView.AdapterContextMenuInfo menuInfo = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        final Cliente clienteExcluir = clientesFiltrados.get(menuInfo.position);
        AlertDialog dialog = new AlertDialog.Builder(this)
                .setTitle("Atenção")
                .setMessage("Realmente deseja excluir este Cliente?")
                .setNegativeButton("NÃO", null)
                .setPositiveButton("SIM", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        clientesFiltrados.remove(clienteExcluir);
                        clientes.remove(clienteExcluir);
                        dao.excluir(clienteExcluir);
                        listaView.invalidateViews();
                    }
                }).create();
        dialog.show();

    }

    //BOTAO IFROOM PARA QUE PERMITA CADASTRAR UM NOVO REGISTRO
    public void cadastrar(MenuItem item){
        Intent it = new Intent(this, CadastroClienteActivity.class);
        startActivity(it);
    }
    //BOTAO IFROOM PARA QUE PERMITA ATUALIZAR UM REGISTRO
    public void atualizar(MenuItem item){
        AdapterView.AdapterContextMenuInfo menuInfo = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();

        final Cliente clienteAtualizar = clientesFiltrados.get(menuInfo.position);
        Intent it = new Intent(this, CadastroClienteActivity.class);
        it.putExtra("cliente",clienteAtualizar);
        startActivity(it);
    }
    //BOTAO IFROOM PARA QUE PERMITA RETORNAR PARA A HOME
    public void voltarHome(MenuItem item){
        AdapterView.AdapterContextMenuInfo menuInfo = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        Intent intent = new Intent(this,HomeActivity.class);
        startActivity(intent);
    }

    //REALIZA A ATUALIZAÇÃO DA LISTA APÓS REALIZAR UMA INCLUSÃO,ALTERAÇÃO OU EXCLUSÃO
    @Override
    public void onResume(){
        super.onResume();
        clientes = dao.obterTodos();
        clientesFiltrados.clear();
        clientesFiltrados.addAll(clientes);
        listaView.invalidateViews();
    }
}
