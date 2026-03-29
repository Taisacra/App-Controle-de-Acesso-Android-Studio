package com.example.controleacesso;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ConfigActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_config);
        Toast.makeText(this,"Testando", Toast.LENGTH_LONG).show();

        Button buttonConfirm = (Button) findViewById(R.id.buttonConfirm);
        Button buttonCancel = (Button) findViewById(R.id.buttonCancel);

        buttonConfirm.setOnClickListener(this);
        buttonCancel.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if(id == R.id.buttonConfirm) {
            SharedPreferences preferences = getSharedPreferences("DadosLogin", MODE_PRIVATE);
            SharedPreferences.Editor editor = preferences.edit();

            EditText input_name = findViewById(R.id.editTextTextName);
            EditText input_password1 = findViewById(R.id.editTextTextPasswordDef1);
            EditText input_password2 = findViewById(R.id.editTextTextPasswordDef2);
            String userName = input_name.getText().toString();
            String password1 = input_password1.getText().toString();
            String password2 = input_password2.getText().toString();

            if(userName.isBlank() || password1.isBlank() || password2.isBlank()){
                Toast.makeText(this, "Os campos devem estar todos preenchidos.", Toast.LENGTH_SHORT).show();
            } else if (!password1.equals(password2)) {
                Toast.makeText(this, "As senhas informadas não correspondem.", Toast.LENGTH_SHORT).show();
            }else if(password1.length() < 7 ){
                Toast.makeText(this, "A senha deve possuir no mínimo 8 caracteres", Toast.LENGTH_SHORT).show();
            }else {
                editor.putString("usuario", userName);
                editor.putString("senha", password1);
                editor.commit();
                Toast.makeText(this, "Usuário e senha salvos.", Toast.LENGTH_SHORT).show();
                finish();
            }
        }

        if (id == R.id.buttonCancel ){
            finish();
        }

    }
}