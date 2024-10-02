package com.example.parcialpreguntados;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class ActividadPregunta extends AppCompatActivity {

    private Pregunta[] preguntas = {
            new Pregunta("¿Qué indica esta señal?", new String[]{"Velocidad mínima 100km/h", "Velocidad máxima 100km/h", "Ninguna es correcta"}, 1, R.drawable.senal_100km),
            new Pregunta("¿Qué indica esta señal?", new String[]{"No avanzar", "Pare", "Contramano"}, 2, R.drawable.senal_contramano),
            // Agrega más preguntas aquí...
    };

    private int preguntaActual;
    private int puntaje;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actividad_pregunta);

        TextView tvPregunta = findViewById(R.id.tv_pregunta);
        ImageView imgPregunta = findViewById(R.id.img_pregunta);
        Button btnOpcion1 = findViewById(R.id.btn_opcion1);
        Button btnOpcion2 = findViewById(R.id.btn_opcion2);
        Button btnOpcion3 = findViewById(R.id.btn_opcion3);

        Intent intent = getIntent();
        preguntaActual = intent.getIntExtra("preguntaActual", 0);
        final String nombre = intent.getStringExtra("nombre");

        if (preguntaActual < preguntas.length) {
            final Pregunta pregunta = preguntas[preguntaActual];

            tvPregunta.setText(pregunta.getPregunta());
            imgPregunta.setImageResource(pregunta.getImagen());
            btnOpcion1.setText(pregunta.getOpciones()[0]);
            btnOpcion2.setText(pregunta.getOpciones()[1]);
            btnOpcion3.setText(pregunta.getOpciones()[2]);

            View.OnClickListener listener = new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int respuestaSeleccionada = 0;
                    if (v.getId() == R.id.btn_opcion1) {
                        respuestaSeleccionada = 0;
                    } else if (v.getId() == R.id.btn_opcion2) {
                        respuestaSeleccionada = 1;
                    } else if (v.getId() == R.id.btn_opcion3) {
                        respuestaSeleccionada = 2;
                    }

                    if (respuestaSeleccionada == pregunta.getRespuestaCorrecta()) {
                        puntaje++;
                    }

                    if (preguntaActual + 1 < preguntas.length) {
                        Intent nextIntent = new Intent(ActividadPregunta.this, ActividadPregunta.class);
                        nextIntent.putExtra("nombre", nombre);
                        nextIntent.putExtra("preguntaActual", preguntaActual + 1);
                        startActivity(nextIntent);
                    } else {
                        Intent resultIntent = new Intent(ActividadPregunta.this, ActividadResultados.class);
                        resultIntent.putExtra("puntaje", puntaje);
                        resultIntent.putExtra("nombre", nombre);
                        startActivity(resultIntent);
                    }
                }
            };

            btnOpcion1.setOnClickListener(listener);
            btnOpcion2.setOnClickListener(listener);
            btnOpcion3.setOnClickListener(listener);
        }
    }
}