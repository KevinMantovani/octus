package br.com.dactus.octusapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class CadastroClienteActivity extends AppCompatActivity {

    private EditText nome;
    private EditText cpfCnpj;
    private ClienteDAO dao;
    private Cliente cliente = null;

    @Override
    protected void onCreate (Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_cliente);

        nome = findViewById(R.id.editNome);
        cpfCnpj = findViewById(R.id.editCpfCnpj);
        dao = new ClienteDAO(this);

        Intent it = getIntent();
        if(it.hasExtra("cliente")){
            cliente = (Cliente) it.getSerializableExtra("cliente");
            nome.setText(cliente.getNome());
            cpfCnpj.setText(cliente.getCpfCnpj());
        }
    }
    //REALIZA O PROCESSO DE SALVAR O REGISTRO
    public void salvar (View view){

        if(cliente == null){
        Cliente c = new Cliente();
        c.setNome(nome.getText().toString());
        c.setCpfCnpj(cpfCnpj.getText().toString());
        dao.inserir(c);
        Toast.makeText(this, "Registro Salvo com Sucesso!", Toast.LENGTH_SHORT).show();

    }else{
            //REALIZA O PROCESSO DE ATUALIZAR O CADASTRO, CASO JÁ EXISTA
            cliente.setNome(nome.getText().toString());
            cliente.setCpfCnpj(cpfCnpj.getText().toString());
            dao.atualizar(cliente);
            Toast.makeText(this, "Cliente Atualizado com Sucesso!", Toast.LENGTH_SHORT).show();
        }
        }
}