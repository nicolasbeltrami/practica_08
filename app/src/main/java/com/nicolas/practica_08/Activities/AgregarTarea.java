package com.nicolas.practica_08.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.nicolas.practica_08.R;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

public class AgregarTarea extends AppCompatActivity {

    private Button btnAtras;
    private Button btnGuardar;
    private TextView tx;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_tarea);

        btnAtras = findViewById(R.id.btnRegresar);
        btnGuardar = findViewById(R.id.btnGuardar);
        tx = findViewById(R.id.txtTareaNombre);

        btnAtras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentAtras = new Intent(AgregarTarea.this, MainActivity.class);
                startActivity(intentAtras);
            }
        });

        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    OutputStreamWriter archivo = new OutputStreamWriter(openFileOutput("tareas.txt", Activity.MODE_PRIVATE));
                    archivo.write(tx.getText().toString());
                    archivo.flush();
                    archivo.close();
                }catch (IOException e){

                }
                Intent nuevaVista = new Intent(AgregarTarea.this, MainActivity.class);
                startActivity(nuevaVista);
            }
        });


    }
}
