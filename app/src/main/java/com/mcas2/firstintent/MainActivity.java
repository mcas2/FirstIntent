package com.mcas2.firstintent;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContract;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText editText;
    String mensaje;
    ActivityResultLauncher<Intent> myARL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myARL = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == Activity.RESULT_OK){
                        Intent myIntentVuelta = result.getData();
                        String mensaje2 = myIntentVuelta.getStringExtra("Extra_vuelta").toString();
                        Context context = getApplicationContext();
                        int duration = Toast.LENGTH_LONG;
                        Toast toast = Toast.makeText(context, mensaje2, duration);
                        toast.show();

                    } else if (result.getResultCode() == Activity.RESULT_CANCELED){
                        String mensaje2 = "Sin mensaje de vuelta";
                        Context context = getApplicationContext();
                        int duration = Toast.LENGTH_LONG;
                        Toast toast = Toast.makeText(context, mensaje2, duration);
                        toast.show();
                    }
                }
            }
        );
    }

    public void lanzar_actividad(View view) {
        editText = findViewById(R.id.editText);
        mensaje = editText.getText().toString();

        //Bundle myBundle = new Bundle();
        //myBundle.putString("Extra_mensaje", mensaje);

        Intent myIntent = new Intent(this, NewActivity.class);
        myIntent.putExtra("Extra_mensaje", mensaje);
        myARL.launch(myIntent);

    }

    @Override
    protected void onStop() {
        super.onStop();
        setResult(RESULT_CANCELED, null);
    }
}