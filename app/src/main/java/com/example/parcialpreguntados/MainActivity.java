package com.example.parcialpreguntados;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText etNombre;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etNombre = findViewById(R.id.et_nombre);
        Button btnComenzar = findViewById(R.id.btn_comenzar);

        btnComenzar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String nombre = etNombre.getText().toString().trim();


                if (TextUtils.isEmpty(nombre)) {
                    // Mostrar un Toast si el campo está vacío
                    Toast.makeText(MainActivity.this, "Por favor, ingrese su nombre", Toast.LENGTH_SHORT).show();
                } else {
                    // Si el nombre no está vacío, iniciar la actividad de preguntas
                    Intent intent = new Intent(MainActivity.this, ActividadPregunta.class);
                    intent.putExtra("nombre", nombre);
                    intent.putExtra("preguntaActual", 0); // 1era pregunta
                    startActivity(intent);
                }
            }
        });
    }
}

