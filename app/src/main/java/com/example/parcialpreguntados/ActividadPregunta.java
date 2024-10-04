package com.example.parcialpreguntados;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class ActividadPregunta extends AppCompatActivity {

    private Pregunta[] preguntas = {
            new Pregunta("¿Qué indica esta señal?", new String[]{"Velocidad mínima 100km/h", "Velocidad máxima 100km/h", "Ninguna es correcta"}, 1, R.drawable.senal_100km),
            new Pregunta("¿Qué indica esta señal?", new String[]{"No avanzar", "Pare", "Contramano"}, 2, R.drawable.senal_contramano),
            new Pregunta("¿Cuál de estas señales prohíbe adelantarse?", new String[]{"Imagen 1", "Imagen 2", "Imagen 3"}, 1, R.drawable.senal_adelantarse),
            new Pregunta("¿Cuál de estas señales indica la presencia de un puente angosto?", new String[]{"Imagen 1", "Imagen 2", "Imagen 3"}, 2, R.drawable.senal_puente),
            new Pregunta("¿Qué indica esta señal?", new String[]{"Comienzo de autopista", "Comienzo de ruta", "Comienzo de semiautopista"}, 0, R.drawable.senal_autopista),
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
        Button btnSalir = findViewById(R.id.btn_salir);

        Intent intent = getIntent();
        preguntaActual = intent.getIntExtra("preguntaActual", 0);
        puntaje = intent.getIntExtra("puntaje", 0);  // pasar puntaje entre preguntas
        final String nombre = intent.getStringExtra("nombre");

        if (preguntaActual < preguntas.length) {
            final Pregunta pregunta = preguntas[preguntaActual];

            tvPregunta.setText(pregunta.getPregunta());
            imgPregunta.setImageResource(pregunta.getImagen());
            btnOpcion1.setText(pregunta.getOpciones()[0]);
            btnOpcion2.setText(pregunta.getOpciones()[1]);
            btnOpcion3.setText(pregunta.getOpciones()[2]);

            // listener botones
            View.OnClickListener listener = new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int respuestaSeleccionada = -1;
                    if (v.getId() == R.id.btn_opcion1) {
                        respuestaSeleccionada = 0;
                    } else if (v.getId() == R.id.btn_opcion2) {
                        respuestaSeleccionada = 1;
                    } else if (v.getId() == R.id.btn_opcion3) {
                        respuestaSeleccionada = 2;
                    }

                    // cambio de color boton seleccionado
                    Button btnSeleccionado = (Button) v;

                    // es correcta?
                    if (respuestaSeleccionada == pregunta.getRespuestaCorrecta()) {
                        puntaje++;
                        btnSeleccionado.setBackgroundColor(Color.GREEN);
                        Toast.makeText(ActividadPregunta.this, "¡Correcto!", Toast.LENGTH_SHORT).show();
                    } else {
                        btnSeleccionado.setBackgroundColor(Color.RED);
                        Toast.makeText(ActividadPregunta.this, "Incorrecto", Toast.LENGTH_SHORT).show();

                        // mostrar en verde
                        if (pregunta.getRespuestaCorrecta() == 0) {
                            btnOpcion1.setBackgroundColor(Color.GREEN);
                        } else if (pregunta.getRespuestaCorrecta() == 1) {
                            btnOpcion2.setBackgroundColor(Color.GREEN);
                        } else if (pregunta.getRespuestaCorrecta() == 2) {
                            btnOpcion3.setBackgroundColor(Color.GREEN);
                        }
                    }

                    // esperar 2 segundos antes de pasar a la siguiente pregunta
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            if (preguntaActual + 1 < preguntas.length) {
                                Intent nextIntent = new Intent(ActividadPregunta.this, ActividadPregunta.class);
                                nextIntent.putExtra("nombre", nombre);
                                nextIntent.putExtra("preguntaActual", preguntaActual + 1);
                                nextIntent.putExtra("puntaje", puntaje);  // pasar el puntaje actualizado
                                startActivity(nextIntent);
                                finish();
                            } else {
                                // si es la última pregunta, ir a la actividad de resultados
                                Intent resultIntent = new Intent(ActividadPregunta.this, ActividadResultados.class);
                                resultIntent.putExtra("puntaje", puntaje);
                                resultIntent.putExtra("nombre", nombre);
                                startActivity(resultIntent);
                                finish();
                            }
                        }
                    }, 2000);  // Espera 2 segundos
                }
            };

            btnOpcion1.setOnClickListener(listener);
            btnOpcion2.setOnClickListener(listener);
            btnOpcion3.setOnClickListener(listener);
        }

        // Botón para salir al menú principal
        btnSalir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ActividadPregunta.this, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish();
            }
        });
    }
}

