package com.example.controleacesso;

import static androidx.core.content.ContextCompat.startActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnConfig = (Button) findViewById(R.id.buttonConfig);
        Button btnExit = (Button) findViewById(R.id.buttonExit);

        btnConfig.setOnClickListener(this);
        btnExit.setOnClickListener(this);
        mostrarTelaLogin();
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        if(id == R.id.buttonConfig){
            Intent i = new Intent(this, ConfigActivity.class);
            startActivity(i);
        }
        if(id == R.id.buttonExit ){
            finish();
        }
    }

    public void mostrarTelaLogin(){

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater(); //criando o inflater

        View view = inflater.inflate(R.layout.dialog_login, null); //le o arquivo xml e transforma em views(TextView,buttons)
        builder.setView(view); //definindo que o dialog vai usar essa view

        Button buttonEntrar = view.findViewById(R.id.buttonEntrar);
        Button buttonSair = view.findViewById(R.id.buttonSair);
        EditText textName = view.findViewById(R.id.editTextTextNameLogin);
        EditText textPassword = view.findViewById(R.id.editTextTextPasswordLogin);
        AlertDialog dialog = builder.create(); //constroi o dialog com o que foi configurado
        dialog.setCancelable(false);
        dialog.show();

        dialog.getWindow().setLayout(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        );


        buttonEntrar.setOnClickListener(v ->{
           String user = textName.getText().toString().trim();
           String senha = textPassword.getText().toString().trim();

           SharedPreferences preferences= getSharedPreferences("DadosLogin", MODE_PRIVATE);
           String userSalvo = preferences.getString("usuario", "");
           String senhaSalva = preferences.getString("senha", "");

            if (user.equals(userSalvo) && senha.equals(senhaSalva)) {
               Toast.makeText(this, "Bem-Vindo!", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            } else {
                Toast.makeText(this, "Usuário ou senha incorretos!", Toast.LENGTH_SHORT).show();
            }

        });

        buttonSair.setOnClickListener(v -> {
            finish();
        });














  ;  }


}
