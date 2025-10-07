package com.example.projetobiblioteca;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.MotionEvent;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class BibliLogin extends AppCompatActivity {

    private EditText edtRa, edtSenha;
    private boolean senhaVisivel = false;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bibli_login);

        edtRa = findViewById(R.id.edt_ra);
        edtSenha = findViewById(R.id.edt_senha);
        Button btLogin = findViewById(R.id.bt_login);

        //aqui vai ficar o negocio de deixar a senha aparecendo
        edtSenha.setOnTouchListener((v, event) -> {
            final int DRAWABLE_RIGHT = 2;
            if (event.getAction() == MotionEvent.ACTION_UP) {
                if (event.getRawX() >= (edtSenha.getRight() - edtSenha.getCompoundDrawables()[DRAWABLE_RIGHT].getBounds().width())) {
                    if (senhaVisivel) {
                        edtSenha.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                        senhaVisivel = false;
                    } else {
                        edtSenha.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                        senhaVisivel = true;
                    }
                    edtSenha.setSelection(edtSenha.getText().length());
                    return true;
                }
            }
            return false;
        });

        //btao de login
        btLogin.setOnClickListener(v -> {
            String ra = edtRa.getText().toString().trim();
            String senha = edtSenha.getText().toString().trim();

            if (ra.isEmpty() || senha.isEmpty()) {
                Toast.makeText(this, "Preencha todos os campos", Toast.LENGTH_SHORT).show();
            } else if (ra.equals("123456") && senha.equals("Aluno1")) {
                Intent intent = new Intent(BibliLogin.this, UnitecaActivity.class);
                startActivity(intent);
                finish();
            } else {
                Toast.makeText(this, "RA ou senha inválidos", Toast.LENGTH_SHORT).show();
            }
        });
    }
}