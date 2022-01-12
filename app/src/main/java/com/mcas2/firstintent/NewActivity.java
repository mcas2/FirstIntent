package com.mcas2.firstintent;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class NewActivity extends AppCompatActivity {
    TextView tv;
    EditText mensajeDeVuelta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new);

        tv = findViewById(R.id.textView);
        Bundle extras = getIntent().getExtras();
        String mensaje = extras.getString("Extra_mensaje");
        tv.setText(mensaje);
    }

    public void salir (View view){
        mensajeDeVuelta = findViewById(R.id.mensajeDeVuelta);
        String mensaje2 = mensajeDeVuelta.getText().toString();

        Intent myResult = new Intent();
        myResult.putExtra("Extra_vuelta", mensaje2);
        setResult(RESULT_OK, myResult);
        this.finish(); //Esta era la única línea para salir del intent.
        //Las demás permiten recuperar información de vuelta.
    }
}