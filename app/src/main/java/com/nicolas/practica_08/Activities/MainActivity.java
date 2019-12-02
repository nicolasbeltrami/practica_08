package com.nicolas.practica_08.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.nicolas.practica_08.Activities.LoginActivity;
import com.nicolas.practica_08.R;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private SharedPreferences prefs;
    private TextView txtTitulo;
    private ListView lvlTarea;
    private Button btnAgregar;


    ArrayList<String> list = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        prefs = getSharedPreferences("Preference", Context.MODE_PRIVATE);
        txtTitulo = findViewById(R.id.txtTituloLista);
        lvlTarea = findViewById(R.id.LvTareas);
        btnAgregar = findViewById(R.id.btnAgregar);

        String archivos[] = fileList();

        if(archivoExiste(archivos,"tareas.txt")) {
            try {
                InputStreamReader archivo = new InputStreamReader(openFileInput("tareas.txt"));

                BufferedReader br = new BufferedReader(archivo);

                String linea = br.readLine();

                while (linea !=null){
                    list.add(linea);
                    linea = br.readLine();
                }
                br.close();
                archivo.close();
            } catch (IOException e) {

            }
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.list_item_tarea, list);
        lvlTarea.setAdapter(adapter);
        btnAgregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent nueva = new Intent(MainActivity.this, AgregarTarea.class);
                startActivity(nueva);
            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){
            case R.id.menu_logout:
                logout();
                return true;
            case R.id.menu_forget_logout:
                removeSharedPreferences();
                logout();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void logout(){
        Intent intent = new Intent(this, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

    private void removeSharedPreferences(){
        prefs.edit().clear().apply();
    }

    private boolean archivoExiste(String archivos[], String nombreArchivo){
        for (int i = 0; i<archivos.length; i++)
            if (nombreArchivo.equals(archivos[i]))
                return true;
        return false;
    }

}
