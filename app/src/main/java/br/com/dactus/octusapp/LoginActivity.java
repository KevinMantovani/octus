package br.com.dactus.octusapp;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

//CLASS LOGIN ESTA SEM VALIDAÇÃO DE DADOS, PERMITINDO ACESSAR SEM INFORMAR DADOS
public class LoginActivity extends AppCompatActivity {

    private EditText Usuario;
    private EditText Senha;
    private Button botaoEntrar;

    public void Logar (View view) {

        Intent intent1 = new Intent(getApplicationContext(), HomeActivity.class);
        startActivity(intent1);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        Usuario = findViewById(R.id.editNomeID);
        Senha = findViewById(R.id.editSenhaID);
        botaoEntrar = findViewById(R.id.btnEntrarID);


    }
}





